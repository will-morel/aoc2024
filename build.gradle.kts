plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.2"
}

repositories {
    // Required to download KtLint
    mavenCentral()
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11.1"
    }

    build {
        dependsOn("ktlintFormat")
    }
}
