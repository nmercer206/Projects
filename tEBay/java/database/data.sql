BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,name,address,phone_number) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','Ben', '1234 Bid Street', '123-456-7890');
INSERT INTO users (username,password_hash,role,name,address,phone_number) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN','Rob', '1234 Bid Street', '123-456-7890');
INSERT INTO users (username,password_hash,role,name,address,phone_number) VALUES ('TestUser1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','Tim', '1234 Bid Street', '123-456-7890');
INSERT INTO users (username,password_hash,role,name,address,phone_number) VALUES ('TestUser2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER','Nick', '1234 Bid Street', '123-456-7890');

INSERT INTO auction_type (auction_type_id, name) VALUES (1, 'Test Auction');

INSERT INTO auction (owner_id, title, description, starting_price, start_date, end_date, image_path)
VALUES (1,'Test Auction', 'This is a test auction', 0.01, NOW(), '2022-12-16 11:30:00', 'https://images.pexels.com/photos/89778/strawberries-frisch-ripe-sweet-89778.jpeg'),
       (1,'Test Auction #2', 'This is another test auction', 0.02, NOW(), '2022-12-16 11:31:00', 'https://images.pexels.com/photos/209339/pexels-photo-209339.jpeg'),
       (1,'Test Auction #3', 'This is yet, another test auction', 0.03, NOW(), '2022-12-16 11:32:00', 'https://images.pexels.com/photos/61127/pexels-photo-61127.jpeg');

INSERT INTO bid (auction_id, user_id, amount) VALUES (1, 4, 200.00);
INSERT INTO bid (auction_id, user_id, amount) VALUES (1, 3, 300.00);
INSERT INTO bid (auction_id, user_id, amount) VALUES (2, 3, 300.00);
INSERT INTO bid (auction_id, user_id, amount) VALUES (2, 4, 400.00);

COMMIT TRANSACTION;
