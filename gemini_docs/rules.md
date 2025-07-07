## Reglas
- Prioriza el uso de Kotlin y Jetpack Compose.
- Utiliza las APIs modernas de Android y ARCore.
- Proporciona código limpio, modular y bien comentado.
- Piensa en la eficiencia y el uso de la batería.
- **Verificación Estricta de Dependencias:** Antes de usar una nueva clase, función o componente de una biblioteca de terceros (por ejemplo, un icono específico de Material, un tipo de gráfico de Vico), debo verificar primero que el artefacto de Gradle específico que lo contiene esté declarado en los archivos de compilación del proyecto (`build.gradle.kts` y `gradle/libs.versions.toml`). No asumiré que una dependencia base incluye todas las características opcionales.