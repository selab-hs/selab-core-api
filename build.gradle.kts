import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.6.21"

    id("org.springframework.boot") version "2.7.6"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    idea
}

group = "com.selab"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

object DependencyVersion {
    const val KOTLIN_LOGGING_VERSION = "3.0.5"
    const val SWAGGER_VERSION = "3.0.0"
    const val JWT_VERSION = "4.3.0"
    const val LOGBACK_ENCODER = "7.3"
}

dependencies {
    /** spring starter */
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    /** kotlin */
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    /** jwt */
    implementation("com.auth0:java-jwt:${DependencyVersion.JWT_VERSION}")

    /** swagger */
    implementation("io.springfox:springfox-swagger2:${DependencyVersion.SWAGGER_VERSION}")
    implementation("io.springfox:springfox-swagger-ui:${DependencyVersion.SWAGGER_VERSION}")
    implementation("io.springfox:springfox-spring-webmvc:${DependencyVersion.SWAGGER_VERSION}")

    /** logger */
    implementation("io.github.microutils:kotlin-logging-jvm:${DependencyVersion.KOTLIN_LOGGING_VERSION}")
    implementation("net.logstash.logback:logstash-logback-encoder:${DependencyVersion.LOGBACK_ENCODER}")

    /** mysql */
    runtimeOnly("mysql:mysql-connector-java")

    /** test */
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    /** etc */
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "7.5.1"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("jar") {
    enabled = false
}

defaultTasks("bootRun")

val Project.isSnapshotVersion: Boolean get() = version.toString().endsWith("SNAPSHOT")
