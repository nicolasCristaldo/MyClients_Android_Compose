# 📋 My Clients

An Android app built with Jetpack Compose that manages clients and their orders. You can add, edit, and delete clients and orders, and track total paid and pending amounts. Uses Room for local storage and Hilt for dependency injection.
<img src="screens/MyClients5.jpeg" alt="Home Screen" width="220"> 
---

## 🚀 Features

- ✅ **Client management**: Add, edit, delete clients with name, email, phone, and address.
- 📦 **Orders tracking**: Add orders per client with description, total amount, and paid status.
- 🔍 **Search & filter**: Quickly find clients and their orders.
- 📊 **Summary views**: Display total paid vs pending amounts for clients and across all orders.

---

## 📸 Screenshots

| Clients List | Add/Edit Client | Client Details |
|--------------|------------------|------------------|
| <img src="screens/MyClients6.jpeg" alt="Clients List" width="220"> | <img src="screens/MyClients1.jpeg" alt="Add Client" width="220"> | <img src="screens/MyClients2.jpeg" alt="Client Details" width="220"> |

| Orders Breakdown | Add/Edit/Delete Order |
|------------------|-------------|
| <img src="screens/MyClients4.jpeg" alt="Orders Overview" width="220"> | <img src="screens/MyClients3.jpeg" alt="Edit Order" width="220"> |

---

## 🧱 Tech Stack

- **UI**: Jetpack Compose
- **Local Database**: Room
- **DI**: Hilt
- **Architecture**: MVVM with ViewModel and StateFlow

---

## 🏛 Architecture

The app follows clean MVVM architecture:
- **UI Layer**: Composable screens observe ViewModel state and render UI.
- **ViewModel**: Handles UI events, business logic, and interacts with the repository.
- **Repository & Room**: Abstracts data storage with queries for clients and their orders.
