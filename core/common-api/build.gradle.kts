import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

val nettyVersion = "4.1.45.Final"
val reactorKotlinExtensionsVersion = "1.1.1"
val nettyReactorVersion = "0.9.7.RELEASE"

dependencies {
    api(project(":core:common"))

    api("io.netty:netty-all:$nettyVersion")
    api("io.netty:netty-buffer:$nettyVersion")
    api("io.netty:netty-transport-native-epoll:$nettyVersion:linux-x86_64")

    api("io.projectreactor:reactor-core:3.4.4")
    api("io.projectreactor.addons:reactor-extra")
    api("io.projectreactor.kotlin:reactor-kotlin-extensions:$reactorKotlinExtensionsVersion")
    api("io.projectreactor.netty:reactor-netty:$nettyReactorVersion")

    api("org.springframework.boot:spring-boot-starter-aop")
    api("org.springframework.boot:spring-boot-starter-webflux")

    api("org.codehaus.janino:janino")
}
