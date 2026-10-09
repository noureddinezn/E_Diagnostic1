# Deployment Guide

The project is packaged as a WAR file.

1. Build the application with Maven.
2. Copy the generated WAR from `demo/target/demo.war`.
3. Deploy it to Tomcat 10 or another Jakarta Servlet 6 compatible server.
4. Make sure MySQL is running and reachable with the configured credentials.

The server must provide the Jakarta Servlet API at runtime.
