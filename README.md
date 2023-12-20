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
* JDK 11 or newer (ex. AdoptOpenJDK)
* Maven
* Set `JAVA_HOME` environment variable

### Installing
1. Download release.
2. Install to local maven repository including sources and javadocs
```
mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=javafxhelper-0.1.10.jar -Dsources=javafxhelper-0.1.10-sources.jar -Djavadoc=javafxhelper-0.1.10-javadoc.jar
```
3. Add dependency in your pom.xml
```xml
<dependency>
	<groupId>io.github.architekt1024</groupId>
	<artifactId>javafxhelper</artifactId>
	<version>0.1.10</version>
</dependency>
```
4. Add to module-info.java
```
requires io.github.architekt1024.javafxhelper
```

## How to use the newer version of JavaFX and OpenJFX
The library should work with any new Java versions.

Change your pom.xml
```xml
<properties>
	<javafx.version><!-- openjfx version --></javafx.version>
</properties>

<dependencies>
	<!-- other dependencies -->
	<dependency>
		<groupId>io.github.architekt1024</groupId>
		<artifactId>javafxhelper</artifactId>
		<version>0.1.10</version>
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

## Build from sources
1. Clone repository
```git clone git@github.com:architekt1024/javafxhelper.git```
2. Install to local maven repository
```mvn clean install```

### Building with a newer version of Java or OpenJFX
* Update OpenJFX version: Change `javafx.version` property in `pom.xml` file
* Update Java version: Change `maven.compiler.source` and `maven.compiler.target` property in `pom.xml` file
