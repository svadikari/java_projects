
CREATE TABLE PRODUCTS (
    STYLE_NUMBER VARCHAR(32) PRIMARY KEY,
    DESCRIPTION VARCHAR(128),
    SIZE VARCHAR(5),
    COLOR VARCHAR(32),
    PRICE NUMERIC(4,2)
);

CREATE TABLE CUSTOMERS (
    CUSTOMER_ID VARCHAR(52) PRIMARY KEY,
    FIRST_NAME VARCHAR(20),
    LAST_NAME VARCHAR(50),
    EMAIL VARCHAR(128) UNIQUE,
    PHONE VARCHAR(16),
    LINE1 VARCHAR(256),
    LINE2 VARCHAR(256),
    CITY VARCHAR(64),
    STATE CHAR(2),
    ZIP VARCHAR(12)
);

CREATE TABLE ORDERS (
    ORDER_ID VARCHAR(16) PRIMARY KEY,
    CUSTOMER_ID VARCHAR(52),
    COST NUMERIC(4,2)
);

CREATE TABLE ORDER_LINES(
    ORDER_LINE_ID BIGINT PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID VARCHAR(16),
    STYLE_NUMBER VARCHAR(32),
    QUANTITY INT
);

ALTER TABLE ORDERS ADD FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS(CUSTOMER_ID);
ALTER TABLE ORDER_LINES ADD FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID);
ALTER TABLE ORDER_LINES ADD FOREIGN KEY (STYLE_NUMBER) REFERENCES PRODUCTS(STYLE_NUMBER);
