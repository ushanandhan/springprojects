#Spring Boot with Kafka Producer Example

This Project covers how to use Spring Boot with Spring Kafka to Publish JSON/String message to a Kafka topic

Start Zookeeper

- bin\windows\zookeeper-server-start.bat config\zookeeper.properties

Start Kafka Server

- bin\windows\kafka-server-start.bat config\server.properties

Create Kafka Topic

- bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic KAFKA_EXAMPLE

Consume from the Kafka Topic via Console

- bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic KAFKA_EXAMPLE --from-beginning

Publish message via WebService

- http://localhost:8081/kafka/publish/Ushan`
