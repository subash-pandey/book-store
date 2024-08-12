BookStore Application

Welcome to the BookStore Application, a comprehensive online platform for buying and selling books. This project is built using Spring Boot, offering a clean and user-friendly interface along with powerful backend functionalities.


Table of Contents
Features
Tech Stack
Usage
Testing
Project Structure
Contribution Guidelines
License


Features
User Roles and Authentication
Admin:
Full access to the application, including managing books and orders.
Can add, update, and delete books from the catalog.
Customer:
Can browse the catalog and place orders.
Can choose between using a stored address or providing a new one during checkout.

Book Management
CRUD operations for managing books.
Books are stored in a MySQL database (or H2 for testing).

Order Management
Customers can place orders, view order history, and track order statuses.

Search and Filtering
Search functionality allows users to find books by title, author, genre, or ISBN.

Secure Transactions
Integrated Spring Security for user authentication and role-based access control.

Tech Stack
Backend: Spring Boot, Spring Security, Spring Data JPA
Database:
Production: MySQL
Testing: H2 (In-memory database)
Frontend: JSP, Bootstrap for responsive design
Build Tool: Maven

Setup Instructions
Prerequisites
Java 17+
Maven 3.6+
MySQL

Open a browser and go to http://localhost:8080.
Default Admin Credentials
Username: admin
Password: admin123


Usage
Admin Operations
Manage Books: Add, update, or delete books from the catalog.
View Orders: Monitor and manage customer orders.

Customer Operations
Browse Books: Explore the catalog of available books.
Place Orders: Add books to the cart and proceed to checkout.

Testing
Running Tests
The project includes unit tests for key components, especially the BookDAO.
To run the tests:
BookDAOTest includes a parameterized test for verifying book retrieval by ID.

Project Structure
src/main/java: Contains the application source code.
controller/: Handles HTTP requests.
service/: Business logic.
dao/: Data access layer, including BookDAO.
entity/: JPA entities representing the database tables.
src/main/resources: Configuration files, including application.properties.
src/test/java: Test cases for the application.

Contribution Guidelines
Fork the repository.
Create a new branch for your feature or bug fix.
Submit a pull request with a clear description of your changes.


License
This project is licensed under the MIT License. See the LICENSE file for more details.

