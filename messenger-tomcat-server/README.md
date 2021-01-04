# Java Messenger - Tomcat server
This project was made regarding the course OOD (Object-Oriented Design) in the fourth-grade at INSA Toulouse, France. The 
specifications are available at [this file](docs/specifications.pdf). The Object-Oriented Design is available in the [docs/modeling](docs/modeling)
folder.

## Installation
This project uses Maven framework to manage dependencies. Please check the [Maven installation guide](https://maven.apache.org/install.html)
to correctly install the project.

### Project

##### Configuration file
You will find a config file `src/main/.properties.example` which is an example of the project configurations. You
need to copy this file in a `.properties` file used by the application with the Unix command:
```bash
$ cp src/main/.properties.example src/main/.properties
```

**Note :** please refer to the Database section to correctly specify the database credentials.

### Database
This part describes the mandatory steps to make the database working.

#### Tables
In the server hosting the database, you need to create a table *messages* using the following command:
```sql
CREATE TABLE `messenger`.`presence` ( 
    `identifier` VARCHAR(255) NOT NULL , 
    `address` VARCHAR(255) NOT NULL ,
    `status` ENUM('CONNECTED','DISCONNECTED','IDLE') NOT NULL , 
    UNIQUE (`identifier`)
) ENGINE = InnoDB; 
```

#### Credentials
The project is provided with a `.properties` file listing all the application credentials.
All the credentials starting by `DB_` are referring to the database.

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