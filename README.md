# E Diagnostic

E Diagnostic is a Java web application for coordinating medical consultations between nurses, general practitioners, and specialists. The project uses Maven, Jakarta Servlet APIs, CDI, Hibernate ORM, and MySQL.

## Main Modules

- Authentication for application users.
- Nurse workspace for patient intake.
- General practitioner workspace for consultations and expertise requests.
- Specialist workspace for expertise follow-up.
- Hibernate-based persistence for users, patients, consultations, slots, prescriptions, and expertise requests.

## Project Layout

- `demo/pom.xml` defines the Maven WAR project.
- `demo/src/main/java` contains controllers, services, DAOs, models, and utilities.
- `demo/src/main/resources` contains Hibernate and database resources.
- `demo/src/main/webapp` contains JSP pages and web deployment descriptors.
