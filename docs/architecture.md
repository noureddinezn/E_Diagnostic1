# Architecture Overview

The application follows a classic layered structure:

- JSP pages render the web interface.
- Servlets handle HTTP requests and route user actions.
- Services contain business logic.
- DAOs isolate database operations.
- Hibernate maps Java entities to database tables.

This separation keeps request handling, business decisions, and persistence code easier to maintain independently.
