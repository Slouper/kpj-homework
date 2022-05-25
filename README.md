# KPJ-HOMEWORK

### 1) Build new docker image

1. `mvn clean install`
2. Default generated image name is: `kpj-homework:1.0.0-SNAPSHOT`

### 2) Run docker image that you built in previous step (docker run command)

<i>Command below counts that you are running rabbitmq server on your localhost machine on the port 5672. Therefore you can see setting of
the `spring.rabbitmq.addresses` in the command.</i>

    docker run -p 8080:8080 -e SPRING_APPLICATION_JSON='{\"spring.rabbitmq.addresses\":\"amqp://guest:guest@host.docker.internal\"}' kpj-homework:0.0.1-SNAPSHOT

##### In case you don't want to use the rabbitmq server running on your PC - `localhost:5672`, then replace the `spring.rabbitmq.addresses` in the command with your own value.

### 3) Run docker image via `docker-compose` command

There is the file `docker-compose.yml` in the project that ensures tha docker-compose command will use it when you run it from the project folder. Run following
command to run your application in docker container.

    docker-compose up

Note that in the docker-compose.yml is following defintion in the <b>environment</b> block:

    SPRING_RABBITMQ_ADDRESSES: "${RABBIT_ADDRESS:-amqp://guest:guest@host.docker.internal:5672/}"

The application will use the environment variable `RABBIT_ADDRESS` for the connection to RABBITMQ server and if it is not defined then it uses default one (
defined in that row directly) which counts with the running RABBITMQ server on your local machine.

#### You can define your own RABBIT_ADDRESS environment variable in order to be used by docker-compose command in following way

1. Create the file .env in the folder where you are running `docker-compose up` command
2. In the file define following:

    RABBIT_ADDRESS=<---your own rabbitmq server address available from the docker image--->
