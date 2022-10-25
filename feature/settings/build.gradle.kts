plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "ru.droidcat.settings"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {

    implementation(libs.bundles.common)
    implementation(libs.coil)
    implementation(libs.composeAccompanistSwipeRefresh)
    implementation(libs.composeNavigationHilt)
    implementation(libs.kotlinSerialization)
    implementation(libs.retrofit)
    implementation(libs.room)
    testImplementation(libs.bundles.commonTest)
    androidTestImplementation(libs.testAndroidCompose)
    debugImplementation(libs.debugComposeManifest)

    kapt(libs.hiltCompiler)

    detektPlugins(libs.detektTwitterCompose)
}