# Release Notes
This file contains all the release notes regarding the Messenger version.

## [v1.0.0 (2021-01-08)](https://github.com/Pythagus/java-messenger/compare/v0.2.0...v1.0.0)
Major update.

### Added
- Tomcat presence server.
- Tools package including Database tools.
- File support.

### Updated
- Repository architecture. The repository is now including tools used by the tomcat and the application sides.
- Database tool major upgrade. The queries are now abstract and have a clause manager.

## [v0.2.0 (2020-12-18)](https://github.com/Pythagus/java-messenger/compare/v0.1.2...v0.2.0)

### Added
- Database usage for message histories.
- Logout broadcast notification.
- Leave a conversation.
- HTTP request maker.
- Application starter.

### Updated
- Packages name according to the Oracle package naming convention.
- Singleton design pattern for NetworkInterface and the controllers.
- UI major update.

## [v0.1.2 (2020-12-10)](https://github.com/Pythagus/java-messenger/compare/v0.1.1...v0.1.2)

### Added
- Starter support to correct null graphics error.
- Pseudo verification.
- Contact list.

### Updated
- Launcher clean.

### Bug fixed
- Timestamp translation.

## [v0.1.1 (2020-12-08)](https://github.com/Pythagus/java-messenger/compare/v0.1.0...v0.1.1)

### Added
- Database support.

## v0.1.0 (2020-12-08)

### Added
- Broadcast notification listening.
- Broadcast notification sending on specific received message type.

### Changed 
- Adding Maven framework support