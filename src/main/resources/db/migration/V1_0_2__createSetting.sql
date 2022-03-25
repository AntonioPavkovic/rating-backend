CREATE TABLE IF NOT EXISTS setting (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    emotion_number int NOT NULL,
    message varchar(128),
    message_timeout int NOT NULL
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO setting (emotion_number,message, message_timeout) VALUES (3,'Thank you for rating.',5);