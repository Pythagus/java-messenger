# Java Messenger
This project was made regarding the course OOD (Object-Oriented Design) in the fourth-grade at INSA Toulouse, France. The 
specifications are available at [this file](docs/specifications.pdf). The Object-Oriented Design is available in the [docs/modeling](docs/modeling)
folder.

## Installation
This project uses Maven framework to manage dependencies. Please check the [Maven installation guide](https://maven.apache.org/install.html)
to correctly install the project.

### Database
This part describes the mandatory steps to make the database working.

#### Tables
In the server hosting the database, you need to create a table *messages* using the following command:
```sql
CREATE TABLE `messages` (
    `id` bigint UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    `user_sender` varchar(255) NOT NULL,
    `user_receiver` varchar(255) NOT NULL,
    `type` enum('MESSAGE','FILE') NOT NULL,
    `content` longtext NOT NULL,
    `sent_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;
```

#### Credentials
The project is provided with a ```src/main/.properties``` file listing all the application credentials.
All the credentials starting by ```DB_``` are referring to the database.

- ```DB_HOST ``` IP address to the database.
- ```DB_DATABASE``` Database name. In this project, you should keep **messenger**.
- ```DB_USER``` Loggable user's name.
- ```DB_PASSWORD``` Loggable user's password.

**Note :** The ```DB_CONNECTION``` and ```DB_PORT``` cannot be modified. For now, the project is only
supporting *MySQL* connection at port 3306.

## Contributing
The project is currently maintained by Maud Pennetier and Damien Molina, students at INSA Toulouse, France.

## License
[MIT](https://choosealicense.com/licenses/mit/)