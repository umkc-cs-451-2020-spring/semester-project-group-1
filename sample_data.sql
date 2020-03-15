# SAMPLE TRANSACTION DATA.
# You should temporarily disable (make it a comment) the foreign key constraint in transaction table to be able to add data for
# Account_ID (since we only need to test the controller for now).

use banking_database;
INSERT INTO transaction(Transaction_ID, Transaction_time, Transaction_type, State, Category, Transaction_description, Ammount, Account_ID) values
	(99682762, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Drinks', 
    'Starbucks', 12.00, 43914578),
    
	(25375589, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Deposit', 'Missouri', 'ATM', 
    'ATM', 800.00, 91857321),
    
    (15868859, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Virginia', 'Food', 
    'Chipotle', 8.00, 91857321),
    
    (68832387, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'New York', 'Entertainment', 
    'Spotify', 10.00, 44536856),
    
    (44688000, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Phone', 
    'T-Mobile', 50.00, 88581692),
    					 
    (23045845, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Food', 
    'Prime Sushi', 140.00, 44533685),
    
    (84021545, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Deposit	', 'Missouri', 'Vehicle', 
    'Gas', 25.00, 43914578),
    
    (53654746, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'California', 'Entertainment', 
    'Netflix', 9.99, 91857321),
    							
    (25546753, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Household', 
    'Rent', 800.00, 83247692),
    							
    (69087867, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Drinks', 
    'Starbucks', 5.00, 91857321),
    							
    (92191465, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Deposit', 'Kansas', 'ATM', 
    'ATM', 750.00, 89712654),
    
	(99624073, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Credit Card', 
    'Commerce Bank Credit Card payment', 620.00, 12684369),		
    
    (42539480, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Grocery', 
    'Walmart', 150.00, 32724332),
    							
    (61479614, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Grocery', 
    'Price Chopper', 100.00, 91857321),
    							
    (55715134, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Deposit', 'New Jersey', 'Check', 
    'Check from friend', 50.00, 53775921),
    							
    (32316263, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Deposit', 'Kansas', 'ATM', 
    'ATM', 300.00, 27641392),
    							
	(94492298, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Shopping', 
    'Oak Park Mall', 400.00, 91857321),
    							
    (67417666, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Vehicle', 
    'Big Splash Car Wash', 20.00, 32022985),
    
	(93799711, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Shopping', 
    'Target', 123.00, 32724332),
    
	(73367155, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Missouri', 'Household', 
    'Water', 100.00, 59918266),
    							
	(69085766, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Food', 
    'Shake Sacks', 11.99, 68342957),
    					 	
	(1473351, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Household', 
    'Google Fiber', 70.00, 45886853),

	(12397594, FROM_UNIXTIME(UNIX_TIMESTAMP('2018-01-01 01:01:01') + FLOOR(0 + (RAND() * 63072000))) , 'Withdrawal', 'Kansas', 'Household', 
    'Electric', 150.00, 91857321);