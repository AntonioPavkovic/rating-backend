CREATE TABLE IF NOT EXISTS custom_user (
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email varchar(250) UNIQUE,
    custom_user_role varchar(250) NOT NULL
    )ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO custom_user (email,custom_user_role) VALUES ('mdugandzic67@gmail.com','ADMIN'),
                                                        ('antoniopavkovic999@gmail.com','ADMIN'),
                                                        ('mar.tomo123@gmail.com','ADMIN'),
                                                        ('danijelsain2012@gmail.com','ADMIN');

