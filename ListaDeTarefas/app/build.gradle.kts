plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.listadetarefas"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.listadetarefas"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.play.services.wearable)

    implementation(libs.appcompat)

    implementation(libs.material)

    implementation(libs.activity)

    implementation(libs.constraintlayout)

    implementation("androidx.wear:wear:1.3.0") // Atualizando para versão mais recente do Wear OS

    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")

    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.10")
}