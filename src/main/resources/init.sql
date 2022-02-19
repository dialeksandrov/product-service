insert into products (id, amount, created_dt, currency_prop, description, language_prop, modified_dt, product_name)
values (1, 10.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'RUB', 'Тестовый продукт', 'RU', null, 'Тест1'),
       (2, 20.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'RUB', 'Тестовый продукт', 'RU', null, 'Тест2'),
       (3, 30.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'RUB', 'Тестовый продукт', 'RU', null, 'Тест3'),
       (4, 40.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'RUB', 'Тестовый продукт', 'RU', null, 'Тест4'),
       (5, 55.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'RUB', 'Тестовый продукт', 'RU', null, 'Тест5'),
       (6, 66.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'USD', 'Test product', 'EN', null, 'Test'),
       (7, 77.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'USD', 'Test product', 'EN', null, 'Test1'),
       (8, 99.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'USD', 'Test product', 'EN', null, 'Test2'),
       (9, 134.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'EUR', 'Test product', 'EN', null, 'Test3'),
       (10, 222.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'EUR', 'Test product', 'EN', null, 'Test4'),
       (11, 333.0, to_timestamp('16-05-2011 15:36:38', 'dd-mm-yyyy hh24:mi:ss'), 'EUR', 'Test product', 'EN', null, 'Test5');