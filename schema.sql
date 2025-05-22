CREATE TABLE user (
                      id        BIGINT AUTO_INCREMENT PRIMARY KEY,
                      nickname  VARCHAR(255) NULL UNIQUE,
                      password  VARCHAR(255) NULL,
                      username  VARCHAR(255) NULL UNIQUE
);

CREATE TABLE board (
                       id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                       content      VARCHAR(255) NULL,
                       created_time DATETIME(6) NULL,
                       title        VARCHAR(255) NULL,
                       view_count   INT NOT NULL,
                       user_id      BIGINT NULL
);

CREATE TABLE hashtag (
                         id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                         tag_name VARCHAR(255) NULL
);

CREATE TABLE board_hashtag (
                               id         BIGINT AUTO_INCREMENT PRIMARY KEY,
                               board_id   BIGINT NULL,
                               hashtag_id BIGINT NULL
);

CREATE TABLE board_like (
                            id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                            board_id BIGINT NULL,
                            user_id  BIGINT NULL
);

CREATE TABLE comment (
                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                         board_id     BIGINT NULL,
                         content      VARCHAR(255) NULL,
                         created_time DATETIME(6) NULL,
                         is_deleted   BIT NOT NULL,
                         user_id      BIGINT NULL
);
