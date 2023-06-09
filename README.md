[![Java CI](https://github.com/opifexM/Catalog/actions/workflows/main.yml/badge.svg)](https://github.com/opifexM/Catalog/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/2eca3417597df6db6808/maintainability)](https://codeclimate.com/github/opifexM/Catalog/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/2eca3417597df6db6808/test_coverage)](https://codeclimate.com/github/opifexM/Catalog/test_coverage)

# Catalog Project

Web link: https://catalog-production.up.railway.app

This is the README for the **Catalog** project. It is a web application that allows users to manage and query data related to computer components such as computers, motherboards, power supplies, and video cards.

## Technologies

The project uses the following technologies:

-   Spring Boot
-   Spring Data JPA
-   Thymeleaf
-   Spring Boot Actuator
-   PostgreSQL
-   SpringDoc OpenAPI

## Endpoints and Query Methods

The Catalog project provides four different endpoints for querying data:

| Endpoint       | Query Method                       |
|----------------|------------------------------------|
| Computers      | Spring Data JPA Criteria API       |
| Motherboards   | Spring Data JPA Specification API  |
| Power Supplies | Annotation-based Query Declaration |
| Video Cards    | Query Creation from Method Names   |


## API Functionality

The project has several REST API endpoints and a Web MVC API for managing computers and their components.

The project also provides a Data Controller for managing data, which includes the following features:

-   Displaying statistics
-   Deleting all data
-   Uploading data from JSON files

For more information on the project structure and the specific implementation details, please refer to the provided code.