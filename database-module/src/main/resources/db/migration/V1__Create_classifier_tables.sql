-- Создание основной таблицы classifier_groups
CREATE TABLE classifier_groups (
    id TEXT PRIMARY KEY NOT NULL,
    min_value INTEGER NOT NULL,
    max_value INTEGER NOT NULL,
    level_value INTEGER NOT NULL,
    name TEXT NOT NULL,
    parent_id TEXT,
    FOREIGN KEY (parent_id) REFERENCES classifier_groups(id)
);

-- Создание таблицы для связи родитель-ребенок
CREATE TABLE classifier_group_children (
    parent_id TEXT NOT NULL,
    child_id TEXT NOT NULL,
    PRIMARY KEY (parent_id, child_id),
    FOREIGN KEY (parent_id) REFERENCES classifier_groups(id) ON DELETE CASCADE,
    FOREIGN KEY (child_id) REFERENCES classifier_groups(id) ON DELETE CASCADE
);

-- Индекс для ускорения поиска по parent_id
CREATE INDEX idx_classifier_groups_parent_id ON classifier_groups(parent_id);