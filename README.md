# javafxhelper

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=architekt1024_javafxhelper&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=architekt1024_javafxhelper)
![GitHub](https://img.shields.io/github/license/architekt1024/javafxhelper?style=plastic)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=architekt1024_javafxhelper&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=architekt1024_javafxhelper)
![GitHub release (latest by date)](https://img.shields.io/github/v/release/architekt1024/javafxhelper?style=plastic)
![GitHub Release Date](https://img.shields.io/github/release-date/architekt1024/javafxhelper?style=plastic)

The aim of the project is to create reusable JavaFX components. The project is at an early stage of development.

## Getting Started
Download last release and install.

### Prerequisites
* JDK 17 or newer (Eclipse Adoptium recomened)
* Maven
* Set `JAVA_HOME` environment variable

### Installing
1. Download release.
2. Install to local maven repository including sources and javadocs
```
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=javafxhelper-0.1.11.jar -Dsources=javafxhelper-0.1.11-sources.jar -Djavadoc=javafxhelper-0.1.11-javadoc.jar
```
3. Add dependency in your pom.xml
```xml
<dependency>
	<groupId>io.github.architekt1024</groupId>
	<artifactId>javafxhelper</artifactId>
	<version>0.1.11</version>
</dependency>
```
4. Add to your module-info.java (only if you use modules)
```
requires io.github.architekt1024.javafxhelper
```

## How to use the newer version of Java and OpenJFX
This project is built and tested against a specific version of Java / OpenJFX. Newer versions are not officially supported.

However, advanced users may choose to override the JDK or OpenJFX dependencies if they require a newer version (for example, due to platform constraints or local environment requirements).

**Important notes**
* Using a newer JDK or OpenJFX version is at your own risk
* No guarantees are provided regarding:
  * binary compatibility
  * runtime behavior
  * visual correctness

Issues reproducible only with overridden JavaFX versions will not be treated as bugs

### How to override the Java or JavaFX version
You may override the JDK version or OpenJFX dependencies in your build configuration (for example via Maven):
```xml
<properties>
	<java.version><!-- Java version --></java.version>
	<javafx.version><!-- openjfx version --></javafx.version>
</properties>

<dependencies>
	<!-- other dependencies -->
	<dependency>
		<groupId>io.github.architekt1024</groupId>
		<artifactId>javafxhelper</artifactId>
		<version>0.1.11</version>
		<exclusions>
			<exclusion>
				<artifactId>javafx-fxml</artifactId>
				<groupId>org.openjfx</groupId>
			</exclusion>
			<exclusion>
				<artifactId>javafx-swing</artifactId>
				<groupId>org.openjfx</groupId>
			</exclusion>
			<exclusion>
				<artifactId>javafx-web</artifactId>
				<groupId>org.openjfx</groupId>
			</exclusion>
		</exclusions>
	</dependency>

	<dependency>
		<artifactId>javafx-fxml</artifactId>
		<groupId>org.openjfx</groupId>
		<version>${javafx.version}</version>
	</dependency>
	<dependency>
		<artifactId>javafx-swing</artifactId>
		<groupId>org.openjfx</groupId>
		<version>${javafx.version}</version>
	</dependency>
	<dependency>
		<artifactId>javafx-web</artifactId>
		<groupId>org.openjfx</groupId>
		<version>${javafx.version}</version>
	</dependency>

	<!-- other dependencies -->
</dependencies>
```

**Ensure that**
* the JavaFX version is compatible with your Java runtime
* all JavaFX modules are aligned to the same version
* your platform-specific artifacts (e.g. Linux, Windows) match the JavaFX distribution

**Recommendation**

If you decide to use a newer JavaFX version:
* verify behavior thoroughly in your environment
* be prepared to debug JavaFX-related issues independently
* consider reverting to the supported version when reporting issues

## Build from sources
1. Clone repository
```git clone git@github.com:architekt1024/javafxhelper.git```
2. Install to local maven repository
```mvn clean install```
