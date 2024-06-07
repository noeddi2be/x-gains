<img src="https://github.com/noeddi2be/x-gains/blob/main/www/spring-x-gains.png?raw=true" width="550" height="200">


# [👉🏼 Project Management File](https://github.com/noeddi2be/x-gains/blob/main/PROJECT-MANAGEMENT.md)

# ☕️ Use Case: X-Gains

X-Gains is a fitness tracker where users can create an account,
log in, add & modify workouts and track their activities and exercises.
The data is persisted in an H2 in-memory database and deployed on Render
in a Docker container.

---

The application was created using an MVC design pattern and is divided into different layers: 
- Presentation Layer: Frontend (Built with Budibase), Controller Classes
- Application Layer: Service Classes
- Domain Layer: Configuration Classes, Model Entities
- Persistence Layer: Repository Classes
- Database Layer: SQL Statements, H2 Database

Maven is used for dependency management as well as for project building,
ensuring a consistent build process for deployment on Render.
The project implements Spring Boot, which simplifies the initial Spring setup, 
leveraging the capabilities of the Spring Framework.

The API is configured using RestController annotations and
documented with Swagger-UI.

Several unit tests are included to ensure proper functioning of the
application. Controller classes are tested with Postman and
can also be tried out on Swagger-UI.

Security is handled using the *spring-boot-starter-security* dependency.
At the moment, basic authentication is used to access the API endpoints.
Credentials are validated using the user entity in the H2 database.
Passwords are stored hashed and are encrypted using
a password encoder from org.springframework.security.crypto.

---

### 👩‍💻🧑‍💻 Project Contributors
- Silvana Rey
- Dominik Schneider
- Boris Vittek
- Manuel Notter

### 🔗 Links
The following services are live at the moment of project delivery and will be
turned off later on. The free version of Render is used, which requires some
loading time (~3 min) for the initial request to startup the system.

#### ✳️ API Documentation & Testing
https://xgains-render.onrender.com/swagger-ui/index.html <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/swagger.png?raw=true" width="500" height="500">

#### 💿 H2 Database (Accessible ATM)
https://xgains-render.onrender.com/h2-console <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/h2.png?raw=true" width="500" height="300">

#### 🎨 Budibase
http://example.com <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/thumbnail.jpg?raw=true" width="500" height="500">

## 📱 App Content
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

## 🧩 Scenario and User Stories

X-Gains is a basic fitness tracking app designed to assist our users in achieving their health <br>
and fitness goals. Whether you are a beginner starting your fitness journey or a more advanced athlete <br>
looking to optimize your workouts, X-Gains provides a user-friendly platform packed with robust features <br>
to support you every step of the way. X-Gains is more than just a fitness tracker; it is a tool designed <br>
to empower users to take control of their health and fitness, stay motivated, and achieve their desired <br>
results. X-Gains is your ultimate companion on the path to a healthier, stronger you.

## 🔧 ER-Model

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/er-model.png?raw=true" width="840" height="450"> <br>

## 🔧 Wireframe User

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/wireframe.png?raw=true" width="900" height="550"> <br>

### 🧩 User Stories

**🟠 Admin Role**
* As an admin, I want to have a Web app so that I can use it on different mobile devices and on desktop computers.
* As an admin, I want to be able to log in securely to the X-Gains app to access administrative features.
* As an admin, I want to see a consistent visual appearance so that I can navigate easily, and it looks consistent.
* (Optional) As an admin, I want to have a dashboard displaying key metrics such as total users and activity summaries.
* As an admin, I want to be able to add, edit, or remove different types of exercises to the app's database.
* (Optional) As an admin, I want to be able to manage user accounts, including creating new accounts, resetting passwords, and deactivating accounts if necessary.

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
