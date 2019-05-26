import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.2.71"
	kotlin("plugin.spring") version "1.2.71"
}

group = "com.example"
version = "1.0"
java.sourceCompatibility = JavaVersion.VERSION_1_8

springBoot{
	mainClassName = "com.example.sanction.SanctionApplicationKt"
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	//implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jodd:jodd-lagarto:5.0.13")
	implementation("org.jsoup:jsoup:1.12.1")
	implementation("org.apache.commons:commons-text:1.6")
	implementation("com.github.mpkorstanje:simmetrics-core:4.1.1")

	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	//testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation ("com.nhaarman:mockito-kotlin:1.6.0")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.7")
	testImplementation ("io.kotlintest:kotlintest-extensions-spring:3.1.10")




}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
