# Advent of Code 2021
by Jani Oksanen (Maakaapeli)

## List of contents
- Prerequisites
- Project
- Tests
- Actual code

### Prerequisites
To run this project, you need Java to be installed in your local machine(Minimum version is Java 8. Preferred version is Java 11)

### Project
This is a repository for Advent of Code 2021 challenge https://adventofcode.com/2021
Every day has it' own packages and test-packages for more cleaner separation of code.

Everything is ran via App.java which has main method. Days are separated to their own methods.

This project was created using Maven with archetype "maven-archetype-quickstart". Dependencies can be installed from project root with following command
```
./mvnw clean install
```
### Tests
This project is developed using Test Driven Development(TDD), which means that before any code, there were test written(which failed ofc).

Tests use special data.json files which are located in src/test/resources/dayX folder(X is a day number) in a data.json file. These files contains example data from assigments

 Bit by bit we implement wanted code to satisfy tests. Tests can be run with maven wrapper from project root with command
```
./mvnw test
```
### Actual code
Every single day has it's own java-package named after the day(prefix is fi.maakaapeli and postfix varies from day1 to day25)

Every day contains it's data in src/main/resources/dayX folder(X is day number) in a data.json file. These files contains actual data from assigments

Code can be run from the project root with following command:
```
./mvnw clean compile exec:java
```


