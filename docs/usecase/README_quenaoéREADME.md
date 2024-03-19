# US001 - Configure the location of the house



# Table of Contents

# Table of Contents
1. [Requirements](#requirements)
    - [Dependency of other user stories](#dependency-of-other-user-stories)
2. [Analysis](#analysis)
    - [Relevant domain model excerpt](#relevant-domain-model-excerpt)
3. [Design](#design)
    - [Class diagram](#class-diagram)
    - [Sequence diagram](#sequence-diagram)
    - [Applied design patterns and principles](#applied-design-patterns-and-principles)
4. [Tests](#tests)
    - [Tests of acceptance](#tests-of-acceptance)
    - [Unit tests](#unit-tests)
    - [Integration tests](#integration-tests)
5. [Implementation](#implementation)
6. [Observations](#observations)


# 1. Requirements
_As an Administrator, I want to configure the location of the house._

According to this requirement the system must allow the administrator to (...)
Location is composed by the following attributes:
- Address (street, number, city, zip code)
- GPS (latitude, longitude)
(...)

## Dependency of other user stories
None

# 2. Analysis

The analysis of this requirement is composed by the following steps:

1. Define the attributes of the location
2. (...)

As mentioned in the requirement, the location is composed by the following attributes:

| Attribute | Rules |
|-----------|-------|
| **Address**   | The address must be composed by street, number, city and zip code. |
| Street    | The street must be composed by letters and numbers. |
| Number    | The number must be composed by numbers. |
| City      | The city must be composed by letters. |
| Zip code  | The zip code must be composed by numbers. |
| **GPS**       | The GPS must be composed by latitude and longitude. |
| Latitude  | The latitude must be composed by numbers. |
| Longitude | The longitude must be composed by numbers. |


## Relevant domain model excerpt

Below is the relevant domain model excerpt for this user story:

**_(image of the domain model)**_


# 3. Design

## Class diagram

Below is the class diagram for this user story:


![US001-US001.png](..%2Fclass%2Fuserstory%2FUS001%2FUS001-US001.png)


## Sequence diagram

Below is the sequence diagram for this user story:

**_(image of the sequence diagram)_**

## Applied design patterns and principles

* Controller - The class LocationController has the responsibility to control the location of the house.
* Information Expert - The class Location has the responsibility to store the address and GPS of the house.
* Creator - The class LocationFactory has the responsibility to create a new location.
* Pure Fabrication - The class LocationRepository has the responsibility to store the location of the house.
* Low Coupling - The class LocationController has low coupling with the class LocationRepository.
* High Cohesion - The class Location has high cohesion with the class LocationController.
* Factory - The class LocationFactory has the responsibility to create a new location.
* Single Responsibility Principle (SRP) - The class Location has the responsibility to store the address and GPS of the house.
* Repository - The class LocationRepository has the responsibility to store the location of the house.
* Aggregate Root - The class Location is the aggregate root of the location of the house.
* Value Object - The class Address is a value object of the location of the house.

# 4. Tests

## Tests of acceptance

- **Scenario 1:** The administrator wants to configure the location of the house for the first time
  - **When** the administrator wants to configure the location of the house.
  - **Then** the system must allow the administrator to configure the location of the house.
  - **And** the system must store the location of the house.
  - **And** the system must confirm the location of the house was stored.

- **Scenario 2:** The administrator wants to configure the location of the house again
  - **When** the administrator wants to configure the location of the house again.
  - **Then** the system must allow the administrator to configure the location of the house.
  - **And** the system must store the new location of the house.
  - **And** the system must confirm the new location of the house was stored.

- **Scenario 3:** The administrator wants to configure the location of the house with invalid parameters
  - **When** the administrator wants to configure the location of the house with invalid parameters.
  - **Then** the system must not allow the administrator to configure the location of the house.
  - **And** the system must not store the location of the house.
  - **And** the system must confirm the location of the house was not stored.

- **Scenario 4:** Test the persistence of the location of the house
  - **Given** the administrator wants to configure the location of the house.
  - **When** the administrator wants to configure the location of the house.
  - **Then** the system must store the location of the house.
  - **And** the system must confirm the location of the house was stored.



## Unit tests

```java
//tests
```

## Integration tests

```java
//tests
```

# 5. Implementation

The main challenges of the implementation of this user story were:
1. Define the attributes of the location
2. (...)

# 6. Observations

* The location of the house is a fundamental requirement for the system.