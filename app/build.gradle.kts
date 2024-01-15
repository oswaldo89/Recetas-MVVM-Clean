import dependencies.Deps
import scripts.validateLayoutDimensions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.recetasyape.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.recetasyape.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        enable = true
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.playServicesMaps)
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExt)
    androidTestImplementation(Deps.espressoCore)
    implementation(Deps.floatingSearchView)
    implementation(Deps.coreSplashScreen)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.okHttpLoggingInterceptor)
    implementation(Deps.daggerHilt)
    kapt(Deps.daggerHiltCompiler)
    implementation(Deps.lifecycleLiveDataKtx)
    implementation(Deps.lifecycleViewModelKtx)
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)
    implementation(Deps.glide)
    kapt(Deps.glideCompiler)
}

tasks.register("validateLayoutDimensions") {
    doLast {
        validateLayoutDimensions(project)
    }
}

tasks.named("preBuild") {
    dependsOn("validateLayoutDimensions")
}