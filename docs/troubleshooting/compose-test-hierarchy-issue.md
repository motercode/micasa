# Problema: `java.lang.IllegalStateException: No compose hierarchies found in the app`

## El Problema

Al ejecutar una prueba de instrumentación de Jetpack Compose con `createComposeRule` o `createAndroidComposeRule`, la prueba falla con el siguiente error:

```
java.lang.IllegalStateException: No compose hierarchies found in the app. Possible reasons include: (1) the Activity that calls setContent did not launch; (2) setContent was not called; (3) setContent was called before the ComposeTestRule ran. If setContent is called by the Activity, make sure the Activity is launched after the ComposeTestRule runs
```

Esto ocurre a pesar de que el código de la aplicación y de la prueba parecen ser correctos.

## La Causa Raíz

La causa más probable de este problema es una de las siguientes:

1.  **Dependencias incorrectas:** Faltan o están mal configuradas las dependencias de prueba de Compose en el archivo `build.gradle.kts`.
2.  **Entorno de ejecución:** El emulador o dispositivo físico donde se ejecuta la prueba tiene algún problema de configuración o estado que impide que la `Activity` se lance correctamente.
3.  **Configuración del `testInstrumentationRunner`:** El `testInstrumentationRunner` en `build.gradle.kts` no está configurado correctamente.
4.  **Problema con Hilt:** Si se usa Hilt, podría haber un problema con la inyección de dependencias en el entorno de prueba.

## La Solución

Para resolver este problema, se seguirán los siguientes pasos:

1.  **Verificar dependencias:** Asegurarse de que las siguientes dependencias están presentes en `app/build.gradle.kts`:
    ```kotlin
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    ```
2.  **Limpiar el proyecto:** Ejecutar `./gradlew clean` para eliminar cualquier artefacto de compilación obsoleto.
3.  **Probar en un dispositivo diferente:** Si es posible, ejecutar las pruebas en un emulador o dispositivo físico diferente para descartar problemas de entorno.
4.  **Revisar la configuración de Hilt para pruebas:** Si se usa Hilt, es necesario configurar un `HiltTestApplication` y un `CustomTestRunner` para las pruebas de instrumentación.

En este caso, dado que las dependencias parecen correctas, el siguiente paso es limpiar el proyecto y volver a intentarlo. Si eso no funciona, se investigará la configuración de Hilt para las pruebas.
