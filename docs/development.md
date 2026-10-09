# Development Workflow

Use small commits with clear English messages.

Suggested local workflow:

```bash
git status
mvn clean package
git add <files>
git commit -m "Describe the completed task"
```

Generated build files under `target` should stay out of version control.
