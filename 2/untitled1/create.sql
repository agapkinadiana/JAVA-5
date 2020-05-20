create database Users; 
use Users;

create table User ( 
	id int primary key auto_increment, 
	name varchar(30) not null, 
    password varchar(30) not null, 
    card_cvc int not null, 
	card_num varchar(30) not null, 
    card_until date not null, 
    card_sum int not null
);