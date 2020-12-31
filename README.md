# Java Messenger
This project was made regarding the course OOD (Object-Oriented Design) in the fourth-grade at INSA Toulouse, France. The 
specifications are available at [this file](docs/specifications.pdf). The Object-Oriented Design is available in the [docs/modeling](docs/modeling)
folder.

## Installation
This project uses Maven framework to manage dependencies. Please check the [Maven installation guide](https://maven.apache.org/install.html)
to correctly install the project.

## Project structure
The current repository has 3 directories :

### messenger-client
The [messenger-client](messenger-client) folder contains the Messenger user application. Please, check the 
[README.md](messenger-client/README.md) file for more details.

### messenger-tomcat-server
The [messenger-tomcat-server](messenger-tomcat-server) folder contains the presence server side application. It is working
with Tomcat servlets. Please, check the [README.md](messenger-tomcat-server/README.md) file for more details.

### messenger-tools
The [messenger-tools](messenger-tools) folder contains the tools used by the `messenger-client` and the `messenger-tomcat-server`
applications. The `jar` file generated from the folder is loaded in the `lib` folder : [tools.jar](lib/tools.jar). Please, check 
the [README.md](messenger-tools/README.md) file for more details.

## Contributing
The project is currently maintained by Maud Pennetier and Damien Molina, students at INSA Toulouse, France.

## License
[MIT](https://choosealicense.com/licenses/mit/)