### Index
- Indexes are used to retrieve data from the database more quickly than otherwise. The users cannot see the indexes, they are just used to speed up searches/queries.

```
CREATE INDEX index_name
ON table_name (column1, column2, ...);
```

## 1. Java Persistence API (JPA): An Overview
- JPA is a specification that defines a standard interface for Java applications to interact with relational databases. It simplifies database access by providing a set of annotations and APIs for mapping Java objects to database tables. JPA is not an implementation but rather a set of rules that frameworks, like Hibernate, implement.
## 2. Hibernate: A Robust JPA Implementation
- Hibernate is a powerful and widely used Object-Relational Mapping (ORM) framework. It is a JPA implementation, meaning it adheres to the rules set by the JPA specification. Hibernate goes beyond JPA, offering additional features and optimizations. Developers often choose Hibernate when they need a full-featured ORM framework with advanced capabilities for complex database interactions.
## 3. Java Database Connectivity (JDBC): The Foundation
- JDBC is the standard Java API for connecting to relational databases. Unlike JPA and Hibernate, JDBC is lower level and requires developers to write SQL queries and handle result sets manually. While it offers more control, it also demands more code. JDBC is often used when fine-grained control over database interactions is required, or when working with databases not covered by higher-level frameworks.

### Useful links
- https://drawsql.app/
- https://www.linkedin.com/pulse/demystifying-database-access-java-jpa-hibernate-jdbc-ali-gb7uf/