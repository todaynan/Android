plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.todaynan"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todaynan"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
//    // 네이버 지도 SDK
//    implementation("com.naver.maps:map-sdk:3.18.0")
//    implementation ("com.google.android.gms:play-services-location:21.3.0")
    //CardView
    implementation("com.android.support:design:29.0.0")
    implementation ("androidx.viewpager2:viewpager2:1.0.0")
    //csv
    implementation ("com.opencsv:opencsv:5.6")
    //리사이클러뷰
    implementation ("androidx.recyclerview:recyclerview:1.2.1")
    implementation ("androidx.fragment:fragment-ktx:1.5.0")

    // Google Map
    implementation ("com.google.android.gms:play-services-maps:18.0.0")
    implementation ("com.google.android.gms:play-services-location:21.3.0")

    implementation ("androidx.coordinatorlayout:coordinatorlayout:1.1.0")
    implementation ("com.google.android.material:material:1.4.0")

    // 구글 로그인
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth")
    implementation ("com.google.android.gms:play-services-auth:19.0.0")
    // 카카오 로그인
    implementation ("com.kakao.sdk:v2-user:2.20.3")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

    //okHttp
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //GSON
    implementation ("com.google.code.gson:gson:2.8.7")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")
}