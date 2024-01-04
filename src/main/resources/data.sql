INSERT INTO customer (id, national_id, name)
VALUES ('77590ca1-6c7a-4819-8e82-aab1c5386536', '12345', 'John Doe');

INSERT INTO wallet (id, customer_id, balance, created_at)
VALUES ('f9d5b369-89a4-4383-9957-a7bd98236c3f', '77590ca1-6c7a-4819-8e82-aab1c5386536', 500.00, '2024-01-04 12:30:00');
