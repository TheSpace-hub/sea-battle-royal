plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}

application {
    mainClass = "ru.seabattleroyal.App"
}
