# 🚗 Gallerist Backend API

A comprehensive RESTful API for automotive dealership management system built with Spring Boot and Java 21. This backend system provides complete functionality for managing car inventory, sales, customers, gallerists, and financial operations.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Development](#development)

## 🎯 Overview

The Gallerist Backend API is a robust enterprise-level application designed to streamline automotive dealership operations. It provides a complete solution for managing:

- **Vehicle Inventory**: Track cars, their status, pricing, and availability
- **Sales Management**: Process car sales with comprehensive transaction tracking
- **Customer Relations**: Manage customer information and purchase history
- **Gallerist Operations**: Handle dealer/gallerist information and assignments
- **Financial Tracking**: Multi-currency support and account management
- **User Authentication**: Secure JWT-based authentication with refresh tokens

## ✨ Features

### Core Functionality

- **🚘 Car Management**
  - Complete vehicle lifecycle tracking (Available, Sold, Reserved, Service, Accident)
  - Multi-currency pricing support (TRY, USD, EUR)
  - Damage/repair cost tracking
  - Vehicle details (brand, model, year, license plate)

- **👥 Customer Management**
  - Customer profile management
  - Address integration
  - Purchase history tracking

- **🏢 Gallerist Management**
  - Dealer information management
  - Address association
  - Vehicle assignment tracking

- **💰 Sales & Inventory**
  - Complete sales transaction processing
  - Inventory management with gallerist-car assignments
  - Real-time inventory status updates

- **💵 Financial Operations**
  - Multi-currency account management
  - Real-time currency rate tracking
  - Transaction history

- **🔐 Authentication & Security**
  - JWT-based authentication
  - Refresh token mechanism
  - Role-based access control
  - Secure password encryption

### Technical Features

- RESTful API design
- Service-oriented architecture
- JPA/Hibernate ORM with PostgreSQL
- Request validation with Bean Validation
- Comprehensive error handling
- CORS support
- Automatic database schema management

## 🛠 Technology Stack

### Core Technologies

- **Java 21** - Latest LTS version
- **Spring Boot 3.5.6** - Application framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Data persistence
- **PostgreSQL** - Relational database
- **Maven** - Dependency management

### Key Dependencies

```xml
<!-- Authentication & Security -->
- Spring Boot Security
- JWT (JJWT 0.12.6)

<!-- Data & Persistence -->
- Spring Data JPA
- PostgreSQL JDBC Driver
- Hibernate Validator

<!-- Development -->
- Lombok
- Spring Boot DevTools
```

## 🏗 Architecture

### Project Structure

```
gallerist/
├── src/main/java/dev/furkankeskin/
│   ├── config/              # Configuration classes
│   │   ├── AppConfig.java
│   │   └── SecurityConfig.java
│   ├── controller/          # REST Controllers
│   │   ├── impl/            # Controller implementations
│   │   ├── IRestAccountController.java
│   │   ├── IRestAddressController.java
│   │   ├── IRestAuthenticationController.java
│   │   ├── IRestCarController.java
│   │   ├── IRestCurrencyRatesController.java
│   │   ├── IRestCustomerController.java
│   │   ├── IRestGalleristCarController.java
│   │   ├── IRestGalleristController.java
│   │   ├── IRestSaledCarController.java
│   │   └── RestBaseController.java
│   ├── dto/                 # Data Transfer Objects (21 DTOs)
│   ├── enums/               # Enumerations
│   │   ├── CarStatusType.java
│   │   └── CurrencyType.java
│   ├── exception/           # Custom exceptions
│   ├── handler/             # Exception handlers
│   ├── jwt/                 # JWT utilities
│   ├── model/               # JPA Entities
│   │   ├── Account.java
│   │   ├── Address.java
│   │   ├── BaseEntity.java
│   │   ├── Car.java
│   │   ├── Customer.java
│   │   ├── Gallerist.java
│   │   ├── GalleristCar.java
│   │   ├── RefreshToken.java
│   │   ├── SaledCar.java
│   │   └── User.java
│   ├── repository/          # JPA Repositories (9 repositories)
│   ├── service/             # Business logic layer
│   │   ├── impl/            # Service implementations
│   │   └── Interface definitions
│   └── utils/               # Utility classes
└── src/main/resources/
    ├── application.properties
    ├── static/
    └── templates/
```

### Design Patterns

- **Layered Architecture**: Controller → Service → Repository → Entity
- **Interface-Based Design**: All services and controllers use interfaces
- **DTO Pattern**: Separation of internal models and API contracts
- **Repository Pattern**: Data access abstraction with Spring Data JPA
- **Builder Pattern**: Lombok @Builder for entity creation
- **Singleton Pattern**: Spring bean management

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21** or higher ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.8+** (included via Maven Wrapper)
- **PostgreSQL 14+** ([Download](https://www.postgresql.org/download/))
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)

## 🚀 Installation

### 1. Clone the Repository

```bash
git clone <repository-url>
cd gallerist-backend/gallerist
```

### 2. Database Setup

Create a PostgreSQL database and schema:

```sql
-- Connect to PostgreSQL
psql -U postgres

-- Create database (if not exists)
CREATE DATABASE postgres;

-- Create schema
CREATE SCHEMA gallerist;
```

### 3. Configure Database Connection

Edit `src/main/resources/application.properties`:

```properties
spring.application.name=gallerist
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.jpa.properties.hibernate.default_schema=gallerist
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

### 4. Build the Project

Using Maven Wrapper (recommended):

```bash
# Unix/Linux/Mac
./mvnw clean install

# Windows
mvnw.cmd clean install
```

Or using Maven directly:

```bash
mvn clean install
```

### 5. Run the Application

```bash
# Using Maven Wrapper
./mvnw spring-boot:run

# Or using Maven
mvn spring-boot:run

# Or run the JAR file
java -jar target/gallerist-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## ⚙️ Configuration

### Application Properties

Key configuration options in `application.properties`:

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
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# Server Configuration (optional)
server.port=8080
server.servlet.context-path=/

# JWT Configuration (add these)
jwt.secret=your-secret-key-here
jwt.expiration=3600000
jwt.refresh.expiration=86400000
```

### Environment Variables

For production, use environment variables:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/postgres
export DB_USERNAME=postgres
export DB_PASSWORD=your_password
export JWT_SECRET=your-secret-key
```

## 📚 API Documentation

## 📝 API Summary

### All Endpoints Overview

**Authentication (Public)**
- `POST /register` - User registration
- `POST /authenticate` - User login
- `POST /refresh_token` - Token refresh

**Cars (Protected)**
- `POST /rest/api/car/save` - Create car
- `GET /rest/api/car/list` - List cars
- `PUT /rest/api/car/update/{id}` - Update car
- `DELETE /rest/api/car/delete/{id}` - Delete car

**Gallerists (Protected)**
- `POST /rest/api/gallerist/save` - Create gallerist
- `GET /rest/api/gallerist/list` - List gallerists
- `PUT /rest/api/gallerist/update/{id}` - Update gallerist
- `DELETE /rest/api/gallerist/delete/{id}` - Delete gallerist

**Customers (Protected)**
- `POST /rest/api/customer/save` - Create customer
- `GET /rest/api/customer/list` - List customers
- `PUT /rest/api/customer/update/{id}` - Update customer
- `DELETE /rest/api/customer/delete/{id}` - Delete customer

**Inventory (Protected)**
- `POST /rest/api/gallerist-car/save` - Assign car to gallerist
- `GET /rest/api/gallerist-car/list` - List inventory
- `PUT /rest/api/gallerist-car/update/{id}` - Update assignment
- `DELETE /rest/api/gallerist-car/delete/{id}` - Remove assignment

**Sales (Protected)**
- `POST /rest/api/saled-car/save` - Record sale
- `GET /rest/api/saled-car/list` - List sales
- `PUT /rest/api/saled-car/update/{id}` - Update sale
- `DELETE /rest/api/saled-car/delete/{id}` - Delete sale

**Addresses (Protected)**
- `POST /rest/api/address/save` - Create address
- `GET /rest/api/address/list` - List addresses
- `PUT /rest/api/address/update/{id}` - Update address
- `DELETE /rest/api/address/delete/{id}` - Delete address

**Accounts (Protected)**
- `POST /rest/api/account/save` - Create account
- `GET /rest/api/account/list` - List accounts
- `PUT /rest/api/account/update/{id}` - Update account
- `DELETE /rest/api/account/delete/{id}` - Delete account

**Currency Rates (Protected)**
- `GET /rest/api/currency-rates?startDate={start}&endDate={end}` - Get rates

### Base URL

```
http://localhost:8080
```

### Authentication Endpoints

#### Register User
```http
POST /register
Content-Type: application/json

{
  "username": "string",
  "password": "string",
  "email": "string"
}
```

#### Login
```http
POST /authenticate
Content-Type: application/json

{
  "username": "string",
  "password": "string"
}

Response:
{
  "payload": {
    "accessToken": "string",
    "refreshToken": "string"
  }
}
```

#### Refresh Token
```http
POST /refresh_token
Content-Type: application/json

{
  "refreshToken": "string"
}
```

### Protected Endpoints

All endpoints below require JWT authentication:
```http
Authorization: Bearer <access_token>
```

#### Car Management

**Create Car**
```http
POST /rest/api/car/save
Content-Type: application/json
Authorization: Bearer <access_token>

Request Body:
{
  "plaka": "34ABC123",
  "brand": "BMW",
  "model": "320i",
  "productionYear": 2022,
  "price": 850000.00,
  "currencyType": "TL",
  "damagePrice": 0.00,
  "carStatusType": "SALABLE"
}

Response:
{
  "payload": {
    "id": 1,
    "plaka": "34ABC123",
    "brand": "BMW",
    "model": "320i",
    "productionYear": 2022,
    "price": 850000.00,
    "currencyType": "TL",
    "damagePrice": 0.00,
    "carStatusType": "SALABLE",
    "createTime": "2024-01-15T10:30:00"
  }
}
```

**List All Cars**
```http
GET /rest/api/car/list
Authorization: Bearer <access_token>

Response:
{
  "payload": [
    {
      "id": 1,
      "plaka": "34ABC123",
      "brand": "BMW",
      "model": "320i",
      "productionYear": 2022,
      "price": 850000.00,
      "currencyType": "TRY",
      "damagePrice": 0.00,
      "carStatusType": "AVAILABLE",
      "createTime": "2024-01-15T10:30:00"
    }
  ]
}
```

**Update Car**
```http
PUT /rest/api/car/update/{id}
Content-Type: application/json
Authorization: Bearer <access_token>

Request Body: Same as Create
```

**Delete Car**
```http
DELETE /rest/api/car/delete/{id}
Authorization: Bearer <access_token>

Response:
{
  "payload": "Car deleted successfully"
}
```

#### Gallerist Management

```http
POST    /rest/api/gallerist/save            # Create new gallerist
GET     /rest/api/gallerist/list            # List all gallerists
PUT     /rest/api/gallerist/update/{id}     # Update gallerist
DELETE  /rest/api/gallerist/delete/{id}     # Delete gallerist

Request Body (Save/Update):
{
  "firstName": "John",
  "lastName": "Doe",
  "addressId": 1
}
```

#### Customer Management

```http
POST    /rest/api/customer/save           # Create new customer
GET     /rest/api/customer/list           # List all customers
PUT     /rest/api/customer/update/{id}    # Update customer
DELETE  /rest/api/customer/delete/{id}    # Delete customer

Request Body (Save/Update):
{
  "firstName": "Jane",
  "lastName": "Smith",
  "tckn": "12345678901",
  "birthDate": "1990-01-15",
  "addressId": 1,
  "accountId": 1
}
```

#### Inventory Management

```http
POST    /rest/api/gallerist-car/save            # Assign car to gallerist
GET     /rest/api/gallerist-car/list            # List inventory
PUT     /rest/api/gallerist-car/update/{id}     # Update assignment
DELETE  /rest/api/gallerist-car/delete/{id}     # Remove assignment

Request Body (Save/Update):
{
  "galleristId": 1,
  "carId": 1
}
```

#### Sales Management

```http
POST    /rest/api/saled-car/save          # Record new sale (buy car)
GET     /rest/api/saled-car/list          # List all sales
PUT     /rest/api/saled-car/update/{id}   # Update sale
DELETE  /rest/api/saled-car/delete/{id}   # Delete sale

Request Body (Save/Update):
{
  "galleristId": 1,
  "carId": 1,
  "customerId": 1
}
```

#### Address Management

```http
POST    /rest/api/address/save           # Create new address
GET     /rest/api/address/list           # List all addresses
PUT     /rest/api/address/update/{id}    # Update address
DELETE  /rest/api/address/delete/{id}    # Delete address

Request Body (Save/Update):
{
  "city": "Istanbul",
  "district": "Kadikoy",
  "neighborhood": "Moda",
  "street": "Bahariye Cad. No:123"
}
```

#### Account Management

```http
POST    /rest/api/account/save           # Create new account
GET     /rest/api/account/list           # List all accounts
PUT     /rest/api/account/update/{id}    # Update account
DELETE  /rest/api/account/delete/{id}    # Delete account

Request Body (Save/Update):
{
  "accountNo": "123456789",
  "iban": "TR330006100519786457841326",
  "amount": 50000.00,
  "currencyType": "TRY"
}
```

#### Currency Rates

```http
GET /rest/api/currency-rates?startDate={startDate}&endDate={endDate}
Authorization: Bearer <access_token>

Example:
GET /rest/api/currency-rates?startDate=2024-01-01&endDate=2024-01-31

Response:
{
  "payload": {
    "rates": [
      {
        "date": "2024-01-15",
        "USD": 29.50,
        "EUR": 32.10,
        "TRY": 1.00
      }
    ]
  }
}

Note: This endpoint fetches currency exchange rates from external API for the specified date range.
```

## 🗄 Database Schema

### Core Entities

#### Car
```sql
- id (BIGINT, PK)
- plaka (VARCHAR) - License plate
- brand (VARCHAR)
- model (VARCHAR)
- production_year (INTEGER)
- price (DECIMAL)
- currency_type (VARCHAR) - ENUM: TRY, USD, EUR
- damage_price (DECIMAL)
- car_status_type (VARCHAR) - ENUM: AVAILABLE, SOLD, RESERVED, SERVICE, ACCIDENT
- create_time (TIMESTAMP)
```

#### Gallerist
```sql
- id (BIGINT, PK)
- first_name (VARCHAR)
- last_name (VARCHAR)
- address_id (BIGINT, FK) - OneToOne relation with Address
- create_time (TIMESTAMP)
```

#### Customer
```sql
- id (BIGINT, PK)
- first_name (VARCHAR)
- last_name (VARCHAR)
- tckn (VARCHAR) - Turkish Identity Number
- birth_date (DATE)
- address_id (BIGINT, FK) - OneToOne relation with Address
- account_id (BIGINT, FK) - OneToOne relation with Account
- create_time (TIMESTAMP)
```

#### GalleristCar (Inventory)
```sql
- id (BIGINT, PK)
- gallerist_id (BIGINT, FK) - ManyToOne relation with Gallerist
- car_id (BIGINT, FK) - ManyToOne relation with Car
- create_time (TIMESTAMP)
- UNIQUE CONSTRAINT: (gallerist_id, car_id)
```

#### SaledCar (Sales)
```sql
- id (BIGINT, PK)
- gallerist_id (BIGINT, FK) - ManyToOne relation with Gallerist
- car_id (BIGINT, FK) - ManyToOne relation with Car
- customer_id (BIGINT, FK) - ManyToOne relation with Customer
- create_time (TIMESTAMP)
- UNIQUE CONSTRAINT: (gallerist_id, car_id, customer_id)
```

#### Account
```sql
- id (BIGINT, PK)
- account_no (VARCHAR)
- iban (VARCHAR)
- amount (DECIMAL)
- currency_type (VARCHAR) - ENUM: TRY, USD, EUR
- create_time (TIMESTAMP)
```

#### Address
```sql
- id (BIGINT, PK)
- city (VARCHAR)
- street (VARCHAR)
- district (VARCHAR)
- neighborhood (VARCHAR)
- create_time (TIMESTAMP)
```

### Base Entity

All entities extend from `BaseEntity` which provides:
```sql
- id (BIGINT, PK) - Auto-generated primary key
- create_time (TIMESTAMP) - Record creation timestamp
```

### Enumerations

#### CarStatusType
- `SALABLE` - Car is available for sale
- `SALED` - Car has been sold

#### CurrencyType
- `TL` - Turkish Lira
- `USD` - US Dollar

### Important Notes

1. **Field Naming Conventions**:
   - Backend uses camelCase for DTO fields (e.g., `firstName`, `plaka`)
   - Database columns use snake_case (e.g., `first_name`, `create_time`)
   
2. **Timestamp Fields**:
   - Only `create_time` is stored (no separate update time)
   - All timestamps are managed by the application

3. **Relationships**:
   - `GalleristCar` and `SaledCar` use ManyToOne relationships
   - `Customer`, `Gallerist` use OneToOne relationships with Address and Account
   - Unique constraints prevent duplicate assignments

## 🔐 Security

### Authentication Flow

1. **Registration**: User registers with username, email, and password
2. **Login**: User authenticates and receives JWT access token and refresh token
3. **Authorization**: Each request includes JWT token in Authorization header
4. **Token Refresh**: When access token expires, use refresh token to get new access token

### Security Features

- **Password Encryption**: BCrypt password hashing
- **JWT Tokens**: Stateless authentication
- **Refresh Tokens**: Secure token renewal mechanism
- **CORS Configuration**: Controlled cross-origin access
- **SQL Injection Prevention**: JPA parameterized queries
- **XSS Protection**: Input validation and sanitization

### Public Endpoints

The following endpoints are publicly accessible:
- `/register` - User registration
- `/authenticate` - User login
- `/refresh_token` - Token refresh

All other endpoints require authentication.

## 🔧 Development

### Running in Development Mode

```bash
# Enable Spring Boot DevTools auto-reload
./mvnw spring-boot:run

# Enable debug logging
./mvnw spring-boot:run -Dspring-boot.run.arguments="--logging.level.dev.furkankeskin=DEBUG"
```

### IDE Setup

#### IntelliJ IDEA

1. Import as Maven project
2. Enable annotation processing (Settings → Build → Compiler → Annotation Processors)
3. Install Lombok plugin
4. Set JDK to Java 21

#### VS Code

1. Install extensions:
   - Language Support for Java
   - Spring Boot Extension Pack
   - Lombok Annotations Support
2. Configure Java SDK path
3. Reload project

### Code Style

- Follow Java naming conventions
- Use Lombok annotations to reduce boilerplate
- Write meaningful method and variable names
- Document complex business logic
- Keep methods focused and small

### Adding New Features

1. **Create Entity**: Add new JPA entity in `model/`
2. **Create Repository**: Extend JpaRepository in `repository/`
3. **Create DTOs**: Add request/response DTOs in `dto/`
4. **Create Service**: Implement business logic in `service/`
5. **Create Controller**: Add REST endpoints in `controller/`
6. **Test**: Write unit and integration tests

## 📦 Building for Production

### Create Production Build

```bash
# Build JAR file
./mvnw clean package -DskipTests

# The JAR will be created in target/
# gallerist-0.0.1-SNAPSHOT.jar
```

### Running in Production

```bash
# Run with production profile
java -jar target/gallerist-0.0.1-SNAPSHOT.jar \
  --spring.profiles.active=production \
  --spring.datasource.url=jdbc:postgresql://prod-host:5432/postgres \
  --spring.datasource.username=prod_user \
  --spring.datasource.password=prod_password
```

### Production Checklist

- [ ] Set `spring.jpa.hibernate.ddl-auto=validate` or `none`
- [ ] Disable SQL logging (`spring.jpa.show-sql=false`)
- [ ] Use environment variables for sensitive data
- [ ] Enable HTTPS/SSL
- [ ] Set appropriate CORS policies
- [ ] Configure proper logging levels
- [ ] Set up monitoring and health checks
- [ ] Enable rate limiting
- [ ] Configure database connection pooling

## 🐳 Docker Deployment

### Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY target/gallerist-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Docker Compose

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:14-alpine
    environment:
      POSTGRES_DB: postgres
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  gallerist-api:
    build: .
    ports:
      - "8080:8080"
    depends_on:
      - postgres
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/postgres
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: postgres

volumes:
  postgres_data:
```

### Run with Docker

```bash
# Build and run
docker-compose up -d

# View logs
docker-compose logs -f gallerist-api

# Stop
docker-compose down
```

## 👨‍💻 Author

**Furkan Keskin**
- GitHub: [@devfurkank](https://github.com/devfurkank)
- Package: dev.furkankeskin
