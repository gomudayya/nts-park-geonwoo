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

CREATE INDEX idx_board_id ON board_hashtag (board_id);
CREATE INDEX idx_hashtag_id ON board_hashtag (hashtag_id);

CREATE TABLE board_like (
                            id       BIGINT AUTO_INCREMENT PRIMARY KEY,
                            board_id BIGINT NULL,
                            user_id  BIGINT NULL
);

CREATE INDEX idx_board_user ON board_like (board_id, user_id);
CREATE INDEX idx_user_id ON board_like (user_id);

CREATE TABLE comment (
                         id           BIGINT AUTO_INCREMENT PRIMARY KEY,
                         board_id     BIGINT NULL,
                         content      VARCHAR(255) NULL,
                         created_time DATETIME(6) NULL,
                         is_deleted   BIT NOT NULL,
                         user_id      BIGINT NULL
);
