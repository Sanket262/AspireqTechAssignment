📘 Merchant Product Management System

A RESTful Merchant Product Management API built with Spring Boot and Java 17, using MySQL as the database.
The system allows merchants to manage their products securely using a custom request header for merchant identification.

---

🚀 Features

- Merchant-based product isolation using HTTP header
- Create, update, view, and soft-delete products
- Fetch product by ID (merchant-specific)
- Fetch all products for a merchant
- Soft delete support (data not physically removed)
- Validation for merchant-product ownership
- Swagger / OpenAPI Documentation
- Clean layered architecture (Controller, Service, Repository)

---

🛠 Tech Stack

- Java 17+
- Spring Boot 3.x
- MySQL
- Spring Web (REST API)
- Spring Data JPA
- Lombok (optional)
- Springdoc OpenAPI (Swagger UI)
- Maven

---

⚙️ Setup Instructions

---

git clone https://github.com/your-username/MerchantProductManagementSystem.git
cd MerchantProductManagementSystem

2. Configure Application Properties
   server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/aspireqtechass
spring.datasource.username=root
spring.datasource.password=sanket
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.operationsSorter=method

Base URL
http://localhost:8080/api/v1/merchant/products

🔐 Required Header (MANDATORY)
X-Merchant-id: MERCHANT123

📌 API Endpoints (From Your Controller)
1️⃣ Create Product

Endpoint

POST /api/v1/merchant/products

cURL
curl -X POST http://localhost:8080/api/v1/merchant/products \
-H "Content-Type: application/json" \
-H "X-Merchant-id: MERCHANT123" \
-d '{
"name": "Laptop",
"description": "Gaming Laptop",
"price": 65000,
"quantity": 5
}'

2️⃣ Get Product by ID

Endpoint

GET /api/v1/merchant/products/{productId}

cURL
curl -X GET http://localhost:8080/api/v1/merchant/products/1 \
-H "X-Merchant-id: MERCHANT123"

3️⃣ Get All Products (With Pagination)

Endpoint

GET /api/v1/merchant/products?page=0&size=5

cURL (Default pagination)
curl -X GET "http://localhost:8080/api/v1/merchant/products" \
-H "X-Merchant-id: MERCHANT123"

cURL (Custom pagination)
curl -X GET "http://localhost:8080/api/v1/merchant/products?page=1&size=10" \
-H "X-Merchant-id: MERCHANT123"

4️⃣ Update Product

Endpoint

PUT /api/v1/merchant/products/{productId}

cURL
curl -X PUT http://localhost:8080/api/v1/merchant/products/1 \
-H "Content-Type: application/json" \
-H "X-Merchant-id: MERCHANT123" \
-d '{
"name": "Updated Laptop",
"description": "High performance laptop",
"price": 70000,
"quantity": 3
}'

5️⃣ Delete Product (Soft Delete)

Endpoint

DELETE /api/v1/merchant/products/{productId}

cURL
curl -X DELETE http://localhost:8080/api/v1/merchant/products/1 \
-H "X-Merchant-id: MERCHANT123"
