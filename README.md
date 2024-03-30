# customer-backend

## assumptions

* code-guideline: always prevent null at application input (controller, database) so that no null checks are necessary
  inside
* api calls will be secured with a token, Spring Security can verify and extract the users scope from (the customerId,
  the user has access to)

## thoughts

* one endpoint that validates the vatId can be used from several clients (browser, mobile, ...) so that only one team
  must maintain the validation rules

## development

To ensure that the tests reflect production a postgres docker container is in use and can be cleaned with

```bash
make freshdb
```

Executing tests

```bash
make freshdb
make test
```

Building the jar and docker image (skipping tests)
(when your OS isn't macos, you might need to change the postgres url to "localhost" before bundling in (src/main/resources/application-production.properties)

```bash
make bundle
```

Starting the built docker image

```bash
make freshdb
docker run -p 9000:9000 customer-backend
# http://localhost:9000/swagger-ui/index.html serves the OpenAPI
# http://localhost:9000/sub-customers serves an empty list due to the fresh db
```

## next steps

* multiple profiles for different deploy stages
* HTTPS only
* DB connection with TLS at rest
* when amount of customers scales tremendously:, partitioning and sharding the customer table based on the customer_id
* shared code formatting guidelines
* enhance exception handler: standard error response DTO providing error messages that could be shown in the UI
* additional endpoints to make CRUD complete
* enhancing the Dockerfile


