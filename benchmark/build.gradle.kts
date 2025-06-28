plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("me.champeau.jmh") version "0.7.3"
}
java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}

jmh {
    jvmArgs.add("-XX:-OmitStackTraceInFastThrow")
    profilers.add("gc")
}

dependencies {
    testImplementation(libs.jmh.core)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    annotationProcessor(libs.jmh.generator.annprocess)
    testImplementation(kotlin("test"))
}