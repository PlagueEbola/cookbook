--liquibase formatted sql
--changeset roman:create-recipe-table splitStatements:true endDelimiter:;
INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (1, 'Salad', '2022-07-20 13:21:05', 'No mayonnaise', null);

INSERT INTO `cook_book`.`recipe` (`id`, `name`, `creation_time`, `description`, `parent_recipe_id`)
VALUES (2, 'Salad with mayonnaise ', '2022-07-20 13:21:05', 'There is mayonnaise', 1);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (3, 'Salad with more mayonnaise', '2022-07-20 13:21:05', 'Too much mayonnaise', 2);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (4, 'Fried potatoes', '2022-07-20 13:21:05', 'It''s a little burnt', null);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (5, 'Fried potatoes with onions', '2022-07-20 13:21:05', 'Its a little spicy because of the onions', 4);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (6, 'Salad with nuts', '2022-07-20 13:21:05', 'With delicious nuts', 1);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (7, 'Potatoes with lard ', '2022-07-20 13:21:05', 'For lard lovers', 4);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (8, 'Fried potatoes with onions and spices', '2022-07-20 13:21:05', 'The color is golden ', 5);

INSERT INTO cook_book.recipe (id, name, creation_time, description, parent_recipe_id)
VALUES (9, 'Potatoes with onions and spices in the oven', '2022-07-20 13:21:05', 'Now in the oven.', 8);
