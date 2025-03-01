plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("C:\\Android Studio APK\\keystore.jks")
            storePassword = "nascar09"
            keyAlias = "key0"
            keyPassword = "nascar09"
        }
    }
    namespace = "com.uasmobilev1"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uasmobilev1"
        minSdk = 24
        targetSdk = 33
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.firebase:firebase-database:20.3.0")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    //implementation("com.google.firebase:firebase-auth:22.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation ("com.firebaseui:firebase-ui-auth:7.2.0")
    implementation ("com.facebook.android:facebook-android-sdk:latest.release")
}