![image](https://user-images.githubusercontent.com/8769673/46193486-fbc00980-c31b-11e8-9ac7-64dbd3b64b32.png)

# 1.	Integration of Spring Boot with Apache Camel & Camunda
## 1.1 Spring Initializr:
Spring Boot has really made developers' lives easier. Spring Boot's starters and auto-configurators reduce a lot of burden on developers. Another nice integration framework is Apache Camel, which provided abstraction over different technologies. In this article, we'll learn how to integrate Spring Boot and Apache Camel.

Spring Boot projects can be created in two ways. One is through Spring Boot Intitializr (https://start.spring.io/) (which we are doing here) and the other is through the STS Plugin for Eclipse. 

When you enter the Spring Initializr website, you'll be greeted with the interface below.
![image](https://user-images.githubusercontent.com/8769673/46129775-52164500-c255-11e8-966f-879f75ac4393.png)

After the ZIP downloads, extract the ZIP file and fire up Eclipse import as a Maven project. When the import process completes, Spring starters will help Maven download all the required dependencies for Camel.

---

## 1.2.	Add additional maven Dependencies for Camunda:
In your pom.xml add below dependencies.
```xml
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
```
Now If we run this application as mvn springboot:run, we get following logs in console,
![image](https://user-images.githubusercontent.com/8769673/46133433-1a5fcb00-c25e-11e8-9d31-5de6d877d1d5.png)

---


## 1.3 Integrating Apache Camel with Camunda using Spring Boot:
	In your pom.xml add below dependencies.
Now, let's get our hands dirty.

Create a CamelController for invoking the Camel route:
![image](https://user-images.githubusercontent.com/8769673/46133805-ffda2180-c25e-11e8-86eb-f3854d049f59.png)

Here, we're calling <mark>firstRoute</mark> and sending the body "Calling via Spring Boot Rest Controller" using ProducerTemplate.

Let's create a component class for placing Camel Routes:
![image](https://user-images.githubusercontent.com/8769673/46133887-36b03780-c25f-11e8-8514-41479e3a45ee.png)

The specialty of Camel starter and Camunda starter is that it'll auto-wire the Camel context and auto-detect all of the Camel routes in our application.

You already have a main method, which was created by Intializr. In that add CamelServiceImpl bean and set camel context & process engine as its property. Autowire them as well.

![image](https://user-images.githubusercontent.com/8769673/46133943-5b0c1400-c25f-11e8-9694-23e6ad41bd1a.png)

---

## 1.4 Create BPMN file:
	Using Camunda Moduler we can create one bpmn flow as below,
![image](https://user-images.githubusercontent.com/8769673/46181874-e16b3900-c2e5-11e8-976e-455565c54dae.png)

You can refer camunda website (https://camunda.com/products/) to create bpmn file.

## 1.5 Run our application:
Once every thing is set lets run our application with the command mvn springboot:run.
![image](https://user-images.githubusercontent.com/8769673/46181602-7a995000-c2e4-11e8-90f4-76a8bef2e5ff.png)
- We can see camel has started and one router is started.
- After that Camunda engine also started.
- Hit http://localhost:8080/camel in the browser, we able to see full output in the console as below,

![image](https://user-images.githubusercontent.com/8769673/46181791-71f54980-c2e5-11e8-9440-c6cba3106a81.png)

## 1.5 Project Structure:

![image](https://user-images.githubusercontent.com/8769673/46181922-3018d300-c2e6-11e8-8291-7e4ac4ebbedb.png)



