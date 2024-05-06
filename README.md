# Blockchain

Blockchain is a Java Spring Boot application for dealing with Bitcoin transactions.

## Run the application

Use the docker to run the container. In the production environment, the applications connect to MySQL docker container. 


```bash
docker-compose up
```

## Test the application

Download Maven first, then run the following command. 
There are a total of 16 tests.
In the test environment, the application connects to an embedded H2 database.
```
mvn clean test
```

## Documentation
Documentation is in this [Google Doc](https://docs.google.com/document/d/1chA2Z0WbRXo5G--7tLcvVzfgAKYUMtc_XGuDYFHf6go/edit?usp=sharing)

## Contributing

This project reference to the following projects:

https://medium.com/thefreshwrites/the-way-of-dockerize-a-spring-boot-and-mysql-application-with-docker-compose-de2fc03c6a42

https://github.com/amilairoshan/ErrorHandlingDemo

## License

[MIT](https://choosealicense.com/licenses/mit/)