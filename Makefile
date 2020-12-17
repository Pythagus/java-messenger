name?="messenger.jar"

install:
	@cp src/main/.properties.example src/main/.properties
	@echo "\e[32m=> Installation completed!\e[39m"

jar:
	@jar cfe ${name} src/main/java/Messenger/Messenger target/classes/Messenger/Messenger.class
	@echo "\e[32m=> JAR file successfully generated!\e[39m"