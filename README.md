# Final Year Project Management System

## Overview
This project is designed to streamline the management of final-year projects for university students. It connects students, faculty members, supervisors, committee members, and coordinators, enabling efficient communication and task management. The system allows students to form groups, propose projects, select projects, and interact with supervisors and committees. Faculty members, supervisors, and coordinators can manage projects, evaluate proposals, and schedule presentations, all within a centralized platform.

## Key Features
- **Student Functionalities**:
    - Group formation by sending and accepting group requests.
    - Propose project ideas to supervisors.
    - Select projects from a list of available projects.
    - View project status, supervising committee, and group members.
- **Supervisor Functionalities**:
    - Approve or reject project proposals.
    - Manage student requests for project selection.
    - Participate in evaluation committees.
- **Faculty Functionalities**:
    - Register as a faculty member.
    - Participate in committees for project evaluation.
- **Coordinator Functionalities**:
    - Create and manage evaluation committees.
    - Schedule presentations and assign evaluators.
    - Approve or reject proposed projects.

## Technologies Used
- **Programming Language**: Java (Object-Oriented Programming concepts).
- **Database**: MySQL for data storage and management.
- **GUI Library**: Java Swing for creating the user interface.

## Prerequisites
Before running the project, ensure you have the following:
1. **Java Development Kit (JDK)** installed on your system.
2. **MySQL** installed and running. Create a database and configure the connection details in the project.
3. **MySQL Connector/J JDBC Driver**: Download the JDBC connector JAR file from the [official MySQL website](https://dev.mysql.com/downloads/connector/j/).

## Setup Instructions
1. **Download the JDBC Connector**:
    - Visit the [MySQL Connector/J download page](https://dev.mysql.com/downloads/connector/j/).
    - Download the JAR file compatible with your MySQL version.
    - Add the JAR file to your project's libraries:
        - In **IntelliJ IDEA**: Go to `File > Project Structure > Libraries`, then add the JAR file.
        - In **NetBeans**: Right-click on the project, select `Properties > Libraries > Add JAR/Folder`, and add the JAR file.

2. **Database Setup**:
    - Create a MySQL database of your desired name.
    - Copy the provided SQL queries in the created `.sql` file.
    - Run the provided SQL scripts to create the necessary tables and insert sample data.

3. **Run the Project**:
    - Open the project in your preferred IDE (IntelliJ IDEA or NetBeans).
    - Ensure the JDBC connector is added to the project libraries.
    - Run the main class to start the application.

## Conclusion
This Final Year Project Management System simplifies the process of managing university projects, making it more organized, transparent, and efficient. It serves as a robust tool for students, faculty, supervisors, and coordinators.

For any issues or questions, feel free to reach out to me at my [email address](muhammadnoman.cs@gmail.com).