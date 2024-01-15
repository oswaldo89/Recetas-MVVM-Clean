import dependencies.Deps
import scripts.validateLayoutDimensions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
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
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), file("proguard-rules.pro"))
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

    // Android
    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.constraintLayout)
    implementation(Deps.playServicesMaps)

    // UI Components
    implementation(Deps.floatingSearchView)
    implementation(Deps.coreSplashScreen)
    implementation(Deps.navigationFragmentKtx)
    implementation(Deps.navigationUiKtx)

    // Networking
    implementation(Deps.retrofit)
    implementation(Deps.retrofitGson)
    implementation(Deps.okHttpLoggingInterceptor)

    // Dependency Injection
    implementation(Deps.daggerHilt)
    kapt(Deps.daggerHiltCompiler)

    // Lifecycle
    implementation(Deps.lifecycleLiveDataKtx)
    implementation(Deps.lifecycleViewModelKtx)

    // AndroidX
    implementation(Deps.activityKtx)
    implementation(Deps.fragmentKtx)

    // Image
    implementation(Deps.glide)
    kapt(Deps.glideCompiler)

    // Testing
    testImplementation(Deps.junit)
    androidTestImplementation(Deps.junitExt)
    androidTestImplementation(Deps.espressoCore)
    testImplementation(Deps.mockk)
    testImplementation(Deps.kotlinxCoroutinesTest)
    testImplementation(Deps.junitJupiterApi)
    testRuntimeOnly(Deps.junitJupiterEngine)
    testImplementation(Deps.junitJupiterParams)
    testRuntimeOnly(Deps.junitVintageEngine)

    //Crash Reporting
    implementation(enforcedPlatform(Deps.firebaseBom))
    implementation(Deps.firebaseAnalytics)
    implementation(Deps.firebaseCrashlytics)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("validateLayoutDimensions") {
    doLast {
        validateLayoutDimensions(project)
    }
}

tasks.named("preBuild") {
    dependsOn("validateLayoutDimensions")
}