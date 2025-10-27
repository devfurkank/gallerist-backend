package dev.furkankeskin.service.impl;

import dev.furkankeskin.dto.*;
import dev.furkankeskin.enums.CarStatusType;
import dev.furkankeskin.exception.BaseException;
import dev.furkankeskin.exception.ErrorMessage;
import dev.furkankeskin.exception.MessageType;
import dev.furkankeskin.model.Car;
import dev.furkankeskin.model.Customer;
import dev.furkankeskin.model.SaledCar;
import dev.furkankeskin.repository.CarRepository;
import dev.furkankeskin.repository.CustomerRepository;
import dev.furkankeskin.repository.GalleristRepository;
import dev.furkankeskin.repository.SaledCarRepository;
import dev.furkankeskin.service.ICurrencyRatesService;
import dev.furkankeskin.service.ISaledCarService;
import dev.furkankeskin.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SaledCarRepository saledCarRepository;

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent() && optionalCar.get().getCarStatusType().equals(CarStatusType.SALED.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remainingCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remainingCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
        return remainingCustomerUSDAmount.multiply(usd);
    }

    public boolean checkAmount(SaledCarDTOIU saledCarDTOIU) {
        Optional<Customer> optionalCustomer = customerRepository.findById(saledCarDTOIU.getCustomerId());
        if (optionalCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(saledCarDTOIU.getCustomerId().toString(), MessageType.NO_RECORD_EXIST));
        }
        Optional<Car> optionalCar = carRepository.findById(saledCarDTOIU.getCarId());
        if (optionalCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(saledCarDTOIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
        }
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optionalCustomer.get());
        if (customerUSDAmount.compareTo(optionalCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optionalCar.get().getPrice()) > 0) {
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(SaledCarDTOIU saledCarDTOIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(saledCarDTOIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(saledCarDTOIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(saledCarDTOIU.getCarId()).orElse(null));
        return saledCar;
    }

    @Override
    public SaledCarDTO buyCar(SaledCarDTOIU saledCarDTOIU) {
        if (!checkCarStatus(saledCarDTOIU.getCarId())) {
            throw new BaseException(new ErrorMessage(saledCarDTOIU.getCarId().toString(), MessageType.CAR_STATUS_IS_ALREADY_SALED));
        }
        if (!checkAmount(saledCarDTOIU)) {
            throw new BaseException(new ErrorMessage("", MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(saledCarDTOIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remainingCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }

    @Override
    public List<SaledCarDTO> getAllSaledCars() {
        List<SaledCar> saledCars = saledCarRepository.findAll();
        List<SaledCarDTO> saledCarDTOs = new ArrayList<>();
        for (SaledCar saledCar : saledCars) {
            saledCarDTOs.add(toDTO(saledCar));
        }
        return saledCarDTOs;
    }

    @Override
    public SaledCarDTO updateSaledCar(Long id, SaledCarDTOIU saledCarDTOIU) {
        Optional<SaledCar> optionalSaledCar = saledCarRepository.findById(id);
        if (optionalSaledCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        SaledCar saledCar = optionalSaledCar.get();

        saledCar.setCustomer(customerRepository.findById(saledCarDTOIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(saledCarDTOIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(saledCarDTOIU.getCarId()).orElse(null));

        SaledCar updatedSaledCar = saledCarRepository.save(saledCar);
        return toDTO(updatedSaledCar);
    }

    @Override
    public String deleteSaledCar(Long id) {
        Optional<SaledCar> optionalSaledCar = saledCarRepository.findById(id);
        if (optionalSaledCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(id.toString(), MessageType.NO_RECORD_EXIST));
        }

        saledCarRepository.delete(optionalSaledCar.get());
        return "Satış kaydı başarıyla silindi!";
    }

    public SaledCarDTO toDTO(SaledCar saledCar) {
        SaledCarDTO saledCarDTO = new SaledCarDTO();
        CustomerDTO customerDTO = new CustomerDTO();
        GalleristDTO galleristDTO = new GalleristDTO();
        CarDTO carDTO = new CarDTO();

        BeanUtils.copyProperties(saledCar, saledCarDTO);
        BeanUtils.copyProperties(saledCar.getCustomer(), customerDTO);
        BeanUtils.copyProperties(saledCar.getGallerist(), galleristDTO);
        BeanUtils.copyProperties(saledCar.getCar(), carDTO);

        saledCarDTO.setCustomer(customerDTO);
        saledCarDTO.setGallerist(galleristDTO);
        saledCarDTO.setCar(carDTO);
        return saledCarDTO;
    }
}
