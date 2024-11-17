# mdb-spring-boot

# Book Management API

This API provides functionality to manage a collection of books. It allows users to perform CRUD operations, including adding, retrieving, updating, and deleting books.

## Key Features:

- Add a new book to the collection.
- Retrieve a list of all books with details such as ID, name, and author.
- Update an existing book's details using its ID.
- Delete a book from the collection by ID.

## Technologies Used:

- **Spring Boot**: For building the RESTful API.
- **JPA/Hibernate**: For database interaction.
- **Lombok**: To simplify model class definitions.
- **SLF4J**: For logging operations.

## Code Highlights:

- **BookController**: The primary controller class handling requests and responses.
- **BookRepository**: Repository interface for database operations.
- **BookDTO**: Data Transfer Object used to return a simplified view of books.
