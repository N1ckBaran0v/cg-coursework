plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("8.1.1")
    id("com.adarshr.test-logger").version("4.0.0")
}

group = "com.github.N1ckBaran0v"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.google.dagger:dagger:2.51.1")
    annotationProcessor("com.google.dagger:dagger-compiler:2.51.1")
    implementation("org.jetbrains:annotations:26.0.1")
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.adarshr:gradle-test-logger-plugin:4.0.0")
    }
}

apply(plugin = "com.adarshr.test-logger")

tasks.test {
    useJUnitPlatform()
}

tasks.shadowJar {
    manifest {
        attributes["Main-Class"] = "com.github.N1ckBaran0v.Main"
        attributes["Created-By"] =  "Nikolay Baranov"
    }
    archiveBaseName.set("coursework")
    archiveClassifier.set("")
    archiveVersion.set("")
}