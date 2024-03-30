-- Задача 7. В подключенном MySQL репозитории создать базу данных Друзья человека --
--Создать базу данных human_friends, если она ещё не создана --
CREATE DATABASE IF NOT EXISTS human_friends;

-- Задача 8. Создать таблицы с иерархией из диаграммы в БД --
-- Использовать базу данных human_friends --
USE human_friends;

-- Создать таблицу Список команд животным --
CREATE TABLE animals_commands_list
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30)
);
-- Создать таблицу Группы животных --
CREATE TABLE animals_group
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30)
);

-- Создать таблицу Виды животных --
CREATE TABLE animals_species
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    group_id INT,
    FOREIGN KEY (group_id) REFERENCES animals_group (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создать таблицу Животные в питомнике --
CREATE TABLE animals_in_kennel
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name varchar(30),
    birth_date DATE,
    species_id INT,
    FOREIGN KEY (species_id) REFERENCES animals_species (id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создать таблицу Команды, выполняемые животными --
CREATE TABLE commands_executed_animals
(
    animal_id INT NOT NULL,
    command_id INT NOT NULL,

    PRIMARY KEY (animal_id, command_id),
    FOREIGN KEY (animal_id) REFERENCES animals_in_kennel (id)
     ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (command_id) REFERENCES animals_commands_list (id)
     ON DELETE CASCADE  ON UPDATE CASCADE
);

-- Задача 9. Заполнить низкоуровневые таблицы именами(животных), командами, которые они выполняют и датами рождения --
-- Заполнить данными таблицу Список команд животным --
INSERT INTO animals_commands_list (name)
VALUES
 ('Сидеть'),
 ('Лежать'),
 ('Бежать'),
 ('Галоп'),
 ('Идти'),
 ('Стоять');

-- Заполнить данными таблицу Группы животных --
INSERT INTO animals_group (name)
VALUES
 ('Домашние животные'),
 ('Вьючные животные');

-- Заполнить данными таблицу Виды животных --
INSERT INTO animals_species (name, group_id)
VALUES
 ('Собака', 1),
 ('Кот', 1),
 ('Хомяк', 1),
 ('Лошадь', 2),
 ('Верблюд', 2),
 ('Осёл', 2);

-- Заполнить данными таблицу Животные в питомнике --
INSERT INTO animals_in_kennel (name, birth_date, species_id)
VALUES
 ('Боксёр', '2019-05-10', 1),
 ('Борис', '2020-02-15', 2),
 ('Хома', '2018-11-20', 3),
 ('Морган', '2017-07-03', 4),
 ('Вася', '2016-09-25', 5),
 ('Жора', '2019-12-08', 5),
 ('Моисей', '2022-07-12', 6);

-- Заполнить данными таблицу Команды, выполняемые животными --
INSERT INTO commands_executed_animals (animal_id, command_id)
VALUES
(1, 1), (1, 2), (1, 3), (1, 5), (1, 6),
(2, 1), (2, 2), (2, 3), (2, 5), (2, 6),
(3, 3),
(4, 4), (4, 5), (4, 6),
(5, 5), (5, 6),
(6, 5), (6, 6),
(7, 5), (7, 6);

-- Задача 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку, объединить таблицы лошади, и ослы в одну таблицу --
DELETE FROM animals_in_kennel WHERE species_id = 5;

   CREATE TABLE horses_and_donkeys AS
   SELECT * from animals_in_kennel WHERE species_id = 4
   UNION
   SELECT * from animals_in_kennel WHERE species_id = 6;

-- Задача 11. Создать новую таблицу Молодые животные, в которую попадут все животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице --
   CREATE TABLE young_animals AS
    SELECT
        id,
        name,
        birth_date,
        TIMESTAMPDIFF(MONTH, birth_date, CURDATE()) AS age_months,
        species_id
    FROM
        animals_in_kennel
    WHERE
        birth_date <= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
        AND birth_date >= DATE_SUB(CURDATE(), INTERVAL 3 YEAR);

-- Задача 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам --

   SELECT id, name, birth_date, species_id FROM horses_and_donkeys
   UNION
   SELECT id, name, birth_date, species_id FROM young_animals;
