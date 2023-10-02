# Library_app_java_console


## Overview

This is a simple Java console application that manages a library's book collection using the Repository and Service pattern. The application provides various features for interacting with the library's books, including adding new books, listing available books, borrowing and returning books, searching for books, and more.

## Features

1. **Add New Books**: You can add new books to the library by providing their title, author, and ISBN number. The application validates the input and stores the book in the library's collection.

2. **List Available Books**: View a list of all available books in the library, including their title, author, and availability status.

3. **Search for Books**: Search for books by title or author, making it easy to find specific books in the collection.

4. **Borrow and Return Books**: Borrow books by providing their ISBN number and return them when you're done. The application tracks borrowing and availability status.

5. **List Borrowed Books**: Display a list of books that are currently borrowed, including borrower information and the date of borrowing.

6. **Remove Books**: Remove books from the library by providing their ISBN number. The application updates the library's collection accordingly.

7. **Update Book Information**: Modify book information, such as title and author, by providing the ISBN number. The application updates the book's details in the collection.

8. **Generate Reports**: Generate reports that provide statistics on available books, borrowed books, and lost books. The reports can be displayed on-screen or saved to a file.

## Usage

1. Clone this repository to your local machine.
2. Open the project in your Java development environment.
3. Build and run the application.
4. Follow the on-screen prompts to use the various features of the Library Management App.

## Repository and Service Pattern

This application follows the Repository and Service pattern to separate concerns and provide a clean structure for managing the library's data. The key components include:

- **Repository**: Manages data storage and retrieval, ensuring data integrity and consistency.

- **Service**: Contains business logic and interacts with the Repository to provide functionality to the user.

## Dependencies

This application uses Java and does not require any external libraries or dependencies. It is designed to be simple to set up and run on any Java-supported platform.

## Contributions

Contributions to this project are welcome! If you'd like to contribute, please open an issue or submit a pull request.



