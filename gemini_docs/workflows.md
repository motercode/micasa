## New Project Creation Workflow:

1.  **Ask for Project Name:** I will ask the user for the name of the new project.
2.  **Create GitHub Repository:** I will use the `gh` CLI to create a new public repository under the `motercode` username and clone it locally. The command will be: `gh repo create motercode/<project-name> --public --clone`

---


## Logcat Issue Resolution Workflow:

1.  **Issue Creation:** When you ask me to solve an issue from the `@logcat` folder, I will first create a new issue on GitHub. The issue will contain the relevant logcat output and a summary of the problem.
2.  **Assign Issue:** I will assign the newly created issue to "motercode".
3.  **Branching:** I will create a new branch from `main` with a descriptive name that includes the issue number (e.g., `fix/issue-123-camera-permission`).
4.  **Commits:** I will create atomic commits for each logical change. Each commit message will reference the issue number (e.g., "Fix #123: Request camera permission at runtime").
5.  **Merging:** Once the issue is resolved and the build is successful, I will merge the feature branch back into `main` using a squash merge.
6.  **Branch Deletion:** After the merge, I will delete the feature branch.
7.  **Comment on Issue:** Before closing the issue, I will add a comment summarizing the plan, the actions taken, and the final result.
8.  **Issue Closing:** Finally, I will close the GitHub issue.

---

## FEAT Workflow

When a prompt starts with the word "FEAT", the following workflow will be triggered:

**Note:** All issue-related operations will be performed using the `gh api` command.

1.  **Issue Creation:** An issue will be created on GitHub. The title of the issue will be a summary of the feature request.
2.  **Assign Issue:** The issue will be assigned to "motercode".
3.  **Development Plan:** A comment will be added to the issue outlining the development plan for the feature.
4.  **Branching:** A new branch will be created from `main` with a descriptive name (e.g., `feat/issue-123-new-feature`).
5.  **Development:** The feature will be developed following best practices.
6.  **Pull Request:** A pull request will be created to merge the feature branch into `main`.
7.  **Merging:** The pull request will be merged using a squash merge.
8.  **Branch Deletion:** The feature branch will be deleted after the merge.
9.  **Final Summary:** Before closing the issue, a comprehensive summary will be posted as a comment. This summary will include:
    *   A summary of the development process.
    *   A summary of any problems encountered and their resolutions.
    *   A list of any pending topics.
    *   A list of any known errors.
10. **Issue Closing:** The issue will be closed.

---

## BUG Workflow

When a prompt starts with the word "BUG", the following workflow will be triggered:

**Note:** All issue-related operations will be performed using the `gh api` command.

1.  **Issue Creation:** An issue will be created on GitHub. The title of the issue will be a summary of the bug report.
2.  **Assign Issue:** The issue will be assigned to "motercode".
3.  **Development Plan:** A comment will be added to the issue outlining the plan to fix the bug.
4.  **Branching:** A new branch will be created from `main` with a descriptive name (e.g., `fix/issue-123-bug-fix`).
5.  **Development:** The bug will be fixed following best practices.
6.  **Pull Request:** A pull request will be created to merge the feature branch into `main`.
7.  **Merging:** The pull request will be merged using a squash merge.
8.  **Branch Deletion:** The feature branch will be deleted after the merge.
9.  **Final Summary:** Before closing the issue, a comprehensive summary will be posted as a comment. This summary will include:
    *   A summary of the development process.
    *   A summary of any problems encountered and their resolutions.
    *   A list of any pending topics.
    *   A list of any known errors.
10. **Issue Closing:** The issue will be closed.

---

## Gradle Issue Resolution Workflow:

1.  **Branching:** For each Gradle issue (or a set of related issues), I will create a new branch from `main` with a descriptive name (e.g., `fix/gradle-deprecation-warning`).
2.  **Issue Summary:** In this new branch, I will create a file named `issue_summary.md` that contains a summary of the Gradle issue(s) and the plan to resolve them.
3.  **Commits:** I will create atomic commits for each logical change. This might be one or more commits, depending on the complexity of the issue. Each commit message will be clear and descriptive.
4.  **Merging:** Once the issue is resolved and the build is successful, I will merge the feature branch back into `main` using a squash merge. This will combine all the commits from the feature branch into a single, clean commit on the `main` branch.
5.  **Branch Deletion:** After the merge, I will delete the feature branch.

---

## Room and Kapt Compatibility Issues

When working with Room and Kapt, I have encountered the following issues:

### 1. `TypeConverter` Resolution Issues

**Problem:** Kapt may fail to resolve `TypeConverter` classes, resulting in a `cannot figure out how to save this field into database` error, even if the converters are correctly defined.

**Solution:**

*   **Ensure `@TypeConverters` is on the `AppDatabase` class:** The `@TypeConverters` annotation must be present on the `AppDatabase` class, not just on the entities.
*   **Use separate classes for each converter:** Instead of nesting multiple converters in a single class, define each converter in its own file. This helps Kapt to resolve them correctly.
*   **Remove `@TypeConverters` from entities:** Once the converters are defined in the `AppDatabase` class, there is no need to add the `@TypeConverters` annotation to the entities.

**Example:**

**Incorrect:**

```kotlin
// Converters.kt
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? { ... }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? { ... }
}

// SessionEntity.kt
@Entity
@TypeConverters(Converters::class)
data class SessionEntity(...)

// AppDatabase.kt
@Database(...)
abstract class AppDatabase : RoomDatabase() { ... }
```

**Correct:**

```kotlin
// DateConverter.kt
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? { ... }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? { ... }
}

// SessionEntity.kt
@Entity
data class SessionEntity(...)

// AppDatabase.kt
@Database(...)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() { ... }
```