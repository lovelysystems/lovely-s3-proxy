plugins {
    base
    alias(libs.plugins.lovelysystems)
}

lovely {
    gitProject()
    dockerProject("ghcr.io/lovelysystems/lovely-s3-proxy") {
        from("docker")
        into("nginx") {
            from("nginx")
        }
    }
}
