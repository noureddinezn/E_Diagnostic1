# Database Notes

The project uses MySQL with the database name `e_diagnastic1`.

Hibernate is configured with:

- MySQL JDBC driver
- MySQL dialect
- SQL logging enabled
- automatic schema update mode

For production deployments, review the database credentials and consider replacing automatic schema updates with managed migrations.
