plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
//    implementation("io.reactivex.rxjava3:rxandroid:3.0.2")
//    // Because RxAndroid releases are few and far between, it is recommended you also
//    // explicitly depend on RxJava's latest version for bug fixes and new features.
//    // (see https://github.com/ReactiveX/RxJava/releases for latest 3.x.x version)
    implementation("io.reactivex.rxjava3:rxjava:3.1.7")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}