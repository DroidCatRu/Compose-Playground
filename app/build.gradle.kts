plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.detekt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.junit)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktLint)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlinKapt)
}

android {
    namespace = "ru.droidcat.playground"
    compileSdk =  33

    defaultConfig {
        applicationId = "ru.droidcat.playground"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    packagingOptions {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.material)
    implementation(libs.splashScreen)

    implementation(libs.bundles.common)
    implementation(libs.roomKtx)

    testImplementation(libs.bundles.commonTest)
    androidTestImplementation(libs.testAndroidCompose)
    androidTestImplementation(libs.testAndroidCore)
    androidTestImplementation(libs.testAndroidHilt)
    androidTestImplementation(libs.testAndroidRunner)

    kapt(libs.hiltCompiler)
    ksp(libs.roomCompiler)
    kaptAndroidTest(libs.testAndroidHiltCompiler)

    coreLibraryDesugaring(libs.desugar)

    detektPlugins(libs.detektTwitterCompose)
}