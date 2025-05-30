# 🛍️ Bidding System – Java EE Enterprise Application

A full-featured enterprise-grade **online bidding system** built using **Java EE technologies**, designed for scalability and modularity.

---

## ✨ Tech Stack

- **Java EE** (JSP, Servlets, EJB, JMS, JPA)  
- **Payara** Application Server  
- **MySQL** Database  
- **JMS** (Java Message Service) for event-driven messaging  
- **Maven** for project management  
- **EAR** packaging structure (Core, Web, EJB, EAR modules)

---

## 📦 Modules

- `core`: Domain models and shared utilities  
- `web`: JSP/Servlet-based frontend and controllers  
- `ejb`: Business logic with session beans  
- `ear`: Aggregated deployable archive  

---

## 📚 Features

- User registration & login  
- Browse items & place bids  
- Countdown timer for each item  
- Background auction handling with EJB timers / JMS  
- Admin module for item management  

---

## 🚀 How to Run

1. Clone the repository  
2. Import the project into NetBeans or IntelliJ as a Maven EAR project  
3. Configure your MySQL database connection  
4. Deploy the EAR to GlassFish Server  

---

> ✅ **Educational Use:** This project demonstrates real-world use of Java EE technologies in a multi-tier architecture.  
> 🧠 Built as part of a university project. Contributions and suggestions are welcome!
