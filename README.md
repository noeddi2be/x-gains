<img src="https://github.com/noeddi2be/x-gains/blob/main/www/spring-x-gains.png?raw=true" width="550" height="200">


# [👉🏼 Project Management File](https://github.com/noeddi2be/x-gains/blob/main/PROJECT-MANAGEMENT.md)

# ☕️ Use Case: X-Gains

We have created a fitness tracker where users can create an account, <br>
log in, add & modify workouts and track their activities and exercises. <br>
The data is persisted in an H2 in-memory database and deployed on Render <br>
in a Docker container.

The application was created using an MVC design pattern. <br>
The application is divided into different layers: 
- Presentation Layer: Frontend (Built with Budibase), Controller classes
- Application Layer: Service classes
- Domain Layer: Configuration classes, Model entities
- Persistence Layer: Data access object, Repository classes
- Database Layer: SQL statements, Database schema (H2)

We have used Maven for dependency management as well as for project building, <br>
ensuring a consistent build process for deployment on Render. <br>
The project was built using Spring Boot, which simplifies the initial Spring setup <br>
and leveraging the robust enterprise capabilities of the Spring Framework.

We have configured an API using RestController annotations of Spring Boot and <br>
adjusted our Swagger UI for documentation purposes.

We have included several testing classes to ensure proper functioning of our <br>
application using JUnit. Controller classes are tested with Postman and <br>
can also be tried out on the Swagger-UI.

Security is handled using the *spring-boot-starter-security* dependency. <br>
At the moment, basic authentication is used to access the API Endpoints. <br>
Credentials are validated using the user entity in the H2 database. <br>
Passwords are stored hashed and decrypted using <br>
a password encoder from org.springframework.security.crypto.

### Links
The following services are live at the moment of project delivery and will be <br>
turned off later on. The free version of Render is used, which requires some <br>
loading time (~3 min) for the initial request to startup the system.

#### API Documentation & Testing
https://xgains-render.onrender.com/swagger-ui/index.html

#### H2 Database (Accessible ATM)
https://xgains-render.onrender.com/h2-console

#### Budibase
http://example.com

<br>

## Project Contributors
- Silvana Rey
- Dominik Schneider
- Boris Vittek
- Manuel Notter
<br>

## 📱 App Content

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/thumbnail.jpg?raw=true" width="300" height="300">

<br>

### 🏋🏽 Supported Exercises

| No. | Exercise | Attribute 1| Attribute 2| Attribute 3| 
| --- | --- | --- | --- | --- |
| 1 | Bench Press | Sets | Repetitions | Weight |
| 2 | Burpees | Sets | Repetitions | - |
| 3 | Rowing | Time | Distance | - |
| 4 | Weighted Squats | Sets | Repetitions | Weight |
| 5 | Mountain Climbers | Time | Repetitions | - |
| 6 | Pull Ups | Sets | Repetitions | Weight |
| 7 | Push Ups | Sets | Repetitions | - |
| 8 | Skull Crushers | Sets | Repetitions | Weight |
| 9 | Swimming | Time | Distance | - |
| 10 | Dead Lift | Sets | Repetitions | Weight |
| 11 | Skip Rope | Sets | Time | - |
| 12 | Arnold Press | Sets | Repetitions | Weight |
| 13 | Biceps Curl | Sets | Repetitions | Weight |
| 14 | Thrusters | Sets | Repetitions | Weight |
| 15 | Chest Flys | Sets | Repetitions | Weight |
| 16 | Tricep Extension | Sets | Repetitions | Weight |
| 17 | Lat Pull | Sets | Repetitions | Weight |
| 18 | Running | Time | Distance | - |
| 19 | Sprints | Sets | Time | Distance |
| 20 | Box Jumps | Sets | Repetitions | Height |

<br>
<br>

## 🧩 Scenario and User Stories

X-Gains is a basic fitness tracking app designed to assist our users in achieving their health <br>
and fitness goals. Whether you are a beginner starting your fitness journey or a more advanced athlete <br>
looking to optimize your workouts, X-Gains provides a user-friendly platform packed with robust features <br>
to support you every step of the way. X-Gains is more than just a fitness tracker; it is a tool designed <br>
to empower users to take control of their health and fitness, stay motivated, and achieve their desired <br>
results. X-Gains is your ultimate companion on the path to a healthier, stronger you.

<br>

## 🔧 ER-Model

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/er-model.png?raw=true" width="840" height="450">
<br>

## 🔧 Wireframe User

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/wireframe.png?raw=true" width="900" height="550">
<br>

### 🧩 User Stories
**🟠 Admin Role**
* As an admin, I want to have a Web app so that I can use it on different mobile devices and on desktop computers.
* As an admin, I want to be able to log in securely to the X-Gains app to access administrative features.
* As an admin, I want to see a consistent visual appearance so that I can navigate easily, and it looks consistent.
* (Optional) As an admin, I want to have a dashboard displaying key metrics such as total users and activity summaries.
* As an admin, I want to be able to add, edit, or remove different types of exercises to the app's database.
* (Optional) As an admin, I want to be able to manage user accounts, including creating new accounts, resetting passwords, and deactivating accounts if necessary.

<br>
<br>

**🟠 User Role**
* As a user, I want to be able to sign up for an account on the X-Gains app to track my fitness progress.
* As a user, I want to authenticate myself so that I can read my personal and confidential data.
* As a user, I want to see and set the gym location.
* As a user, I want to have a personalized dashboard showing my recent activity.
* As a user, I want to be able to create and log my daily exercises, including type, duration, and intensity, to track my progress over time.
* (Optional) As a user, I want to see the data from the previous workout next to the current workout, so I do not have to check the old log.
* (Optional) As a user, I want to be able to view my past workout history and analyze my performance trends.
* (Optional) As a user, I want to have a clock timer running while doing the workout and I want to be able to reset the timer manually.

<br>
<br>

*→ Implementation and overall scope/expectations not fully clear yet, hence a few user stories listed as optional and final implementation will vary from above mentioned scope.*
