package dev.furkankeskin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends BaseEntity{

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "district")
    private String district;

    @Column(name = "neighborhood")
    private String neighborhood;
}
