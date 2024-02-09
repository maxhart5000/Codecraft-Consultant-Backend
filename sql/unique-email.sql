USE `full-stack-ecommerce`;

-- Clean up previous database tables

SET FOREIGN_KEY_CHECKS=0;

TRUNCATE customer;
TRUNCATE orders;
TRUNCATE order_item;
TRUNCATE address;

SET FOREIGN_KEY_CHECKS=1;

-- Make the email address unique 

ALTER TABLE customer ADD UNIQUE (email);
