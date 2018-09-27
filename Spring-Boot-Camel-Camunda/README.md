# 1.	Integration of Spring Boot with Apache Camel & Camunda
## 1.1 Spring Initializr:
Spring Boot has really made developers' lives easier. Spring Boot's starters and auto-configurators reduce a lot of burden on developers. Another nice integration framework is Apache Camel, which provided abstraction over different technologies. In this article, we'll learn how to integrate Spring Boot and Apache Camel.

Spring Boot projects can be created in two ways. One is through Spring Boot Intitializr (https://start.spring.io/) (which we are doing here) and the other is through the STS Plugin for Eclipse. 

When you enter the Spring Initializr website, you'll be greeted with the interface below.
![image](https://user-images.githubusercontent.com/8769673/46129775-52164500-c255-11e8-966f-879f75ac4393.png)

After the ZIP downloads, extract the ZIP file and fire up Eclipse import as a Maven project. When the import process completes, Spring starters will help Maven download all the required dependencies for Camel.

## 1.2.	Add additional maven Dependencies for Camunda:
In your pom.xml add below dependencies.

	<repositories>
		<repository>
			<id>camunda-bpm-nexus</id>
			<name>camunda-bpm-nexus</name>
			<url>https://app.camunda.com/nexus/content/groups/public</url>
		</repository>
	</repositories>
	<dependency>
		<groupId>org.camunda.bpm.springboot</groupId>
		<artifactId>camunda-bpm-spring-boot-starter</artifactId>
		<version>3.0.0</version>
	</dependency>
	<dependency>
		<groupId>org.apache.camel</groupId>
		<artifactId>camel-spring</artifactId>
		<version>2.22.1</version>
	</dependency>

	<dependency>
		<groupId>org.camunda.bpm.extension.camel</groupId>
		<artifactId>camunda-bpm-camel-spring</artifactId>
		<version>0.5</version>
	</dependency>
	<dependency>
		<groupId>com.h2database</groupId>
		<artifactId>h2</artifactId>
	</dependency>
Now If we run this application as mvn springboot:run, we get following logs in console,

