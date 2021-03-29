plugins {
  kotlin("jvm") version "1.4.31"
}

group = "xyz.ragunath"
version = "0.1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))

  // JUnit
  testImplementation(kotlin("test-junit5"))
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")

  // ApprovalTests
  testImplementation(files("libs/approvaltests-9.5.1-SNAPSHOT.jar"))
  testImplementation(files("libs/approvaltests-util-9.5.1-SNAPSHOT.jar"))

  // Jqwik
  testImplementation("net.jqwik:jqwik-api:1.5.0")
  testRuntimeOnly("net.jqwik:jqwik-engine:1.5.0")
}

tasks.test {
  useJUnitPlatform()
}
