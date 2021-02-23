-- CREATE DATABASE Bookstore;

DROP TABLE Admin, Busket, Orders, Customers, Books;

CREATE TABLE IF NOT EXISTS Customers (
	Customer_Login varchar(100) PRIMARY KEY,
	Customer_Password text NOT NULL CHECK (char_length(Customer_Password) >= 4),
	LastName varchar(100) NOT NULL,
	FirstName varchar(100) NOT NULL,
	Address varchar(200) NOT NULL,
	Phone_number varchar(100) NOT NULL,
	Mail varchar(100)
);

CREATE TABLE IF NOT EXISTS Orders (
	Order_id Serial PRIMARY KEY,
	Customer_Login varchar REFERENCES Customers (Customer_Login),
	Address varchar(200) NOT NULL,
	Delivery_time timestamptz NOT NULL,
	Status varchar(100) NOT NULL,
	Order_time timestamptz NOT NULL,
	Order_price real NOT NULL,
	Order_list text NOT NULL
);

CREATE TABLE IF NOT EXISTS Books (
	Book_id Serial PRIMARY KEY,
	Price real NOT NULL,
	Amount integer NOT NULL,
	Title varchar(100) NOT NULL,
	Author varchar(100) NOT NULL,
	Genre varchar(100) NOT NULL,
	Pub_house varchar(100) NOT NULL,
	Pub_Year integer NOT NULL,
	Num_pages integer NOT NULL,
	Cover_type varchar(100) NOT NULL
);
	
CREATE TABLE IF NOT EXISTS Busket (
	Customer_Login varchar(100) REFERENCES Customers (Customer_Login),
	Book_id integer REFERENCES Books (Book_id),
	Amount integer NOT NULL
);

CREATE TABLE IF NOT EXISTS Admin (
	Admin_login varchar(100) PRIMARY KEY,
	Admin_Password text NOT NULL CHECK (char_length(Admin_Password) >= 4)
);

