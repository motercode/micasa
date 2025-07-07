# Android Project Initialization Workflow

This document outlines the standardized process for creating a new, modern Android project from scratch. It includes repository creation, dependency configuration, and a detailed troubleshooting guide for common initial build errors.

---

## 1. GitHub Repository Creation and Synchronization

The first step is to synchronize the local project with its remote repository on GitHub under the `motercode` organization.

1.  **Prompt for Repository Name:** Ask the user for the desired name for the GitHub repository.
    *   **User Input:** e.g., `desktrack`

2.  **Check for Existing Repository:** Before taking any action, check if a repository with the given name already exists in the `motercode` organization.
    ```bash
    gh repo view motercode/<repository-name>
    ```

3.  **Action Based on Existence:**

    *   **If the Repository Exists:** If the command above succeeds, the repository already exists. The local project must be synchronized with the remote.
        - **For a new local setup:** Clone the repository into the current empty directory.
          ```bash
          git clone https://github.com/motercode/<repository-name>.git .
          ```
        - **For an existing local project:** Connect the remote and pull the latest changes, handling any merge conflicts.
          ```bash
          git init # if not already a repo
          git remote add origin https://github.com/motercode/<repository-name>.git
          git fetch origin
          git pull origin main --allow-unrelated-histories # Or the appropriate default branch
          ```

    *   **If the Repository Does NOT Exist:** If the `gh repo view` command fails with a 404 error, proceed to create it from the local project.
        - **Initialize a local Git repository:**
          ```bash
          git init
          ```
        - **Add all existing files to staging:**
          ```bash
          git add .
          ```
        - **Create an initial commit:**
          ```bash
          git commit -m "Initial commit"
          ```
        - **Create the GitHub repository and push the initial commit:**
          ```bash
          gh repo create motercode/<repository-name> --public --source=. --remote=origin && git push -u origin main
          ```
        > **Note:** If the `gh repo create` command fails, create the repository manually on the GitHub website and then run the following commands to connect it:
        > ```bash
        > git remote add origin https://github.com/motercode/<repository-name>.git
        > git push -u origin main
        > ```

---

## 2. Dependency and Tech Stack Configuration

Configure the Gradle build files to include the standard tech stack.

### Tech Stack:
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Architecture:** MVVM
- **Asynchrony:** Coroutines & Flows
- **Database:** Room
- **Dependency Injection:** Hilt
- **Location:** Google Play Services
- **Charts:** Vico

### Steps:

1.  **Update `gradle/libs.versions.toml`:** Add the versions and library definitions for all dependencies.

    ```toml
    [versions]
    agp = "8.11.0"
    kotlin = "2.0.21"
    coreKtx = "1.16.0"
    junit = "4.13.2"
    junitVersion = "1.2.1"
    espressoCore = "3.6.1"
    lifecycleRuntimeKtx = "2.9.1"
    activityCompose = "1.10.1"
    composeBom = "2024.09.00"
    hilt = "2.51.1"
    room = "2.6.1"
    navigationCompose = "2.7.7"
    playServicesLocation = "21.3.0"
    vico = "2.0.0-alpha.21"
    lifecycleViewModelCompose = "2.9.1"
    kotlinxCoroutinesAndroid = "1.8.1"

    [libraries]
    androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
    junit = { group = "junit", name = "junit", version.ref = "junit" }
    androidx-junit = { group = "androidx.test.ext", name = "junit", version.ref = "junitVersion" }
    androidx-espresso-core = { group = "androidx.test.espresso", name = "espresso-core", version.ref = "espressoCore" }
    androidx-lifecycle-runtime-ktx = { group = "androidx.lifecycle", name = "lifecycle-runtime-ktx", version.ref = "lifecycleRuntimeKtx" }
    androidx-lifecycle-viewmodel-compose = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-compose", version.ref = "lifecycleViewModelCompose" }
    androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
    androidx-compose-bom = { group = "androidx.compose", name = "compose-bom", version.ref = "composeBom" }
    androidx-ui = { group = "androidx.compose.ui", name = "ui" }
    androidx-ui-graphics = { group = "androidx.compose.ui", name = "ui-graphics" }
    androidx-ui-tooling = { group = "androidx.compose.ui", name = "ui-tooling" }
    androidx-ui-tooling-preview = { group = "androidx.compose.ui", name = "ui-tooling-preview" }
    androidx-ui-test-manifest = { group = "androidx.compose.ui", name = "ui-test-manifest" }
    androidx-ui-test-junit4 = { group = "androidx.compose.ui", name = "ui-test-junit4" }
    androidx-material3 = { group = "androidx.compose.material3", name = "material3" }

    # Hilt
    hilt-android = { group = "com.google.dagger", name = "hilt-android", version.ref = "hilt" }
    hilt-compiler = { group = "com.google.dagger", name = "hilt-android-compiler", version.ref = "hilt" }
    hilt-navigation-compose = { group = "androidx.hilt", name = "hilt-navigation-compose", version = "1.2.0" }

    # Room
    room-runtime = { group = "androidx.room", name = "room-runtime", version.ref = "room" }
    room-compiler = { group = "androidx.room", name = "room-compiler", version.ref = "room" }
    room-ktx = { group = "androidx.room", name = "room-ktx", version.ref = "room" }

    # Navigation
    androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }

    # Play Services
    play-services-location = { group = "com.google.android.gms", name = "play-services-location", version.ref = "playServicesLocation" }

    # Vico
    vico-compose = { group = "com.patrykandpatrick.vico", name = "compose", version.ref = "vico" }
    vico-compose-m3 = { group = "com.patrykandpatrick.vico", name = "compose-m3", version.ref = "vico" }
    vico-core = { group = "com.patrykandpatrick.vico", name = "core", version.ref = "vico" }

    # Coroutines
    kotlinx-coroutines-android = { group = "org.jetbrains.kotlinx", name = "kotlinx-coroutines-android", version.ref = "kotlinxCoroutinesAndroid" }

    [plugins]
    android-application = { id = "com.android.application", version.ref = "agp" }
    kotlin-android = { id = "org.jetbrains.kotlin.android", version.ref = "kotlin" }
    kotlin-compose = { id = "org.jetbrains.kotlin.plugin.compose", version.ref = "kotlin" }
    hilt-gradle = { id = "com.google.dagger.hilt.android", version.ref = "hilt" }
    kotlin-kapt = { id = "org.jetbrains.kotlin.kapt", version = "2.0.21" }
    ```

