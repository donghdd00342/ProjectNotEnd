## Development

To start your application in the dev profile, simply run:

    ./mvnw

## Building for production

To optimize the NotEnd application for production, run:

    ./mvnw -Pprod clean package

To ensure everything worked, run:

    java -jar target/*.war


## Using Docker to simplify development (optional)

A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw verify -Pprod dockerfile:build dockerfile:tag@version dockerfile:tag@commit

Then run:

    docker-compose -f src/main/docker/app.yml up -d

## Docs

### API docs
    http://localhost:8080/v2/api-docs
    http://localhost:8080/swagger-ui.html

### Gcloud
1. gcloud components list / 
2. gcloud auth list / gcloud config set account `ACCOUNT`
3. gcloud projects list
4. gcloud config set project <PROJECT_ID> / gcloud config list project
5. mvn appengine:run
6. mvn appengine:deploy