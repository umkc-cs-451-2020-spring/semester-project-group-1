drop database banking_database;
create database banking_database;
use banking_database;

create or replace TABLE user(
	User_ID int PRIMARY KEY,
    Email varchar(45),
    User_password varchar(45),
    Phone_number varchar(20)
)engine = InnoDB;

create or replace TABLE account(
	Account_ID int PRIMARY KEY,
    Account_type varchar(45),
    Balance float,
    User_ID int,
    CONSTRAINT FOREIGN KEY USER_ID (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;

create or replace TABLE transaction(
	Transaction_ID int PRIMARY KEY,
    Transaction_type varchar(255),
    Transaction_time datetime,
    State varchar(2),
    Category varchar(45),
    Transaction_description text,
    Ammount float,
    Account_ID int,
    CONSTRAINT FOREIGN KEY ACCOUNT_ID (Account_ID) REFERENCES account(Account_ID)
)engine = InnoDB;

create or replace TABLE notification(
	Notification_ID int AUTO_INCREMENT,
	Trigger_ID int NOT null,
	Notification_type varchar(255),
    Transaction_ID int DEFAULT NULL,
    CONSTRAINT FOREIGN KEY TRANSACTION_ID (Transaction_ID) REFERENCES transaction(Transaction_ID),
    constraint primary key notification_pk (Notification_ID, Trigger_ID, Notification_Type)
)engine = InnoDB;

create or replace TABLE state_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    CONSTRAINT FOREIGN KEY USER_ID1 (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;

create or replace TABLE category_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    CONSTRAINT FOREIGN KEY USER_ID2 (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;

create or replace TABLE time_range_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    CONSTRAINT FOREIGN KEY USER_ID3 (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;

    
    
    