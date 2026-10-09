# Security Notes

The project uses BCrypt for password verification.

Recommended follow-up improvements:

- Move database credentials out of `hibernate.cfg.xml`.
- Disable verbose SQL logging outside development.
- Add servlet filters for role-based access control.
- Validate and normalize request parameters before persistence.
- Add tests around authentication and role routing.
