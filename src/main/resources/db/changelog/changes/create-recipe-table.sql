--liquibase formatted sql
--changeset roman:create-recipe-table splitStatements:true endDelimiter:;
CREATE TABLE IF NOT EXISTS recipe (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        creation_time DATETIME NOT NULL,
                        description TEXT,
                        parent_recipe_id BIGINT,
                        FOREIGN KEY (parent_recipe_id) REFERENCES recipe(id) ON DELETE CASCADE,
                        is_deleted TINYINT(1) NOT NULL DEFAULT 0
)ENGINE=InnoDB;
--rollback DROP TABLE recipe;

