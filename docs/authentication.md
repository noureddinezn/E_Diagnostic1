# Authentication Flow

The login flow is handled by `AuthServlet` and `AuthService`.

1. The user submits an email and password from the login page.
2. `AuthService` loads the user by email through `UtilisateurDAO`.
3. `PasswordUtil` verifies the submitted password against the stored hash.
4. The servlet stores the authenticated user in the session.
5. The user is redirected based on their role.

Passwords are verified with BCrypt through the utility layer.
