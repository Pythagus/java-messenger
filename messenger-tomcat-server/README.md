```sql
CREATE TABLE `messenger`.`presence` ( 
    `identifier` VARCHAR(255) NOT NULL , 
    `status` ENUM('CONNECTED','DISCONNECTED','IDLE') NOT NULL , 
    UNIQUE (`identifier`)
) ENGINE = InnoDB; 
```