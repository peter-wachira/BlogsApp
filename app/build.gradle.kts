plugins{
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion (29)
    buildToolsVersion  = "29.0.3"

    defaultConfig {
        applicationId = "com.droid.diexample"
        minSdkVersion (21)
        targetSdkVersion (29)
        versionCode  = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

}

dependencies {
    //app dependencies
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.20")
    implementation ("androidx.appcompat:appcompat:1.1.0")
    implementation ("androidx.core:core-ktx:1.3.0")
    implementation ("androidx.constraintlayout:constraintlayout:1.1.3")

    //koin
    implementation ("org.koin:koin-android:2.1.5")
    implementation ("org.koin:koin-android-scope:2.1.5")
    implementation("org.koin:koin-androidx-viewmodel:2.1.5")

    //fragment -ktx
    implementation ("androidx.fragment:fragment-ktx:1.2.5")
    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.0")
    //logging
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.1")

    //room
    implementation ("androidx.room:room-runtime:2.2.5")
    implementation ("androidx.room:room-ktx:2.2.5")
    kapt ("androidx.room:room-compiler:2.2.5")

    //test
    testImplementation ("junit:junit:4.12")
    androidTestImplementation ("androidx.test.ext:junit:1.1.1")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.2.0")
}
