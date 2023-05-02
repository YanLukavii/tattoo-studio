plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.flywaydb:flyway-core")
	implementation("org.springframework.kafka:spring-kafka")
	implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")


}

