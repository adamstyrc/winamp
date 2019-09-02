object Versions {
    val min_sdk = 21
    val target_sdk = 28
    val compile_sdk = 28
    val build_tools = "28.0.3"

    val kotlin = "1.3.41"
}

object Libraries {
    val kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    val koin_kotlin = "org.koin:koin-core:2.0.1"
    val koin_kotlin_ext = "org.koin:koin-core-ext:2.0.1"
    val koin_android = "org.koin:koin-android:2.0.1"
    val koin_android_ext = "org.koin:koin-android-ext:2.0.1"
    val koin_android_viewmodel = "org.koin:koin-android-viewmodel:2.0.1"

    val androidx_core = "androidx.core:core-ktx:1.0.2"
    val androidx_appcompat = "androidx.appcompat:appcompat:1.0.0"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:1.1.3"
    val androidx_lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata:2.0.0"
    val androidx_lifecycle_extensions = "androidx.lifecycle:lifecycle-extensions:2.0.0"
    val androidx_lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0"

    val android_component_room
            = "android.arch.persistence.room:runtime:1.0.0"
    val google_material = "com.google.android.material:material:1.0.0"


    val rxjava2 = "io.reactivex.rxjava2:rxjava:2.1.0"
    val rxjava2_rxandroid = "io.reactivex.rxjava2:rxandroid:2.0.1"
    val rxjava2_rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.0.2"

    val gson = "com.google.code.gson:gson:2.8.5"

    // TEST LIBRARIES
    val junit = "junit:junit:4.12"
    val robolectric = "org.robolectric:robolectric:4.3"
    val mockito = "org.mockito:mockito-core:2.23.4"
    val mockito_inline = "org.mockito:mockito-inline:2.13.0"
    val androidx_test_core = "androidx.test:core:1.0.0"
    val androidx_test_runner = "androidx.test:runner:1.2.0"
    val androidx_test_rules = "androidx.test:rules:1.2.0"
    val androidx_test_ext_junit = "androidx.test.ext:junit:1.1.0"
    val androidx_test_espresso_core = "androidx.test.espresso:espresso-core:3.2.0"
    val android_arch_core_testing = "android.arch.core:core-testing:1.0.0"

}

object GradlePlugins {
    val android = "com.android.tools.build:gradle:3.5.0"
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}
