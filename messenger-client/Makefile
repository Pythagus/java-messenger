name?="messenger.jar"

install:
	@cp .properties.example .properties
	@echo "\e[32m=> Installation completed!\e[39m"

jar:
	@jar -cvfm tata.jar src/main/resources/META-INF/MANIFEST.MF target/classes/fr/insa/messenger/Launcher.class
	@echo "\e[32m=> JAR file successfully generated!\e[39m"

run:
	// Run the jar.