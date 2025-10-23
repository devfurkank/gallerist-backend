# 🚗 Gallerist - Araba Galerisi Yönetim Sistemi

Gallerist, modern araba galerilerinin ihtiyaçlarını karşılamak üzere geliştirilmiş kapsamlı bir backend yönetim sistemidir. Spring Boot framework'ü kullanılarak geliştirilmiş, güvenli, ölçeklenebilir ve profesyonel bir RESTful API uygulamasıdır.

## 📋 İçindekiler

- [Özellikler](#-özellikler)
- [Teknoloji Yığını](#-teknoloji-yığını)
- [Proje Yapısı](#-proje-yapısı)
- [Veritabanı Şeması](#-veritabanı-şeması)
- [Kurulum](#-kurulum)
- [Kullanım](#-kullanım)
- [API Endpoints](#-api-endpoints)
- [Güvenlik](#-güvenlik)
- [Konfigürasyon](#-konfigürasyon)

## ✨ Özellikler

### 🔐 Kimlik Doğrulama ve Yetkilendirme
- JWT (JSON Web Token) tabanlı kimlik doğrulama
- Refresh token mekanizması
- Spring Security entegrasyonu
- Kullanıcı kayıt ve giriş sistemi
- Stateless authentication yapısı

### 🚙 Araç Yönetimi
- Araç ekleme, güncelleme, silme ve listeleme
- Plaka, marka, model, üretim yılı takibi
- Fiyat ve döviz türü yönetimi
- Hasar bedeli takibi
- Araç durumu takibi (Satılabilir/Satıldı)
- Döviz kuru entegrasyonu

### 👤 Galericiler Yönetimi
- Galerici bilgileri yönetimi
- Adres bilgisi entegrasyonu
- Galerici-araç ilişkilendirmesi
- Satış geçmişi takibi

### 🧑‍💼 Müşteri Yönetimi
- Müşteri bilgileri yönetimi (Ad, Soyad, TCKN)
- Doğum tarihi takibi
- Adres bilgisi yönetimi
- Hesap bilgisi entegrasyonu
- Satın alınan araçlar geçmişi

### 💰 Hesap Yönetimi
- Müşteri hesap bilgileri
- IBAN doğrulaması
- Hesap bakiyesi takibi
- Döviz türü desteği

### 📍 Adres Yönetimi
- Detaylı adres bilgisi yönetimi
- İl, ilçe, mahalle bilgisi
- Açık adres ve posta kodu

### 📊 Satış Yönetimi
- Araç satış kayıtları
- Galerici-Müşteri-Araç ilişkilendirmesi
- Satış geçmişi ve raporlama
- Unique constraint'ler ile veri bütünlüğü

### 💱 Döviz Kuru Entegrasyonu
- Anlık döviz kurları
- Farklı para birimleri desteği (TRY, USD, EUR)
- Otomatik fiyat hesaplama

## 🛠 Teknoloji Yığını

### Backend Framework
- **Spring Boot 3.5.6** - Ana application framework
- **Java 21** - Programlama dili
- **Maven** - Bağımlılık yönetimi ve build tool

### Veritabanı
- **PostgreSQL** - İlişkisel veritabanı
- **Spring Data JPA** - ORM ve veritabanı erişimi
- **Hibernate** - JPA implementasyonu

### Güvenlik
- **Spring Security** - Uygulama güvenliği
- **JWT (JJWT 0.12.6)** - Token tabanlı kimlik doğrulama
  - jjwt-api
  - jjwt-impl
  - jjwt-jackson

### Yardımcı Kütüphaneler
- **Lombok** - Boilerplate kod azaltma
- **Spring Validation** - Veri doğrulama
- **Spring Web** - RESTful web servisleri

### Test
- **Spring Boot Test** - Unit ve integration testler
- **Spring Security Test** - Güvenlik testleri

## 📁 Proje Yapısı

```
gallerist/
├── src/
│   ├── main/
│   │   ├── java/dev/furkankeskin/
│   │   │   ├── config/              # Konfigürasyon sınıfları
│   │   │   │   ├── AppConfig.java
│   │   │   │   └── SecurityConfig.java
│   │   │   ├── controller/          # REST Controller'lar
│   │   │   │   ├── impl/           # Controller implementasyonları
│   │   │   │   ├── IRestAccountController.java
│   │   │   │   ├── IRestAddressController.java
│   │   │   │   ├── IRestAuthenticationController.java
│   │   │   │   ├── IRestCarController.java
│   │   │   │   ├── IRestCurrencyRatesController.java
│   │   │   │   ├── IRestCustomerController.java
│   │   │   │   ├── IRestGalleristCarController.java
│   │   │   │   ├── IRestGalleristController.java
│   │   │   │   ├── IRestSaledCarController.java
│   │   │   │   ├── RestBaseController.java
│   │   │   │   └── RootEntity.java
│   │   │   ├── dto/                 # Data Transfer Objects
│   │   │   │   ├── AccountDTO.java
│   │   │   │   ├── AccountDTOIU.java
│   │   │   │   ├── AddressDTO.java
│   │   │   │   ├── AddressDTOIU.java
│   │   │   │   ├── AuthRequest.java
│   │   │   │   ├── AuthResponse.java
│   │   │   │   ├── BaseDTO.java
│   │   │   │   ├── CarDTO.java
│   │   │   │   ├── CarDTOIU.java
│   │   │   │   ├── CurrencyRatesItems.java
│   │   │   │   ├── CurrencyRatesResponse.java
│   │   │   │   ├── CustomerDTO.java
│   │   │   │   ├── CustomerDTOIU.java
│   │   │   │   ├── GalleristCarDTO.java
│   │   │   │   ├── GalleristCarDTOIU.java
│   │   │   │   ├── GalleristDTO.java
│   │   │   │   ├── GalleristDTOIU.java
│   │   │   │   ├── RefreshTokenRequest.java
│   │   │   │   ├── SaledCarDTO.java
│   │   │   │   ├── SaledCarDTOIU.java
│   │   │   │   └── UserDTO.java
│   │   │   ├── enums/               # Enum türleri
│   │   │   │   ├── CarStatusType.java (SALABLE, SALED)
│   │   │   │   └── CurrencyType.java
│   │   │   ├── exception/           # Exception yönetimi
│   │   │   │   ├── BaseException.java
│   │   │   │   ├── ErrorMessage.java
│   │   │   │   └── MessageType.java
│   │   │   ├── gallerist/          # Main application
│   │   │   │   └── GalleristApplication.java
│   │   │   ├── handler/             # Exception handler'lar
│   │   │   │   ├── ApiError.java
│   │   │   │   ├── AuthEntryPoint.java
│   │   │   │   ├── Exception.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── jwt/                 # JWT yönetimi
│   │   │   │   ├── JWTAuthenticationFilter.java
│   │   │   │   └── JWTService.java
│   │   │   ├── model/               # Entity sınıfları
│   │   │   │   ├── Account.java
│   │   │   │   ├── Address.java
│   │   │   │   ├── BaseEntity.java
│   │   │   │   ├── Car.java
│   │   │   │   ├── Customer.java
│   │   │   │   ├── Gallerist.java
│   │   │   │   ├── GalleristCar.java
│   │   │   │   ├── RefreshToken.java
│   │   │   │   ├── SaledCar.java
│   │   │   │   └── User.java
│   │   │   ├── repository/          # JPA Repository'ler
│   │   │   │   ├── AccountRepository.java
│   │   │   │   ├── AddressRepository.java
│   │   │   │   ├── CarRepository.java
│   │   │   │   ├── CustomerRepository.java
│   │   │   │   ├── GalleristCarRepository.java
│   │   │   │   ├── GalleristRepository.java
│   │   │   │   ├── RefreshTokenRepository.java
│   │   │   │   ├── SaledCarRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/             # Business logic
│   │   │   │   ├── impl/           # Service implementasyonları
│   │   │   │   ├── IAccountService.java
│   │   │   │   ├── IAddressService.java
│   │   │   │   ├── IAuthenticationService.java
│   │   │   │   ├── ICarService.java
│   │   │   │   ├── ICurrencyRatesService.java
│   │   │   │   ├── ICustomerService.java
│   │   │   │   ├── IGalleristCarService.java
│   │   │   │   ├── IGalleristService.java
│   │   │   │   └── ISaledCarService.java
│   │   │   └── utils/               # Yardımcı sınıflar
│   │   │       └── DateUtils.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/dev/furkankeskin/gallerist/
│           └── GalleristApplicationTests.java
├── pom.xml
└── README.md
```

## 🗄 Veritabanı Şeması

### Ana Tablolar

#### 🚗 Car (Araç)
- **id**: Primary Key
- **plaka**: Araç plakası
- **brand**: Marka
- **model**: Model
- **production_year**: Üretim yılı
- **price**: Fiyat
- **currency_type**: Döviz türü (ENUM: TRY, USD, EUR)
- **damage_price**: Hasar bedeli
- **car_status_type**: Araç durumu (ENUM: SALABLE, SALED)

#### 👤 Gallerist (Galerici)
- **id**: Primary Key
- **first_name**: Ad
- **last_name**: Soyad
- **address_id**: Adres referansı (One-to-One)

#### 🧑‍💼 Customer (Müşteri)
- **id**: Primary Key
- **first_name**: Ad
- **last_name**: Soyad
- **tckn**: TC Kimlik Numarası
- **birth_date**: Doğum tarihi
- **address_id**: Adres referansı (One-to-One)
- **account_id**: Hesap referansı (One-to-One)

#### 💰 Account (Hesap)
- **id**: Primary Key
- **account_no**: Hesap numarası
- **iban**: IBAN numarası
- **amount**: Bakiye
- **currency_type**: Döviz türü (ENUM)

#### 📍 Address (Adres)
- **id**: Primary Key
- **city**: İl
- **district**: İlçe
- **neighborhood**: Mahalle
- **street**: Sokak/Cadde
- **postal_code**: Posta kodu

#### 🔗 GalleristCar (Galerici-Araç İlişkisi)
- **id**: Primary Key
- **gallerist_id**: Galerici referansı (Many-to-One)
- **car_id**: Araç referansı (Many-to-One)
- **Unique Constraint**: (gallerist_id, car_id)

#### 💼 SaledCar (Satılmış Araç)
- **id**: Primary Key
- **gallerist_id**: Galerici referansı (Many-to-One)
- **car_id**: Araç referansı (Many-to-One)
- **customer_id**: Müşteri referansı (Many-to-One)
- **Unique Constraint**: (gallerist_id, car_id, customer_id)

#### 👨‍💼 User (Kullanıcı)
- **id**: Primary Key
- **username**: Kullanıcı adı
- **password**: Şifre (hash'lenmiş)

#### 🔄 RefreshToken
- **id**: Primary Key
- **token**: Refresh token
- **user_id**: Kullanıcı referansı

### İlişkiler
- **Customer ↔ Account**: One-to-One
- **Customer ↔ Address**: One-to-One
- **Gallerist ↔ Address**: One-to-One
- **GalleristCar ↔ Gallerist**: Many-to-One
- **GalleristCar ↔ Car**: Many-to-One
- **SaledCar ↔ Gallerist**: Many-to-One
- **SaledCar ↔ Car**: Many-to-One
- **SaledCar ↔ Customer**: Many-to-One

## 🚀 Kurulum

### Ön Gereksinimler

- Java 21 veya üzeri
- Maven 3.6+
- PostgreSQL 12+
- IDE (IntelliJ IDEA, Eclipse, VS Code önerilir)

### Adım Adım Kurulum

1. **Projeyi Klonlayın**
```bash
git clone <repository-url>
cd gallerist
```

2. **PostgreSQL Veritabanını Oluşturun**
```sql
CREATE DATABASE postgres;
CREATE SCHEMA gallerist;
```

3. **Veritabanı Kullanıcısını Yapılandırın**

`src/main/resources/application.properties` dosyasını düzenleyin:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=gallerist
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. **Bağımlılıkları İndirin**
```bash
mvn clean install
```

5. **Uygulamayı Çalıştırın**
```bash
mvn spring-boot:run
```

veya IDE üzerinden `GalleristApplication.java` dosyasını çalıştırın.

Uygulama varsayılan olarak `http://localhost:8080` adresinde başlayacaktır.

## 💻 Kullanım

### 1. Kullanıcı Kaydı
```bash
POST /register
Content-Type: application/json

{
  "username": "kullanici_adi",
  "password": "sifre"
}
```

### 2. Giriş Yapma
```bash
POST /authenticate
Content-Type: application/json

{
  "username": "kullanici_adi",
  "password": "sifre"
}
```

Yanıt:
```json
{
  "data": {
    "accessToken": "eyJhbGciOiJIUzI1NiIs...",
    "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
  }
}
```

### 3. Token Yenileme
```bash
POST /refresh_token
Content-Type: application/json

{
  "refreshToken": "eyJhbGciOiJIUzI1NiIs..."
}
```

### 4. Korumalı Endpoint'lere Erişim
```bash
GET /api/v1/cars
Authorization: Bearer eyJhbGciOiJIUzI1NiIs...
```

## 🔌 API Endpoints

### 🔐 Authentication (Public)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| POST | `/register` | Yeni kullanıcı kaydı |
| POST | `/authenticate` | Kullanıcı girişi |
| POST | `/refresh_token` | Token yenileme |

### 🚗 Car Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/car` | Tüm araçları listele |
| GET | `/api/v1/car/{id}` | Belirli aracı getir |
| POST | `/api/v1/car` | Yeni araç ekle |
| PUT | `/api/v1/car/{id}` | Araç güncelle |
| DELETE | `/api/v1/car/{id}` | Araç sil |

### 👤 Gallerist Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/gallerist` | Tüm galericileri listele |
| GET | `/api/v1/gallerist/{id}` | Belirli galericiyi getir |
| POST | `/api/v1/gallerist` | Yeni galerici ekle |
| PUT | `/api/v1/gallerist/{id}` | Galerici güncelle |
| DELETE | `/api/v1/gallerist/{id}` | Galerici sil |

### 🧑‍💼 Customer Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/customer` | Tüm müşterileri listele |
| GET | `/api/v1/customer/{id}` | Belirli müşteriyi getir |
| POST | `/api/v1/customer` | Yeni müşteri ekle |
| PUT | `/api/v1/customer/{id}` | Müşteri güncelle |
| DELETE | `/api/v1/customer/{id}` | Müşteri sil |

### 💰 Account Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/account` | Tüm hesapları listele |
| GET | `/api/v1/account/{id}` | Belirli hesabı getir |
| POST | `/api/v1/account` | Yeni hesap ekle |
| PUT | `/api/v1/account/{id}` | Hesap güncelle |
| DELETE | `/api/v1/account/{id}` | Hesap sil |

### 📍 Address Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/address` | Tüm adresleri listele |
| GET | `/api/v1/address/{id}` | Belirli adresi getir |
| POST | `/api/v1/address` | Yeni adres ekle |
| PUT | `/api/v1/address/{id}` | Adres güncelle |
| DELETE | `/api/v1/address/{id}` | Adres sil |

### 🔗 GalleristCar Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/gallerist-car` | Tüm galerici-araç ilişkilerini listele |
| GET | `/api/v1/gallerist-car/{id}` | Belirli ilişkiyi getir |
| POST | `/api/v1/gallerist-car` | Yeni ilişki ekle |
| PUT | `/api/v1/gallerist-car/{id}` | İlişki güncelle |
| DELETE | `/api/v1/gallerist-car/{id}` | İlişki sil |

### 💼 SaledCar Management (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/saled-car` | Tüm satışları listele |
| GET | `/api/v1/saled-car/{id}` | Belirli satışı getir |
| POST | `/api/v1/saled-car` | Yeni satış kaydı ekle |
| PUT | `/api/v1/saled-car/{id}` | Satış güncelle |
| DELETE | `/api/v1/saled-car/{id}` | Satış sil |

### 💱 Currency Rates (Protected)
| Method | Endpoint | Açıklama |
|--------|----------|----------|
| GET | `/api/v1/currency-rates` | Güncel döviz kurlarını getir |

## 🔒 Güvenlik

### JWT Token Yapısı
Uygulama, JWT (JSON Web Token) tabanlı stateless authentication kullanır:

- **Access Token**: Kısa ömürlü (önerilen: 15-30 dakika), API erişimi için kullanılır
- **Refresh Token**: Uzun ömürlü (önerilen: 7-30 gün), access token yenilemek için kullanılır

### Security Configuration
```java
- Public Endpoints: /register, /authenticate, /refresh_token
- Protected Endpoints: /api/v1/** (JWT token gerektirir)
- CSRF: Disabled (Stateless API)
- Session Management: STATELESS
```

### Password Encoding
- Şifreler BCrypt algoritması ile hash'lenir
- Salt değeri otomatik oluşturulur
- Plain text şifreler asla veritabanında saklanmaz

### Authorization Header Format
```
Authorization: Bearer <access_token>
```

## ⚙️ Konfigürasyon

### application.properties
```properties
# Application Name
spring.application.name=gallerist

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=gallerist
spring.datasource.username=postgres
spring.datasource.password=your_password

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration (Recommended to add)
# jwt.secret=your-256-bit-secret-key
# jwt.expiration=900000
# jwt.refresh.expiration=604800000
```

### Environment Variables (Önerilen)
Güvenlik için hassas bilgileri environment variable olarak kullanın:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/postgres
export DB_USERNAME=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your-secret-key
```

## 🏗 Mimari Kararlar

### Katmanlı Mimari
Proje, klasik katmanlı mimari (Layered Architecture) prensiplerine uygun olarak geliştirilmiştir:

1. **Controller Layer**: HTTP isteklerini karşılar, validasyon ve response formatı
2. **Service Layer**: Business logic ve iş kuralları
3. **Repository Layer**: Veritabanı erişimi ve CRUD işlemleri
4. **Model Layer**: Entity sınıfları ve veritabanı şeması

### Interface-Implementation Pattern
- Tüm controller ve service'ler interface-implementation pattern ile yazılmıştır
- Loose coupling ve test edilebilirlik sağlar
- Dependency injection kolaylaşır

### DTO Pattern
- Entity'ler direkt olarak client'a expose edilmez
- DTO'lar ile data transfer sağlanır
- DTOIU (Insert/Update DTO'ları) ile farklı operasyonlar için farklı validasyonlar

### Base Entity Pattern
- Ortak alanlar (id, createDate, updateDate) BaseEntity'de tanımlanmış
- Code reusability sağlanmış
- Audit trail için hazır altyapı

## 🔄 Geliştirme Notları

### Hibernate DDL Auto
Proje şu anda `spring.jpa.hibernate.ddl-auto=update` kullanıyor. Production ortamı için:
- `validate` kullanın
- Veritabanı migrasyonları için Liquibase veya Flyway kullanımı önerilir

### Logging
- SQL logları development için aktif
- Production'da `spring.jpa.show-sql=false` olmalı
- Centralized logging sistemi (ELK Stack) entegrasyonu önerilir

### Exception Handling
- Global exception handler ile merkezi hata yönetimi
- Custom exception'lar (BaseException)
- Standartlaştırılmış error response'lar

## 📊 Performans Optimizasyonu

### Öneriler
1. **Database Indexing**: Sık sorgulanan alanlara index ekleyin
2. **Caching**: Redis ile caching mekanizması
3. **Connection Pooling**: HikariCP configuration
4. **Lazy/Eager Loading**: JPA fetch type optimizasyonu
5. **Pagination**: Büyük listeler için sayfalama

## 🧪 Test

Test çalıştırma:
```bash
mvn test
```

Test coverage raporu:
```bash
mvn jacoco:report
```

## 📝 Lisans

Bu proje özel bir projedir ve tüm hakları saklıdır.

## 👨‍💻 Geliştirici

**Furkan Keskin**
- GitHub: [@devfurkank](https://github.com/devfurkank)

## 🤝 Katkıda Bulunma

1. Bu repository'yi fork edin
2. Feature branch oluşturun (`git checkout -b feature/AmazingFeature`)
3. Değişikliklerinizi commit edin (`git commit -m 'Add some AmazingFeature'`)
4. Branch'inizi push edin (`git push origin feature/AmazingFeature`)
5. Pull Request oluşturun

## 📮 İletişim

Sorularınız veya önerileriniz için lütfen issue açın veya pull request gönderin.

---

⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın!

