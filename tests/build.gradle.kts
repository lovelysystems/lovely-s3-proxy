plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)
    testImplementation(libs.kotest.framework)

    testImplementation(libs.ktor.client.core)
    testImplementation(libs.ktor.client.cio)
}

tasks.register<Exec>("dockerComposeUp") {
    group = "Tests"
    description = "Starts Docker containers using docker-compose"
    commandLine("docker-compose", "up", "--wait")
    doLast {
        commandLine("sleep", "2") //time to create the bucket
    }
}

tasks.register<Exec>("dockerComposeDown") {
    group = "verification"
    description = "Stops Docker containers using docker-compose"
    commandLine("docker-compose", "down")
}

tasks.test {
    useJUnitPlatform()
    dependsOn("dockerComposeUp")
    finalizedBy("dockerComposeDown")
}
