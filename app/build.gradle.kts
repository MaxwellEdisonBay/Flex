plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
}
android {
    namespace = "com.flexfitnesstestapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.flexfitnesstestapp"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "API_URL", "\"https://api.freecurrencyapi.com/\"")
            buildConfigField("String", "API_KEY", "\"tCTxj9LGP7C83vfsNZB3bx5mcQ6mvRX4XqB1KROn\"")
            buildConfigField(
                "String",
                "GOOGLE_CLIENT_ID",
                "\"517002206928-q68ssjtd2qfmclvq9eopd3qqfpdmejl3.apps.googleusercontent.com\""
            )
//            buildConfigField ("String", "GOOGLE_CLIENT_ID", "\"199539274648-n947culrpg5egqij4kubs306urugp4k8.apps.googleusercontent.com\"")
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "API_URL", "\"https://api.freecurrencyapi.com/\"")
            buildConfigField("String", "API_KEY", "\"tCTxj9LGP7C83vfsNZB3bx5mcQ6mvRX4XqB1KROn\"")
            buildConfigField(
                "String",
                "GOOGLE_CLIENT_ID",
                "\"517002206928-q68ssjtd2qfmclvq9eopd3qqfpdmejl3.apps.googleusercontent.com\""
            )
//            buildConfigField ("String", "GOOGLE_CLIENT_ID", "\"199539274648-n947culrpg5egqij4kubs306urugp4k8.apps.googleusercontent.com\"")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.0.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.android.gms:play-services-auth:20.6.0")

    // compose navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.3")

    // hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // timber log
    implementation("com.jakewharton.timber:timber:5.0.1")

    // pagination
    implementation("androidx.paging:paging-compose:1.0.0-alpha19")

    // coil
    implementation("io.coil-kt:coil-compose:2.3.0")

    implementation("com.google.code.gson:gson:2.10.1")
    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // squircle button shapes
    implementation("com.github.stoyan-vuchev:squircle-shape:1.0.3")

    // Baloon tooltip UI library
    implementation("com.github.skydoves:balloon-compose:1.5.4")
}