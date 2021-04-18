import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

val jakartaVersion = "8.0.0"
val mongoDbEncryptVersion = "2.6.0"
val mongoDbAsyncDriverVersion = "3.12.8"
val mongoReactiveStreamsVersion = "4.2.2"
val springDataMongoDbVersion = "3.1.7"
val springDataMongoDbReactiveVersion = "2.4.2"
val jodaTimeVersion = "2.10.10"

dependencies {
    api(project(":data:common"))

    api("org.mongodb:mongodb-driver-async:$mongoDbAsyncDriverVersion")
    api("jakarta.platform:jakarta.jakartaee-api:$jakartaVersion")
    api("org.springframework.data:spring-data-mongodb:$springDataMongoDbVersion")
    api("org.springframework.boot:spring-boot-starter-data-mongodb-reactive:$springDataMongoDbReactiveVersion")
    api("org.mongodb:mongodb-driver-reactivestreams:$mongoReactiveStreamsVersion")
    api("com.bol:spring-data-mongodb-encrypt:$mongoDbEncryptVersion")
    api("joda-time:joda-time:$jodaTimeVersion")
    api("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
}
