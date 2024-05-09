# Blockchain
Blockchain is a Java Spring Boot application for dealing with Bitcoin transactions.

  
## Run the application in production environment
Use the docker-compose to run the application container. Go to the folder of the project, and then run the following command. In the production environment, the applications connect to MySQL docker container.

```bash

docker-compose  up

```


## Run the application in development environment
Download Maven first, then go to the folder of the project, then run the following command. In the development environment, the application connects to an embedded H2 database.

```bash

mvn clean spring-boot:run -D spring.profiles.active=dev

```  

## Test the application
Download Maven first, then go to the folder of the project, then run the following command. In the testing environment, the application connects to an embedded H2 database.

```bash

mvn clean test -D spring.profiles.active=dev

```

  
## Documentation

Documentation is in this [Google Doc](https://docs.google.com/document/d/1chA2Z0WbRXo5G--7tLcvVzfgAKYUMtc_XGuDYFHf6go/edit?usp=sharing)

  

## Contributing
This project reference to the following projects:

https://medium.com/thefreshwrites/the-way-of-dockerize-a-spring-boot-and-mysql-application-with-docker-compose-de2fc03c6a42

https://github.com/amilairoshan/ErrorHandlingDemo

https://springdoc.org/

  
## License
[MIT](https://choosealicense.com/licenses/mit/)