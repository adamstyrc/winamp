object Versions {
    val min_sdk = 21
    val target_sdk = 28
    val compile_sdk = 28
    val build_tools = "28.0.3"

    const val android_component_room = "1.0.0"
    const val kotlin = "1.3.41"
}

object Libraries {
    val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    val androidx_core = "androidx.core:core-ktx:1.0.2"
    val androidx_appcompat = "androidx.appcompat:appcompat:1.0.0"

    val android_component_room
            = "android.arch.persistence.room:runtime:${Versions.android_component_room}"

}

object GradlePlugins {
    val android = "com.android.tools.build:gradle:3.5.0"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50"
}
