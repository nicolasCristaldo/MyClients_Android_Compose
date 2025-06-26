# Mantener clases generadas por Room y sus métodos
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keep class **_Impl { *; }
-keep class **Dao { *; }
-dontwarn androidx.room.**

# Mantener clases de entidades de Room
-keep class com.nicolascristaldo.myclients.data.room.entities.** { *; }

# Mantener clases de Jetpack Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Mantener clases de Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Mantener clases de ViewModel
-keep class * extends androidx.lifecycle.ViewModel { *; }
-dontwarn androidx.lifecycle.**

# Mantener clases de navegación
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# Mantener clases de Dagger Hilt
-keep class dagger.** { *; }
-keep class **_HiltModules { *; }
-keep class **_HiltComponents { *; }
-dontwarn dagger.**
-keepattributes *Annotation*
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Mantener clases de recursos generados (R)
-keep class com.nicolascristaldo.myclients.R { *; }
-keep class com.nicolascristaldo.myclients.R$* { *; }

# Mantener clases de dominio y modelos de datos
-keep class com.nicolascristaldo.myclients.domain.model.** { *; }

# Mantener clases de los providers
-keep class com.nicolascristaldo.myclients.data.providers.** { *; }

# Reglas generales para Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class ** {
    @kotlin.Metadata *;
}

# Mantener métodos de reflexión usados por Kotlin
-keepclassmembers class ** {
    public static ** companion;
}

# Mantener clases de Flow de Kotlin
-keep class kotlinx.coroutines.flow.** { *; }

# Evitar advertencias para clases de AndroidX
-dontwarn androidx.**

# Mantener atributos necesarios para reflexión y depuración
-keepattributes SourceFile,LineNumberTable
-keepattributes Signature
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Evitar optimizaciones agresivas que puedan romper la app
-dontoptimize
-dontshrink

# Mantener clases de tu aplicación que podrían ser accedidas por reflexión
-keep class com.nicolascristaldo.myclients.** { *; }