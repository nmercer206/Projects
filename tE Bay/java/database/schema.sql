BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	name varchar(25) NOT NULL,
	address varchar(60) NOT NULL,
	phone_number varchar(20) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE auction (
	auction_id SERIAL,
	owner_id int,
	title varchar(50),
	description varchar(400),
	starting_price numeric,
	winning_bid_id int,
	start_date timestamp,
	end_date timestamp,
	image_path varchar(200),
	CONSTRAINT pk_auction PRIMARY KEY (auction_id)
);

CREATE TABLE auction_type (
	auction_type_id int,
	name varchar(50),
	CONSTRAINT pk_auction_type PRIMARY KEY (auction_type_id)
);

CREATE TABLE bid (
	bid_id SERIAL,
	auction_id int,
	user_id int,
	amount numeric,
	CONSTRAINT pk_bid PRIMARY KEY (bid_id)
);

COMMIT TRANSACTION;