2.  **Update Project `build.gradle.kts`:** Apply the Hilt plugin.

    ```kotlin
    plugins {
        alias(libs.plugins.android.application) apply false
        alias(libs.plugins.kotlin.android) apply false
        alias(libs.plugins.kotlin.compose) apply false
        alias(libs.plugins.hilt.gradle) apply false
    }
    ```

3.  **Update App Module `build.gradle.kts`:** Apply plugins and add dependencies.

    ```kotlin
    plugins {
        alias(libs.plugins.android.application)
        alias(libs.plugins.kotlin.android)
        alias(libs.plugins.kotlin.compose)
        alias(libs.plugins.hilt.gradle)
        id("org.jetbrains.kotlin.kapt")
    }

    // ... android block ...

    dependencies {
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)
        implementation(libs.androidx.lifecycle.viewmodel.compose)
        implementation(libs.androidx.activity.compose)
        implementation(platform(libs.androidx.compose.bom))
        // ... other compose dependencies

        // Hilt
        implementation(libs.hilt.android)
        kapt(libs.hilt.compiler)
        implementation(libs.hilt.navigation.compose)

        // Room
        implementation(libs.room.runtime)
        kapt(libs.room.compiler)
        implementation(libs.room.ktx)

        // ... other dependencies
    }
    ```

---

## 3. Hilt Application Setup

1.  **Create Application Class:** Create a file `src/main/java/com/example/project/ProjectApplication.kt` and annotate it with `@HiltAndroidApp`.

    ```kotlin
    package com.example.desktrack

    import android.app.Application
    import dagger.hilt.android.HiltAndroidApp

    @HiltAndroidApp
    class DeskTrackApplication : Application()
    ```

2.  **Update `AndroidManifest.xml`:** Add the `android:name` attribute to the `<application>` tag.

    ```xml
    <application
        android:name=".DeskTrackApplication"
        ...>
    </application>
    ```

3.  **Annotate `MainActivity.kt`:** Add the `@AndroidEntryPoint` annotation to the `MainActivity` class.

---

## 4. Build and Troubleshoot

Run a clean build and resolve any issues that arise.

```bash
./gradlew clean build
```

### Common Initial Build Errors & Solutions:

*   **Error: `kotlin-kapt` Plugin Conflict**
    *   **Symptom:** Build fails with "Error resolving plugin... already on the classpath".
    *   **Solution:** The `kotlin-kapt` plugin must be applied **only** in the app-level `build.gradle.kts` file using `id("org.jetbrains.kotlin.kapt")`. Do **not** apply it in the root project's `build.gradle.kts`. Ensure the version is defined in `libs.versions.toml`.

*   **Error: `AndroidManifest.xml` Parsing Error**
    *   **Symptom:** Build fails with `ManifestMerger2$MergeFailureException`.
    *   **Cause:** Often caused by a duplicated `<application>` tag during manual edits.
    *   **Solution:** Inspect `AndroidManifest.xml` and remove the extra `<application` line.

*   **Error: Resource `style/Theme.ProjectName` Not Found**
    *   **Symptom:** Build fails with `AAPT: error: resource style/Theme.ProjectName not found`.
    *   **Cause:** A mismatch between the theme name defined in `ui/theme/Theme.kt` (e.g., `DesktrackTheme`) and the name referenced in `AndroidManifest.xml` (e.g., `@style/Theme.DeskTrack`). The auto-generated name in the manifest often includes a dot that is not present in the Compose theme definition.
    *   **Solution:** Correct the `android:theme` attribute in `AndroidManifest.xml` to match the actual theme name (e.g., `@style/Theme.Desktrack`).

---

By following this workflow, a new project can be rapidly and reliably configured to a buildable state with the complete, modern tech stack.
