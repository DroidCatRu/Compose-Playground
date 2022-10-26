plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.detekt)
    alias(libs.plugins.junit)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "ru.droidcat.ui"
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
    implementation(libs.composeMaterial3)
    implementation(libs.material)
    implementation(libs.splashScreen)

    testImplementation(libs.bundles.commonTest)
    androidTestImplementation(libs.testAndroidCompose)
    debugImplementation(libs.debugComposeManifest)

    detektPlugins(libs.detektTwitterCompose)
    detektPlugins(libs.detektRulesLibraries)
}