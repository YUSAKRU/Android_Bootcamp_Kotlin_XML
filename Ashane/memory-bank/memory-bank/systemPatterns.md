```markdown
# systemPatterns.md

## Aşhane Android Food Ordering App: System Patterns

This document details the system patterns employed in the Aşhane Android food ordering application. It covers architectural design, data models, API definitions, component structure, integration points, and scalability strategy.

## 1. Architectural Design: Microservices-Inspired, API-First

The Aşhane app utilizes a microservices-inspired architecture, focusing on independent, deployable services.  While not a full microservices implementation due to resource constraints and team size, the design principles are applied where beneficial to ensure modularity and scalability.  The core of the system revolves around an API-First approach. All communication between the Android app and the backend is mediated through RESTful APIs. This allows for future expansion to other platforms (iOS, web) and facilitates independent development and scaling of individual services.

*   **Key Principles:**
    *   **Modularity:**  Breaking down the application into smaller, manageable modules (services).
    *   **Loose Coupling:**  Minimizing dependencies between modules to allow for independent development and deployment.
    *   **API-First:**  Designing and implementing APIs before building the client applications.
    *   **Stateless Services:**  Services designed to be stateless where possible, improving scalability and resilience.
    *   **Data Ownership:** Each service owns its data, minimizing data dependencies across services.

## 2. Data Models

This section outlines the key data models used within the Aşhane system. These models are presented in a simplified format for clarity.  Actual implementation details (e.g., specific data types, database schema) may vary.

*   **User:**
    *   `userId` (String, Unique Identifier)
    *   `name` (String)
    *   `email` (String)
    *   `phoneNumber` (String)
    *   `address` (Object):
        *   `street` (String)
        *   `city` (String)
        *   `postalCode` (String)
    *   `paymentMethods` (Array of PaymentMethod)

*   **Restaurant:**
    *   `restaurantId` (String, Unique Identifier)
    *   `name` (String)
    *   `address` (Object):
        *   `street` (String)
        *   `city` (String)
        *   `postalCode` (String)
    *   `cuisine` (String)
    *   `openingHours` (String)
    *   `menu` (Array of MenuItem)

*   **MenuItem:**
    *   `menuItemId` (String, Unique Identifier)
    *   `name` (String)
    *   `description` (String)
    *   `price` (Number)
    *   `imageURL` (String)
    *   `category` (String)

*   **Order:**
    *   `orderId` (String, Unique Identifier)
    *   `userId` (String, Foreign Key to User)
    *   `restaurantId` (String, Foreign Key to Restaurant)
    *   `orderItems` (Array of OrderItem)
    *   `orderDate` (Date)
    *   `totalAmount` (Number)
    *   `status` (String, e.g., "Pending", "Confirmed", "Preparing", "Delivering", "Delivered", "Cancelled")
    *   `deliveryAddress` (Object):  (Same structure as User.address)

*   **OrderItem:**
    *   `menuItemId` (String, Foreign Key to MenuItem)
    *   `quantity` (Integer)
    *   `price` (Number)  (Price at the time of ordering)

*   **PaymentMethod:**
    *   `paymentMethodId` (String, Unique Identifier)
    *   `userId` (String, Foreign Key to User)
    *   `type` (String, e.g., "Credit Card", "Debit Card", "PayPal")
    *   `details` (Object)  (Type-specific details, e.g., card number, expiry date)

## 3. API Definitions

The application exposes RESTful APIs to interact with the backend services.  The following are examples of key API endpoints.  This is a simplified representation; complete API documentation will be maintained separately (e.g., using OpenAPI/Swagger).

*   **User Service:**
    *   `GET /users/{userId}`: Retrieve user information.
    *   `POST /users`: Create a new user.
    *   `PUT /users/{userId}`: Update user information.
    *   `POST /users/{userId}/paymentMethods`: Add a new payment method.

*   **Restaurant Service:**
    *   `GET /restaurants`: Retrieve a list of restaurants (with optional query parameters for filtering).
    *   `GET /restaurants/{restaurantId}`: Retrieve restaurant information.
    *   `GET /restaurants/{restaurantId}/menu`: Retrieve the restaurant's menu.

*   **Order Service:**
    *   `POST /orders`: Create a new order.
    *   `GET /orders/{orderId}`: Retrieve order information.
    *   `GET /orders/user/{userId}`: Retrieve orders for a specific user.
    *   `PUT /orders/{orderId}/status`: Update the status of an order.

*   **Authentication Service:**
    *   `POST /login`: Authenticate a user and return a JWT token.
    *   `POST /register`: Register a new user.

All API requests and responses will utilize JSON format.  Authentication will be handled using JWT (JSON Web Tokens).

## 4. Component Structure

The Aşhane Android application is structured into the following key components:

*   **UI Layer (Activities, Fragments, Composables):** Responsible for presenting data to the user and handling user interactions. Uses Model-View-ViewModel (MVVM) architecture.
*   **ViewModel Layer:**  Acts as an intermediary between the UI and the data layer.  Prepares and manages data for the UI, handles user input, and interacts with the repository layer.
*   **Repository Layer:**  Provides a clean API for accessing data from various sources (remote APIs, local database).  Handles caching and data persistence.
*   **Data Source Layer:**  Handles the actual data retrieval and persistence.  Includes:
    *   **Remote Data Source (Retrofit/OkHttp):**  Communicates with the backend APIs.
    *   **Local Data Source (Room/SQLite):**  Provides local data persistence for offline access and caching.
*   **Dependency Injection (Hilt/Dagger):**  Manages dependencies between components, promoting testability and maintainability.
*   **Navigation Component:** Handles navigation between different screens within the application.

## 5. Integration Points

The Aşhane Android application integrates with the following external systems:

*   **Backend API:** The primary integration point for retrieving restaurant data, user data, placing orders, and managing order status.
*   **Payment Gateway (e.g., Stripe, PayPal SDK):**  Integrates with a payment gateway to process payments for orders.  The Android app will use the gateway's SDK to securely collect payment information and process transactions.
*   **Push Notification Service (e.g., Firebase Cloud Messaging):**  Integrates with a push notification service to send real-time updates to users regarding their order status and other relevant information.
*   **Location Services (Android Location APIs):**  Uses location services to determine the user's location for delivery address and restaurant search.

## 6. Scalability Strategy

The scalability strategy for the Aşhane application focuses on both the client-side (Android app) and the backend services.

*   **Android App:**
    *   **Efficient UI Design:**  Optimizing UI layouts and image resources to minimize memory consumption and improve performance.
    *   **Background Tasks:**  Using background tasks (e.g., WorkManager) for long-running operations (e.g., uploading images, syncing data) to avoid blocking the main thread.
    *   **Data Caching:**  Implementing data caching mechanisms (using Room or shared preferences) to reduce network requests and improve offline access.
    *   **Code Optimization:**  Profiling the app and optimizing code for performance bottlenecks.
*   **Backend Services:**
    *   **Horizontal Scaling:**  Designing the backend services to be horizontally scalable, allowing for adding more instances of the services to handle increased traffic.
    *   **Load Balancing:**  Using a load balancer to distribute traffic across multiple instances of the backend services.
    *   **Database Optimization:**  Optimizing the database schema and queries for performance, and considering database sharding for very large datasets.
    *   **Caching (Backend):**  Implementing caching mechanisms (e.g., Redis, Memcached) to reduce database load and improve response times.
    *   **Asynchronous Processing:**  Using message queues (e.g., RabbitMQ, Kafka) for asynchronous processing of tasks such as sending emails and generating reports.
    *   **Content Delivery Network (CDN):** Using a CDN to serve static assets (e.g., images, videos) to users from geographically distributed servers, improving performance and reducing latency.

Created on 17.05.2025
```