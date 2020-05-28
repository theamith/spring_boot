drop table if exists products;

create table products(id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  count int NOT NULL,
  price double NOT NULL
);