#  E-Commerce REST API

A backend REST API for an e-commerce platform built with Spring Boot and PostgreSQL.

##  Tech Stack
- Java 21
- Spring Boot 4.0.6
- Spring Security + JWT Authentication
- Spring Data JPA + Hibernate
- PostgreSQL
- Maven
- Lombok

##  Features
- User registration and login with JWT authentication
- Role-based access control (USER / ADMIN)
- Product and category management
- Order placement and tracking
- Password encryption with BCrypt

##  API Endpoints

### Auth
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/api/auth/register` | Public |
| POST | `/api/auth/login` | Public |

### Products
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/products` | Public |
| GET | `/api/products/{id}` | Public |
| POST | `/api/products` | Admin |
| PUT | `/api/products/{id}` | Admin |
| DELETE | `/api/products/{id}` | Admin |

### Categories
| Method | Endpoint | Access |
|--------|----------|--------|
| GET | `/api/categories` | Public |
| POST | `/api/categories` | Admin |

### Orders
| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/api/orders` | User |
| GET | `/api/orders/my` | User |
| GET | `/api/orders` | Admin |
| PUT | `/api/orders/{id}/status` | Admin |

##  Setup & Run

### Prerequisites
- Java 17+
- PostgreSQL
- Maven

### Steps
1. Clone the repo
```bash
   git clone https://github.com/DeepstuffxP/ecommerce-api.git
```
2. Create a PostgreSQL database named `ecommerce_db`
3. Copy `application.properties.example` to `application.properties` and fill in your details
4. Run the app
```bash
   ./mvnw spring-boot:run
```
5. API runs on `http://localhost:8080`

##  Authentication
Register or login to get a JWT token, then include it in all protected requests:
```
Authorization: Bearer <your_token>
```
