# Project Name: OTT Demo

## Overview
This project is a simple demo of movie list and streaming. It demonstrates the use of various tools and libraries to build a custom movie filtering. 

---

## Tools & Libraries Used

### 1. **Kotlin**
   - Kotlin is the primary programming language used for this Android project. It ensures concise, safe, and modern syntax for building the app.

### 2. **Android Jetpack Components**
   - **LiveData** & **ViewModel**: Used for managing UI-related data in a lifecycle-conscious way.
   
### 3. **Hilt (Dependency Injection)**
   - Hilt is used to simplify dependency injection and manage the app's dependencies in a scalable and maintainable way.

### 4. **Retrofit**
   - Used for making API calls to the backend server, enabling HTTP requests like `GET`, `POST`, etc.

### 5. **Paging 3**
   - The Paging 3 library is used to efficiently load large datasets in chunks, handling pagination in a memory-efficient way.

### 6. **Coroutines**
   - Kotlin Coroutines are used to handle asynchronous tasks in a more efficient and readable manner.

### 7. **Material Design**
   - Google’s Material Design components are used to build a responsive, modern UI.

### 8. **Unit Testing (JUnit, Mockito, MockWebServer)**
   - JUnit and Mockito are used for unit testing. MockWebServer is used for mocking API responses during testing.

---

## Project Architecture

The project follows the **MVVM (Model-View-ViewModel)** architecture, promoting a clear separation of concerns between the UI and data layers.

- **Model**: Represents the data layer, including network calls (via Retrofit), local database (via Room), and data models.
- **View**: Represents the UI layer, which includes activities and fragments responsible for displaying the data.
- **ViewModel**: Handles the business logic and acts as a bridge between the Model and View. It is responsible for making network calls and exposing data via LiveData.
- **Repository**: Handles data management from different sources like network and local storage.

Additionally, the **Clean Architecture** principles have been followed to ensure maintainability, scalability, and testability.

---

## Setup Guide

To get started with this project, follow these steps:

### Prerequisites:
- Android Studio (Jellyfish or Higher)
- Kotlin 1.5 or higher
- A GitHub account (if you want to clone the repository)

### Steps to Run the Project:

 **Clone the repository**
   Clone this repository to your local machine:

   
