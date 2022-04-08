# Rating-app


### What is it all about?
*Rating application is a simple application that is used for simple
rating of experience inside certain facility. It is usually used at 
exits on airports, restaurants, banks etc.This repository contains spring 
boot backend service api which is protected by oAuth2.0*

---
### What technologies we used?

- Java 
- Maven
- Spring boot
- Docker
- Docker-compose
- Flyway
- OAuth2
- Pusher
- Slack api
- Swagger
---
### What are the prerequisites for application to start?

*You only need to have docker and docker compose services installed 
on your machine.*

---

### How to run the application?
*All you need to do is clone this repository to your machine,
position yourself in a rating-app folder and run docker-compose up -d command.
It will execute docker-compose.yml file which contains these three services*:
- Mariadb database
- Phpmyadmin DBMS
- Rating application

*It will first download mariadb and pma images from maven remote repository if
you don't have one locally yet.Then it needs rating application image, which will
be created from Dockerfile.It will download fresh image and mount our application in.
After mounting is over, it will boot all up, by the way, script will initiate DB creation
and flyway will create migrations on first boot.
You are all set up, you have:*
- port: 9000-**Rating app**
- port: 8000-**Phpmyadmin**
- port: 3306-**Mariadb**

Database credentials:
- Username:**root**
- Password:**root**
---

### Database and available endpoints

*Database rating_app consists of five tables*:
- **Emotion**
- **Setting**
- **Rating**
- **Emotion-Setting**
- **Custom-User**

### Emotion 

*This table holds information about available emotion(emoji), by default 
it has 5 rows, each row has emotion id, name and color.*

---
### Setting

*This table holds information about current setting, it has only one row 
with 3 columns, number of emotions(emojis) which is set to user view,
message that will pop after user rates some service and message delay column,
which holds information for how long should message stay on screen before it 
resets to a previous screen.
There are some validation rules applied to these columns:*

**Number of emotions:**

- **minimum value: 3**
- **maximum value: 5**
- **default value: 3**

**Message:**

- **minimum length: 3**
- **maximum length: 120**
- **default: Thank you for rating**

**Message timeout:**

- **minimum: 0**
- **maximum: 10**
- **default: 5**
---
### Rating

*This table holds information about all ratings applied.It stores data about 
when the rating is applied(createdAt) and which emotion is applied(emotionId).
This table is connected to rating table with **Many-To-One** relationship.*

---
### Emotion-Setting

*This table holds information about order in which the emotions will be presented
on the user-view depending on which setting(number of emotion) is set.
This table is connected on emotion with **Many-To-One** relationship.*

---
### Custom-User

*This table will hold information about our users which have access to some endpoints
that are protected with OAuth2 protocol*.

---
### Available endpoints

**Public:**

- GET "http://localhost:9000/api/v1/settings" (returns current settings and list of emojis)
- POST "http://localhost:9000/api/v1/ratings" (creates rating based on emotionId)

**Protected:**
- PATCH "http://localhost:9000/api/v1/settings" (updates setting table)
- GET "http://localhost:9000/api/v1/ratings" (returns a list of ratings for passed range arguments)

---

### Authorization

*For authorization, we used OAuth2 protocol(Implicit flow because our frontend is Single page app).
To be able to access our protected endpoints you should provide Bearer token
in header of every request(because Session Management is set to **Stateless**)*

---

### Pusher

*In our project, we also used Pusher, JSON based protocol used by clients to communicate with Pusher Channels,
mainly over a ***WebSocket*** connection.With pusher, we managed to send information
trough channel when table setting gets updated so the front side can gather information
and apply changes without page refresh.*

---

### Slack

*We also used ***Slack api*** for sending messages to Slack channel once per a day 
if number of daily ratings is lower than expected.*






