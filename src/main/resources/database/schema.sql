DROP DATABASE IF EXISTS spring_mini_project_db;
CREATE DATABASE spring_mini_project_db;

CREATE TYPE habit_frequency AS ENUM ('DAILY', 'WEEKLY', 'MONTHLY');
CREATE TYPE habit_log_status AS ENUM ('COMPLETED', 'MISSED');

DROP TABLE IF EXISTS app_users;
CREATE TABLE app_users
(
    app_user_id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username      VARCHAR(100)        NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    password      VARCHAR(255)        NOT NULL,
    level         INT                 NOT NULL,
    xp            INT                 NOT NULL,
    profile_image VARCHAR(255),
    is_verified   BOOLEAN             NOT NULL,
    created_at    TIMESTAMP           NOT NULL
);

DROP TABLE IF EXISTS achievements;
CREATE TABLE achievements
(
    achievement_id SERIAL PRIMARY KEY,
    title          VARCHAR(100) NOT NULL,
    description    TEXT,
    badge          VARCHAR(255) NOT NULL,
    xp_required    INT          NOT NULL
);

DROP TABLE IF EXISTS app_user_achievements;
CREATE TABLE app_user_achievements
(
    app_user_id    UUID,
    achievement_id INT ,
    FOREIGN KEY (app_user_id)
        REFERENCES app_users (app_user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (achievement_id)
        REFERENCES achievements (achievement_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS habits;
CREATE TABLE habits
(
    habit_id    SERIAL PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description VARCHAR(250),
    frequency   habit_frequency NOT NULL ,
    is_active   BOOLEAN      NOT NULL,
    app_user_id UUID,
    created_at  TIMESTAMP    NOT NULL,
    FOREIGN KEY (app_user_id) REFERENCES app_users(app_user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS habit_logs;
CREATE TABLE habit_logs
(
    habit_log_id SERIAL PRIMARY KEY,
    log_date     DATE    NOT NULL,
    status       habit_log_status NOT NULL,
    xp_earned    INT     NOT NULL,
    habit_id     INT,
    FOREIGN KEY (habit_id) REFERENCES habits (habit_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

