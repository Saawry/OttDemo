plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android) apply false
    id("kotlin-kapt")
}

android {
    namespace = "com.gadwaer.ottdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.gadwaer.ottdemo"
        minSdk = 24
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
        debug {
            buildConfigField ("String", "API_KEY", "\"b7b0daf3\"")
            buildConfigField ("String", "BASE_URL", "\"https://www.omdbapi.com/\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    implementation(libs.viewmodel)
    implementation(libs.glide)
    implementation(libs.glide.compiler)
    implementation(libs.livedata)
    implementation(libs.gson)
    implementation(libs.retrofit)


    implementation(libs.daggerHilt)
    annotationProcessor(libs.daggerHiltCompiler)
    kapt(libs.daggerHiltCompiler)


    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.circle.indicator)
    implementation(libs.why.not.image.carousel)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}