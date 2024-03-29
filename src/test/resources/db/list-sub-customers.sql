INSERT INTO sub_customer (id, customer_id, tax_id, first_name, last_name, comment, address_street, address_house_number, address_zip_code, address_city, address_country) VALUES
('af499571-75a5-4ab6-8403-42c5c5506a51', '06fbb35a-eba7-4179-a856-62da9c3b9375','DE111111111','Jane','Doe',NULL,NULL,NULL,NULL,NULL,NULL),
('c7d26013-7fb9-4a84-8b9b-2cde1909fe33', '06fbb35a-eba7-4179-a856-62da9c3b9375','ATU1111111','John','Doe','some comment','Am Prater','4B','12345','Wien','Deutschland'),
('9f21e41c-0076-4372-8bd3-5ea2144fee74', 'c3b9edd7-1a2d-4102-a7eb-8545d6c2be8a','DE111111111','Jane','Doe','duplicate because another customer has this sub-customer too','Hauptstr.','1','11223','Freiburg','Deutschland');
