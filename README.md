# Bookmate Network

Welcome to the Bookmate Network, a platform designed for book lovers to manage, share, and review books. Built with Java, Spring Boot, and Angular, this application offers a rich set of features for an engaging reading experience.

## Features

- Manage and share book details
- Borrow and rate books
- Secure authentication with Spring Security
- Custom queries with Spring Data JPA
- Responsive and intuitive UI with Angular

## Technologies Used

- **Java**: Backend development
- **Spring Boot**: Backend services and REST API
- **Spring Security**: User authentication and security
- **Spring Data JPA**: Database interactions
- **MySQL**: Relational database
- **Angular**: Frontend development and responsive design

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 11 or later
- Node.js and npm (for Angular)
- MySQL 5.7 or later

## Installation

Follow these steps to set up the project:

### Backend

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```
2. Navigate to the backend directory:
    ```bash
    cd book-network
    ```
3. Configure your `application.properties` file with your database credentials and other settings.
4. Build and run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

### Frontend

1. Navigate to the frontend directory:
    ```bash
    cd book-network-UI
    ```
2. Install dependencies:
    ```bash
    npm install
    ```
3. Start the Angular development server:
    ```bash
    ng serve
    ```

## Usage

1. Start the backend server by running `./mvnw spring-boot:run` from the backend directory.
2. Start the Angular frontend by running `ng serve` from the frontend directory.
3. Open your browser and navigate to `http://localhost:4200` to access the application.

## Troubleshooting

If you encounter issues:

- Ensure all services are running (backend and frontend).
- Check the console for error messages and consult the logs for more details.
- Verify your database configuration and connectivity.

## FAQ

**Q: How do I reset my password?**  
A: Use the "Forgot Password" feature on the login page to reset your password.

**Q: Can I contribute to the project?**  
A: Yes! Please fork the repository and submit a pull request with your changes.

## Contributing

To contribute to this project:

1. Fork the repository.
2. Create a feature branch:
    ```bash
    git checkout -b feature-branch
    ```
3. Commit your changes:
    ```bash
    git commit -m "Add new feature"
    ```
4. Push to the branch:
    ```bash
    git push origin feature-branch
    ```
5. Submit a pull request with a description of your changes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- **Spring Boot**: For the powerful backend framework.
- **Angular**: For a responsive and dynamic frontend experience.
- **MySQL**: For reliable database management.

## Contact

For any questions or feedback, please reach out to the project maintainers:

- [Tanmay Dhanvijay](tanmaydhanvijay0586@gmail.com)

## Future Enhancements

- **Deployment**: Plans to deploy the application to a cloud service.
- **Testing**: Integration of JUnit for unit testing and ensuring code quality.
