## GitHub API Best Practices:

*   **Using `gh api` with file content:** When using the `gh api` command to create or update issues, comments, or other GitHub objects with content from a file, it is important to pass the file's content correctly. Use the `-f body=@filename` syntax to pass the content of the file, not the filename as a string. For example:
    ```bash
    # Incorrect: This will set the body to the string "@filename.md"
    gh api ... -f body="@filename.md"

    # Correct: This will set the body to the content of filename.md
    gh api ... -f body=@filename.md
    ```

---

## Critical Reminders:

*   **`gh api` command:** Always double-check the syntax of the `gh api` command before executing it. Specifically, when passing file content, ensure you are using the `@` symbol correctly and not passing the filename as a string.
