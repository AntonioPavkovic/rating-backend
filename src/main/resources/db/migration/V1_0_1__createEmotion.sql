CREATE TABLE IF NOT EXISTS emotion (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    color varchar(250) NOT NULL,
    name varchar(250) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO emotion (color,name) VALUES ('red','bad'),
                                        ('yellow','very dissatisfied'),
                                        ('gray','dissatisfied'),
                                        ('blue','satisfied'),
                                        ('green','very satisfied');
