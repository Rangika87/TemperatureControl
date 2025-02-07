# Building Temperature Control Service

This project is a RESTful API for managing and controlling the temperature of buildings. It allows receiving sensor data, adjusting heating or cooling, and optimizing energy usage.

## Features
✅ Store temperature readings from sensors.<br>
✅ Compare current and desired temperatures to control heating and cooling.<br>
✅Adjusts the temperature based on heating or cooling.<br>
    
- for heating, temperature increases gradually by 0.5 upto desired temperature
                The room never heats more than the desired temperature.
                Prevents overheating
- for cooling, temperature decreases gradually by 0.5 upto desired temperature
                The room never cools below the desired temperature.
                Prevents overcooling
.
  
✅Simulates a command being sent to the respective system<br>
✅Saves the updated room temperature<br>
✅Retrieves the room from the database.<br>

## Tech Stack
- **Java** (Spring Boot)
- **PostgreSQL** (Database)
- **JUnit & Mockito** (Testing)

## Setup and Build Instructions

### Prerequisites
- Java 17 or later
- PostgreSQL installed and running
- Maven installed

### Installation Steps
1. **Clone the repository:**
   ```sh
   git clone https://github.com/Rangika87/TemperatureControl.git
   cd TemperatureControl
   ```


2. **Build and run the application:**
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## API Endpoints & Usage

### 1. Get all sensor data
**Request:**
```sh
curl -X GET http://localhost:8080/sensorData/
```
**Response:**
```json
[
  {
    "roomId": 1,
    "currentTemperature": 22.5,
    "timestamp": "2024-02-05T12:00:00Z"
  }
]
```

### 2. Get room temperature by room number (get the latest value in the sensor data)
**Request:**
```sh
curl -X GET http://localhost:8080/room/1/temperature
```
**Response:**
```json
{
    "id": 6,
    "roomId": 1,
    "currentTemperature": 22.5,
    "timestamp": "2025-02-07T06:05:58.608531"
}
```

### 3. Control temperature (send sensor reading)
**Request:**
```sh
curl -X POST http://localhost:8080/sensor \
     -H "Content-Type: application/json" \
     -d '{"roomId": 1, "currentTemperature": 19.0}'
```
**Response:**
```json
"Turning on heating for room 1 and the set the temperature to 19.5"
```

### 4. Get all room details
**Request:**
```sh
curl -X GET http://localhost:8080/roomDetails/
```
**Response:**
```json

 {
        "id": 1,
        "name": "Server Room",
        "desiredTemperature": 21.0,
        "currentTemperature": 22.5
    },
    {
        "id": 2,
        "name": "Meeting Room",
        "desiredTemperature": 23.5,
        "currentTemperature": 24.0
    },
```

## Running Tests
Run the unit tests using:
```sh
mvn test
```

## Author
- Rangika Fernando

## License
This project is licensed under the MIT License.
