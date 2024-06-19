# kafka-spring
Contains example of code for working with kafka server over the Spring-Framework

# Test scenario

- See scenario PDF document  ```/kafka-test/doc/process.pdf```

1. The consumer accepts events with data from a REST service and then puts that data into the incoming topic.
2. The producer reads the events with data from the incoming topic and passes the data to a validator.
3. If the data is valid, it is saved to the database.
4. If the data is not valid, it is saved to an error topic.


![Scenarion of process] (https://github.com/OlPrognimak/kafka-spring/blob/master/kafka-test/doc/scenario.png)
