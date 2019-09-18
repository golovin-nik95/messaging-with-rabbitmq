# messaging-with-rabbitmq
Capstone project of the course "Java Engineer to Scalable Backend Developer" at Grid Dynamics

## How to run?

### Build all modules:

`./gradlew clean build`

### Start rabbitmq ha-cluster in docker:

`docker-compose up -d rabbit1 rabbit2 rabbit3`

### Start publisher/listener service either in local or in docker:

**Local:** `./gradlew <service>:bootRun`

**Docker:** `docker-compose up -d <service> --build --force-recreate`

## Services

* RabbitMQ HA-cluster:
    HA-Cluster of 3 nodes (Replication Factor 2) with a direct exchange "ex.example" 
    bound to the durable eagerly synchronized mirrored queue "q.example" 
    with the routing key "rk.example"
    * hostnames: rabbit1,rabbit2,rabbit3
    * Ports : 5672-5674:5672,15672-15674:15672
    * Admin UI: http://localhost:15672
    * Username/password: guest/guest

* Publisher: 
    Simple Spring Boot application sending 10 messages per second to the exchange "ex.example" 
    with the routing key "rk.example"
    * hostname: publisher

* Listener: 
    Simple Spring Boot application receiving and  messages from the queue "q.example"
    * hostname: listener

## How to check HA of the cluster

### Shutdown one of the cluster nodes

`docker-compose stop <nodename>`

### Make sure the flow keeps working either in rabbitmq UI or in docker:

**RabbitMQ UI:** `http://localhost:15672`

**Docker:** `docker-compose logs -f --tail 5`

### Restart one of the cluster nodes

`docker-compose up -d <nodename>`

### Make sure node joined the cluster in rabbitmq UI or in docker

**RabbitMQ UI:** `http://localhost:15672`

**Docker:** `docker-compose logs -f --tail 5`