# US001 - Configure the location of the house

----

# Table of Contents
1. **[Requirements](#1-requirements)**

    1.1 [Dependency of other user stories](#11-dependency-of-other-user-stories)
2. **[Analysis](#2-analysis)**
3. **[Design](#3-design)**

    3.1 [Class diagram](#31-class-diagram)
    
    3.2 [Sequence diagram](#32-sequence-diagram)

    3.3 [Applied design patterns and principles](#33-applied-design-patterns-and-principles)
4. **[Tests](#4-tests)**

    4.1 [Tests of acceptance](#41-tests-of-acceptance)

    4.2 [Unit tests](#42-unit-tests)

    4.3 [Integration tests](#43-integration-tests)
5. **[Implementation](#5-implementation)**
6. **[Observations](#observations)**


-----



# 1. Requirements
_"As an Administrator, I want to configure the location of the house. "_

According to this requirement the system must allow the administrator to (...)
Location is composed by the following attributes:
- Address (street, number, city, zip code)
- GPS (latitude, longitude)
(...)

## 1.1. Dependency of other user stories
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


## 2.1. Relevant domain model excerpt

Below is the relevant domain model excerpt for this user story:

**_(image of the domain model)**_


# 3. Design

## 3.1 Class diagram

Below is the class diagram for this user story:


![US001-US001.png](..%2Fclass%2Fuserstory%2FUS001%2FUS001-US001.png)


## 3.2. Sequence diagram

Below is the sequence diagram for this user story:

**_(image of the sequence diagram)_**

## 3.3. Applied design patterns and principles

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

## 4.1. Tests of acceptance

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



## 4.2. Unit tests

```java
//tests
```

## 4.3. Integration tests

```java
//tests
```

# 5. Implementation

The main challenges of the implementation of this user story were:
1. Define the attributes of the location
2. (...)

# 6. Observations

* The location of the house is a fundamental requirement for the system.