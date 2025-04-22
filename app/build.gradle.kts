/*
 * build.gradle.kts - Module app
 * Created by Ulises Gonzalez
 * Copyright (c) 2025. All rights reserved
 */
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.com.google.hilt)
    alias(libs.plugins.com.google.services)
    alias(libs.plugins.com.google.crashlytics)
    alias(libs.plugins.com.google.ksp)
}

android {
    namespace = BuildConfig.APP_NAMESPACE
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        resValue(
            type = "string",
            name = "APP_NAME",
            value = "\"${properties["app.name"]}\"",
        )

        buildConfigField(
            type = "String",
            name = "DATABASE_NAME",
            value = "\"${properties["database.name"]}\"",
        )

        buildConfigField(
            type = "String",
            name = "PREFERENCES_NAME",
            value = "\"${properties["preferences.name"]}\"",
        )

        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = ReleaseConfig.VERSION_CODE
        versionName = ReleaseConfig.VERSION_NAME
        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName(BuildTypes.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            isMinifyEnabled = Build.Release.isMinifyEnabled
            isShrinkResources = Build.Release.isShrinkResources
            isDebuggable = Build.Release.enableUnitTestCoverage
            enableUnitTestCoverage = Build.Release.isDebuggable
        }
        getByName(BuildTypes.DEBUG) {
            isMinifyEnabled = Build.Debug.isMinifyEnabled
            isDebuggable = Build.Debug.isDebuggable
            enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
        }
    }

    flavorDimensions += "version"
    productFlavors {
        create(FlavorTypes.FREE) {
            dimension = "version"
        }
        create(FlavorTypes.PAY) {
            dimension = "version"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {
    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.bundles.androidx.lifecycle.libs)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.bundles.org.coroutines.libs)
    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    // Libs
    implementation(libs.androidx.splashscreen)
    implementation(libs.material)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlin.serialization)
    implementation(libs.konfetti.compose)
    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    // Hilt
    implementation(libs.bundles.com.google.hilt.libs)
    ksp(libs.com.google.hilt.compiler)
    // Firebase
    implementation(libs.bundles.com.firebase.libs)
    // Room
    implementation(libs.bundles.androidx.room.libs)
    ksp(libs.androidx.room.compiler)
    // DataStore
    implementation(libs.bundles.androidx.datastore.libs)
    // Test
    testImplementation(libs.junit)
    testImplementation(libs.com.google.hilt.testing)
    testImplementation(libs.bundles.org.mockito.libs)
    testImplementation(libs.org.robolectric)
    testImplementation(libs.org.coroutines.test)
    // AndroidTest
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
