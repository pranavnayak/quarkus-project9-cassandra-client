# quarkus-project9-cassandra-client
 A Sample Java Microservice Application to build a REST application using the Cassandra Quarkus extension, which allows us to connect to an Apache Cassandra, using the DataStax Java driver and  DataStax Object Mapper – a powerful Java-to-CQL mapping framework.
 
The DataStax Object Mapper – a powerful Java-to-CQL mapping framework that greatly simplifies the application’s data access layer code by sparing us the hassle of writing our CQL queries by hand.

The application is quite simple: the user can add elements in a list using a form, and the items list is updated. All the information between the browser and the server is formatted as JSON, and the elements are stored in the Cassandra database.

docker run --name local-cassandra-instance -p 9042:9042 -d cassandra
docker exec -it local-cassandra-instance cqlsh
CREATE KEYSPACE IF NOT EXISTS k1 WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
CREATE TABLE IF NOT EXISTS k1.fruit(name text PRIMARY KEY, description text);
