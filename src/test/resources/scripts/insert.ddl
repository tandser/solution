DELETE FROM meals;
DELETE FROM users;

ALTER SEQUENCE seq_users RESTART WITH 1;
ALTER SEQUENCE seq_meals RESTART WITH 1;

INSERT INTO users (name, email, password, role) VALUES
  ('Lynn Douglas', 'l.douglas@gmail.com', 'Mr01LRc', 'ADMIN'),
  ('Scott Welch',  's.welch@gmail.com',   'Izhyw29', 'USER');

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2017-01-09 10:00:00', 'Breakfast', '700',  2),
  ('2017-01-09 13:00:00', 'Launch',    '1000', 2),
  ('2017-01-09 19:00:00', 'Supper',    '1200', 2),

  ('2017-01-10 10:00:00', 'Breakfast', '800',  2),
  ('2017-01-10 13:00:00', 'Launch',    '1500', 2),
  ('2017-01-10 19:00:00', 'Supper',    '1000', 2),

  ('2017-01-11 10:00:00', 'Breakfast', '500',  2),
  ('2017-01-11 13:00:00', 'Launch',    '1100', 2),
  ('2017-01-11 19:00:00', 'Supper',    '1000', 2),

  ('2017-01-12 10:00:00', 'Breakfast', '500',  2),
  ('2017-01-12 13:00:00', 'Launch',    '1000', 2),
  ('2017-01-12 19:00:00', 'Supper',    '1200', 2);