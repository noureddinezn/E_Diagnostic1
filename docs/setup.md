# Setup Guide

## Requirements

- Java 17
- Maven
- MySQL
- Apache Tomcat 10 or another Jakarta Servlet 6 compatible container

## Database

Create a MySQL database named `e_diagnastic1`, then import the SQL script from:

```text
demo/src/main/resources/E_Diagnastic1.sql
```

Update the database connection in:

```text
demo/src/main/resources/hibernate.cfg.xml
```

## Build

Run the Maven package command from the `demo` directory:

```bash
mvn clean package
```

The generated WAR file is created under `demo/target`.
