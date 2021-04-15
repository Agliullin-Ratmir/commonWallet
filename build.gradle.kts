import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("idea")
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
}

group = "common.wallet"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web" )
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-autoconfigure:2.4.4")
	implementation("org.springframework.boot:spring-boot-starter-data-rest:2.4.4")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.data:spring-data-mongodb:3.1.6")
	implementation("org.mapstruct:mapstruct:1.3.1.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.3.1.Final")
	implementation("org.mapstruct:mapstruct-processor:1.3.1.Final")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.0.2")
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-ribbon:1.1.8.RELEASE")
	implementation("org.springframework.cloud:spring-cloud-starter-netflix-ribbon:2.2.7.RELEASE")
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes:1.1.8.RELEASE")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.cloud:spring-cloud-dependencies:2020.0.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
