INSERT INTO rdp.customer (id, name) VALUES (1, 'Jonas');
INSERT INTO rdp.customer (id, name) VALUES (2, 'Peter');
INSERT INTO rdp.customer (id, name) VALUES (3, 'Stefan');


INSERT INTO rdp.category (id, name) VALUES (1, 'Hotel');
INSERT INTO rdp.category (id, name) VALUES (2, 'Apartment');

INSERT INTO rdp.supplementary_package (id, name, price) VALUES (1, 'Vollpension', 2000);
INSERT INTO rdp.supplementary_package (id, name, price) VALUES (2, 'Halbpension', 1000);


INSERT INTO rdp.apartment (id, max_guests, name, price_per_day, category_id) VALUES (1, 2, 'Hotelzimmer Wien', 7000, 1);
INSERT INTO rdp.apartment (id, max_guests, name, price_per_day, category_id) VALUES (2, 2, 'Hotelzimmer Linz', 6000, 1);
INSERT INTO rdp.apartment (id, max_guests, name, price_per_day, category_id) VALUES (3, 5, 'Apartment Obertraun', 12000, 2);