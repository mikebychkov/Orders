
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250),
    country CHAR(3),
    address TEXT,
    phone VARCHAR(50)
);

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(250)
);

CREATE TABLE products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    cat_id INT REFERENCES category(id),
    description VARCHAR(100),
    price NUMERIC(8, 2),
    photo VARCHAR(1024)
);

--

CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP,
    cust_id INT REFERENCES customers(id)
);

CREATE TABLE order_details (
    id SERIAL PRIMARY KEY,
    ord_id INT REFERENCES orders(id),
    prod_id INT REFERENCES products(id),
    quantity SMALLINT
);

--

CREATE TABLE invoices (
    id SERIAL PRIMARY KEY,
    ord_id INT REFERENCES orders(id),
    amount NUMERIC(8, 2),
    issued TIMESTAMP,
    due TIMESTAMP
);

CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP,
    amount NUMERIC(8, 2),
    inv_id INT REFERENCES invoices(id)
);

