# PocketPou

A Java virtual pet simulation game exploring object-oriented design and software design patterns.

## Status

Initial project scaffold. Pet care, customization, inventory, shop, mini-games and persistence are planned features, not yet implemented.

## Requirements

- JDK 21
- Maven 3.9+ (or IntelliJ IDEA with bundled Maven)

## Build and run

```sh
mvn clean package
java -jar target/pocketpou-1.0-SNAPSHOT.jar
```

Alternatively, open `pom.xml` as a project in IntelliJ IDEA and run `com.pocketpou.Main`.

## Structure

- `model`: domain objects and game rules.
- `view`: terminal input and output.
- `controller`: coordinates user actions and the domain model.
- `src/main/resources`: application resources.
- `src/test/java`: future automated tests.
- `docs`: UML and design documentation.

The MVC structure is a starting point. Design patterns will be selected and implemented as the domain model evolves.
