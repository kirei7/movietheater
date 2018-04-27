/*Aspect tables for jdbcTemplate*/
DROP TABLE IF EXISTS accessed_price;
CREATE TABLE accessed_price (
  id BIGINT PRIMARY KEY,
  counter BIGINT
);
DROP TABLE IF EXISTS accessed_name;
CREATE TABLE accessed_name (
  id BIGINT PRIMARY KEY,
  counter BIGINT
);
DROP TABLE IF EXISTS tickets_bought;
CREATE TABLE tickets_bought (
  id BIGINT PRIMARY KEY,
  counter BIGINT
);