# EnviroTrack

### EnviroTrack API

The project aims to develop a REST API service called EnviroTrack that facilitates the collection, storage, and analysis of environmental data obtained from sensors. The project focuses on creating a scalable and reliable system for managing sensor data.

> Using: <br>
>API: `Spring (Boot, REST, Data JPA)`, `Hibernate Validation`, `PostgreSQL`, `Lombok` <br>
> Client: `Java Core`, `RestTemplate`, `XChart`, `Jackson`

#### Features
* **Sensor registration endpoint (POST /sensors/registration):** <br>
Allows the registration of new sensors, validating the sensor's name and preventing duplicates.
* **Measurement addition endpoint (POST /measurements/add):** <br>Enables the addition of new measurements with temperature values and rainfall status, validating the input and associating measurements with the corresponding sensor.
* **Measurement retrieval endpoint (GET /measurements):** <br>Retrieves all stored measurements from the database.
* **Rainy days count endpoint (GET /measurements/rainyDaysCount):** <br>Returns the count of rainy days recorded in the database.

### Java Client
The project includes a Java client **that simulates the behavior of a real sensor** to test the EnviroTrack API. The client performs the following actions:

* Generates a specified number of measurements with random temperature and rainfall data.
* Sends the measurements to the EnviroTrack API by making POST requests to /measurements/add endpoint.
* Retrieves the measurements from the API by sending a GET request to /measurements endpoint.
* Uses the XChart library to create a graph of the temperature values obtained from the API.



