plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("androidx.navigation.safeargs.kotlin")
    id("org.jetbrains.kotlin.kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
    id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.example.facturas_tfc"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.facturas_tfc"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    viewBinding{
        enable = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation (libs.retromock)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation (libs.firebase.auth)
    implementation (libs.androidx.lifecycle.viewmodel.ktx.v240)
    implementation (libs.androidx.lifecycle.livedata.ktx.v240)
    implementation (libs.androidx.lifecycle.common.java8)
//noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler.v250)
    implementation (libs.firebase.config.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    testImplementation(libs.junit.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // SECURED SHARED PREFERENCES
    implementation(libs.androidx.security.crypto)
    // MOCKITO
    testImplementation(libs.mockito.core)
    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.junit.jupiter)
    //HILT
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
}