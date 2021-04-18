import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

val feignReactorVersion = "2.0.22"
val openFeignVersion = "2.2.6.RELEASE"

dependencies {
    api(project(":core:common-api"))

    api("org.springframework.cloud:spring-cloud-starter-openfeign:$openFeignVersion")
    api("org.springframework.cloud:spring-cloud-openfeign-core:$openFeignVersion")

    api("com.playtika.reactivefeign:feign-reactor:$feignReactorVersion")
    api("com.playtika.reactivefeign:feign-reactor-core:$feignReactorVersion")
    api("com.playtika.reactivefeign:feign-reactor-spring-configuration:$feignReactorVersion")
    api("com.playtika.reactivefeign:feign-reactor-webclient:$feignReactorVersion")
}
