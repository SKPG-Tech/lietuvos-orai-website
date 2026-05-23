plugins {
    java
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
    id("gg.jte.gradle") version "3.2.4"
}

group = "dev.salvijus"
version = "0.0.1-SNAPSHOT"
description = "orai"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("gg.jte:jte-spring-boot-starter-4:3.2.4")
    implementation("io.github.wimdeblauwe:htmx-spring-boot:5.1.0")
    implementation("org.springframework.boot:spring-boot-starter-restclient")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
}

jte {
    generate()
    binaryStaticContent = true
}

tasks.bootJar {
    archiveFileName.set("orai.jar")
}