# Service Layer

Service classes hold application business behavior between servlets and DAOs.

Current services:

- `AuthService`
- `InfirmierService`
- `GeneralisteService`
- `SpecialisteService`

This layer keeps servlet code focused on HTTP concerns while allowing business rules to stay reusable.
