CREATE TABLE sub_customer (
    id UUID UNIQUE NOT NULL,
    customer_id UUID NOT NULL,
    tax_id VARCHAR(20) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    comment VARCHAR(100) NULL,
    address_street VARCHAR(255) NULL,
    address_house_number VARCHAR(20) NULL,
    address_zip_code VARCHAR(20) NULL,
    address_city VARCHAR(100) NULL,
    address_country VARCHAR(20) NULL,
    UNIQUE(customer_id, tax_id)
)