# Best Fit Memory Allocation Project

This project demonstrates the **Best Fit Memory Allocation** algorithm, a memory management technique used in operating systems to allocate processes to memory blocks efficiently. The project is implemented in **Java** with **MySQL** integration for logging allocation results.

---

## Features

- Implements the **Best Fit** algorithm to allocate processes to the smallest available memory blocks.
- Handles unallocated processes and logs them appropriately.
- Integrates with a **MySQL database** to store allocation results for analysis.
- Provides a modular and scalable code structure using design patterns like **Singleton** and **Factory**.
- Allows dynamic user input for memory blocks and processes.
- Outputs results in the console and logs them persistently in the database.

---

## Requirements

- **Operating System:** Windows, macOS, or Linux
- **Java Development Kit (JDK):** Version 8 or above
- **MySQL Server:** Version 5.7 or above
- **IDE (Optional):** IntelliJ IDEA, Eclipse, or any Java-supported IDE
- **Maven:** For dependency management

---

## Installation

### 1. Clone the Repository
```bash
https://github.com/TeamSarose/Best_Fit_Algorithm.git
```

---

### 2. Set Up the MySQL Database
- Create a database named **memory_allocation**.
- Run the following SQL script to create the required table
```bash
CREATE TABLE MemoryAllocationLog (
    id INT AUTO_INCREMENT PRIMARY KEY,
    process_id INT NOT NULL,
    process_size INT NOT NULL,
    block_id INT DEFAULT NULL,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

---

### 3. Configure the Project
- Update the **DatabaseConnection** class with your MySQL username and password:
```bash
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/memory_allocation", "root", "your_password"
);
```

---

### 4. Build and Run the Program



