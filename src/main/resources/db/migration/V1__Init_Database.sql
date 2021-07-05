CREATE SEQUENCE author_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE authors (
  id int8 NOT NULL DEFAULT nextval('author_id_seq'),
  firstname VARCHAR (255),
  lastname VARCHAR (255),
  PRIMARY KEY (id));


CREATE SEQUENCE book_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE books (
  id int8 NOT NULL DEFAULT nextval('book_id_seq'),
  title VARCHAR(255),
  shot_desc VARCHAR(255),
  PRIMARY KEY(id));

CREATE TABLE authors_books (
  id_books int8 NOT NULL,
  id_authors int8 NOT NULL,
  PRIMARY KEY (id_books, id_authors),
  FOREIGN KEY (id_authors) REFERENCES authors(id),
  FOREIGN KEY (id_books) REFERENCES books(id));

CREATE SEQUENCE reader_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE readers (
  id int8 NOT NULL DEFAULT nextval('reader_id_seq'),
  firstname VARCHAR (255),
  lastname VARCHAR (255),
  PRIMARY KEY (id));

CREATE SEQUENCE checkout_id_seq
  START WITH 1
  INCREMENT BY 1
  NO MINVALUE
  MAXVALUE 2147483647
  CACHE 1;

CREATE TABLE checkouts (
  id int8 NOT NULL DEFAULT nextval('checkout_id_seq'),
  id_readers int8 NOT NULL,
  checkout_date date,
  PRIMARY KEY (id),
  FOREIGN KEY (id_readers) REFERENCES readers(id));

CREATE TABLE checkouts_books (
  id_books int8 NOT NULL,
  id_checkouts int8 NOT NULL,
  PRIMARY KEY (id_books, id_checkouts),
  FOREIGN KEY (id_books) REFERENCES books(id),
  FOREIGN KEY (id_checkouts) REFERENCES checkouts(id));
