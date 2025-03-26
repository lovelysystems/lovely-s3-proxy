plugins {
    base
    alias(libs.plugins.lovelysystems)
}

lovely {
    gitProject()
    dockerProject("lovelysystems/lovely-s3-proxy") {
        from("docker")
        into("nginx") {
            from("nginx")
        }
    }
}

subprojects {
    apply {
        plugin("com.lovelysystems.gradle")
    }
}
