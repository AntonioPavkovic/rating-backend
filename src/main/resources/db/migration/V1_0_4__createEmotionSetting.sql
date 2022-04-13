CREATE TABLE IF NOT EXISTS emotion_setting (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    emotion_id int NOT NULL,
    emotion_value int NOT NULL,
    constraint emotion_id_emotion_setting_constraints_emotion_id_emotion foreign key(emotion_id) references emotion(id)
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO emotion_setting (emotion_id,emotion_value) VALUES  (5, 3),
                                                               (3, 3),
                                                               (1, 3),
                                                               (5, 4),
                                                               (4, 4),
                                                               (2, 4),
                                                               (1, 4),
                                                               (5, 5),
                                                               (4, 5),
                                                               (3, 5),
                                                               (2, 5),
                                                               (1, 5);