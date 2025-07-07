## Git Development Best Practices:

* **Branching Strategy:**
    * Use a `main` branch for production-ready code.
    * Create feature branches from `main` for new features or bug fixes (e.g., `feature/new-login-flow`, `bugfix/fix-crash-on-startup`).
    * Use descriptive branch names.
* **Commits:**
    * Make small, atomic commits that represent a single logical change.
    * Write clear and concise commit messages. Use the imperative mood (e.g., "Add user authentication" instead of "Added user authentication").
    * Reference issue numbers in commit messages (e.g., "Fix #42: Add validation to the login form").
* **Pull Requests (PRs):**
    * Create PRs to merge feature branches into `main`.
    * Write a clear description of the changes in the PR.
    * Request reviews from other developers.
    * Ensure that the code is well-tested and that all tests pass before merging.
* **Code Reviews:**
    * Be respectful and constructive in your feedback.
    * Focus on the code, not the person.
    * Provide specific suggestions for improvement.
* **Squash Merging a Feature Branch:**
    1. Ensure the feature branch is up-to-date: `git checkout <feature-branch>` and `git pull`.
    2. Switch to the target branch: `git checkout main`.
    3. Perform the squash merge (this stages the changes): `git merge --squash <feature-branch>`.
    4. **Crucially, create the commit:** `git commit -m "feat: A clear, concise summary of the feature"`.
    5. Clean up the now-merged branch: `git branch -D <feature-branch>`.
* **Keeping the Repository Clean:**
    * Use a `.gitignore` file to exclude unnecessary files from the repository (e.g., build artifacts, log files, IDE configuration files).
    * Regularly prune stale branches.
    * Use `git rebase` to keep your feature branches up-to-date with `main` and to create a clean, linear history.
* **General:**
    * Pull the latest changes from the remote repository before starting new work.
    * Use `git stash` to temporarily save changes that are not ready to be committed.
    * Use `git log` to view the commit history.
    * Use `git blame` to see who last modified a line of code.
    * Use `git bisect` to find the commit that introduced a bug.