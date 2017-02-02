DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS seq_users;
DROP SEQUENCE IF EXISTS seq_meals;

-------------------

CREATE SEQUENCE seq_users START 1;

CREATE TABLE users (
  id               INTEGER PRIMARY KEY DEFAULT nextval('seq_users'),
  name             VARCHAR   NOT NULL,
  email            VARCHAR   NOT NULL,
  password         VARCHAR   NOT NULL,
  created          TIMESTAMP NOT NULL DEFAULT now(),
  role             VARCHAR   NOT NULL,
  norm_of_calories INTEGER   NOT NULL DEFAULT 2000,
  version          INTEGER   NOT NULL DEFAULT 0,
  enabled          BOOLEAN   NOT NULL DEFAULT TRUE
);

CREATE UNIQUE INDEX unique_users ON users (email);

-------------------

CREATE SEQUENCE seq_meals START 1;

CREATE TABLE meals (
  id               INTEGER PRIMARY KEY DEFAULT nextval('seq_meals'),
  date_time        TIMESTAMP NOT NULL,
  description      TEXT      NOT NULL,
  calories         INTEGER   NOT NULL,
  user_id          INTEGER   NOT NULL REFERENCES users ON DELETE CASCADE,
  version          INTEGER   NOT NULL DEFAULT 0
);

CREATE UNIQUE INDEX unique_meals ON meals (user_id, date_time);