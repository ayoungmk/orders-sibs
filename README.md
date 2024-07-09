# Order Management System Sibs

## Description

This is an order management system developed in Java using the Spring Boot framework. The purpose of this system is to manage customer orders, items, and their respective completion statuses.

## Features

- **User Registration:** Allows adding, editing, and removing users.
- **Item Registration:** Allows adding, editing, and removing items.
- **Order Management:** Creation, updating, and cancellation of orders.
- **Stock Management:** Creation, updating, and deletion of items in stock.
- **Reports:** Generation of reports on completed orders, stock movements, sent emails, and errors.

## Project Setup

### Prerequisites

- Java 8
- Maven 3.6.3 or higher
- PostgreSQL
- Docker

### Step-by-Step

1. **Clone the repository:**
   ```sh
   git clone https://github.com/ayoungmk/orders-sibs.git
   cd orders-sibs
   ```

2. **Data.sql**
      The project contains a Data.sql file that initially populates some information in the system's entities.

3. **Docker:**
   ```sh
   docker compose up
   ```

4. **Install dependencies and compile the project:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

5. **Run the application:**
   ```sh
   mvn spring-boot:run
   ```

## Entity Relationships

- **User:**
  - **ID:** Unique identifier for the user.
  - **Name:** User's name.
  - **Email:** User's email.

- **Item:**
  - **ID:** Unique identifier for the item.
  - **Name:** Item name.

- **Order:**
  - **ID:** Unique identifier for the order.
  - **Creation Date:** Order creation date.
  - **Item:** ID of the requested item.
  - **Quantity:** Total quantity of items in the order.
  - **User:** ID of the user who placed the order.
  - **Status:** Order status (e.g., Complete, Incomplete).

- **Stock:**
  - **ID:** Unique identifier for the stock.
  - **Item:** ID of the item in stock.
  - **Quantity:** Total quantity of items currently in stock.

- **Stock Movement:**
  - **ID:** Unique identifier for the movement.
  - **Creation Date:** Movement creation date.
  - **Item:** ID of the item associated with the movement.
  - **Quantity:** Total quantity of items associated with the movement.

## Business Rules

1. **Item Registration:**
   - The item name cannot be blank.
   - The item name must be at least 2 characters long.

2. **Order Management:**
   - An order can only be completed if there are products available in stock.
   - The system automatically checks the stock every time a stock movement or an order is created to complete incomplete orders.
   - After this initial check, the stock quantity must be updated.

3. **Stock Movement Management:**
   - When a stock movement is created, the system checks if the quantity is sufficient to complete incomplete orders.

4. **Reports:**
   - The report generated via Logger shows orders when they are complete, stock movements performed, sent emails, and errors.

## Route Instructions
Root: /orderSibs/v1

### Users

- **GET /users:** Returns a list of all users.
- **GET /users/{id}:** Returns details of a specific user.
- **POST /users:** Creates a new user.
  - Body (JSON):
    ```json
    {
      "name": "User Name",
      "email": "user@email.com"
    }
    ```
- **PUT /users/{id}:** Updates details of a specific user.
  - Body (JSON):
    ```json
    {
      "name": "User Name",
      "email": "user@email.com"
    }
    ```
- **DELETE /users/{id}:** Removes a specific user.

### Items

- **GET /items:** Returns a list of all items.
- **GET /items/{id}:** Returns details of a specific item.
- **POST /items:** Creates a new item.
  - Body (JSON):
    ```json
    {
      "name": "Item Name"
    }
    ```
- **PUT /items/{id}:** Updates details of a specific item.
  - Body (JSON):
    ```json
    {
      "name": "Item Name"
    }
    ```
- **DELETE /items/{id}:** Removes a specific item.

### Orders

- **GET /orders:** Returns a list of all orders.
- **GET /orders/{id}:** Returns details of a specific order.
- **POST /orders:** Creates a new order.
  - Body (JSON):
    ```json
    {
      "itemName": "Item Name",
      "quantity": "Item Quantity",
      "userName": "User Name"
    }
    ```
- **PUT /orders/{id}:** Updates details of a specific order.
  - Body (JSON):
    ```json
    {
      "itemName": "Item Name",
      "quantity": "Item Quantity",
      "userName": "User Name"
    }
    ```
- **DELETE /orders/{id}:** Removes a specific order.

### Stock

- **GET /stock:** Returns a list of all stock items.
- **GET /stock/{id}:** Returns details of a specific stock item.
- **POST /stock:** Creates a new stock item.
  - Body (JSON):
    ```json
    {
      "item": {
        "id": "Item ID",
        "name": "Item Name"
      },
      "quantity": "Item Quantity"
    }
    ```
- **PUT /stock/{id}:** Updates the quantity of a specific stock item.
  - Body (JSON):
    ```json
    {
      "quantity": "Item Quantity"
    }
    ```
- **DELETE /stock/{id}:** Removes a specific stock item.

### Stock Movements

- **GET /stockMovements:** Returns a list of all stock movements.
- **GET /stockMovements/{id}:** Returns details of a specific stock movement.
- **POST /stockMovements:** Creates a new stock movement.
  - Body (JSON):
    ```json
    {
      "creationDate": "Creation Timestamp",
      "itemName": "Item Name",
      "quantity": "Item Quantity"
    }
    ```
- **PUT /stockMovements/{id}:** Updates details of a specific stock movement.
  - Body (JSON):
    ```json
    {
      "creationDate": "Creation Timestamp",
      "itemName": "Item Name",
      "quantity": "Item Quantity"
    }
    ```
- **DELETE /stockMovements/{id}:** Removes a specific stock movement.

---
