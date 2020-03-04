use commerce_monitor;

Create TABLE user(
	User_ID int PRIMARY KEY,
    Email varchar(45),
    User_password varchar(45),
    Phone_number varchar(20)
)engine = InnoDB;
​
Create TABLE account(
	Account_ID int PRIMARY KEY,
    Account_type varchar(45),
    Balance float,
    User_ID int,
    CONSTRAINT FOREIGN KEY USER_ID (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;
​
Create TABLE transaction(
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
​
Create TABLE notification(
	Notification_ID int PRIMARY KEY,
    Transaction_ID int DEFAULT NULL,
    Notification_type varchar(255),
    Notification_description text,
    CONSTRAINT FOREIGN KEY TRANSACTION_ID (Transaction_ID) REFERENCES transaction(Transaction_ID)
)engine = InnoDB;
​
Create TABLE state_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    Trigger_description text,
    CONSTRAINT FOREIGN KEY USER_ID (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;
​
Create TABLE category_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    Trigger_description text,
    CONSTRAINT FOREIGN KEY USER_ID (User_ID) REFERENCES user(User_ID)
)engine = InnoDB;
​
Create TABLE time_range_trigger(
	Trigger_ID int PRIMARY KEY AUTO_INCREMENT,
    User_ID int DEFAULT NULL UNIQUE,
    Trigger_rule varchar(255),
    Trigger_description text,
    CONSTRAINT FOREIGN KEY USER_ID (User_ID) REFERENCES user(User_ID)
)