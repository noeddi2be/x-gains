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

Every project member contributed significantly in every phase of the project, ensuring a collaborative outcome.
Throughout the project, Manuel led the back-end development and Boris coordinated the front-end efforts. 
Silvana was also significantly involved in the front end and was responsible for the wireframe while Dominik was taking care of the documentation stuff and the finish of the project but also supported the frontend development.

### 🔗 Links
The following services are live at the moment of project delivery and will be
turned off later on. The free version of Render is used, which requires some
loading time (~3 min) for the initial request to startup the system.

#### ✳️ API Documentation & Testing
[Swagger-UI](https://xgains-render.onrender.com/swagger-ui/index.html) <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/swagger.png?raw=true" width="500" height="380">

#### 💿 H2 Database (Accessible on request)
[H2 Console](https://xgains-render.onrender.com/h2-console) <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/h2.png?raw=true" width="500" height="300">

#### 🎨 Budibase
[Budibase Frontend](https://inttech.budibase.app/app/brugg2_xgains#/home) <br>

<img src="https://github.com/noeddi2be/x-gains/blob/main/www/X-Gains_Logo.png?raw=true" width="500" height="500">

Unfortunately, we faced huge problems implementing the frontend with Budibase. Especially difficult were the functionality to login
with basic authentication as well as using dynamic variables to store data received by API responses.

For the scope of this project we wanted to use basic authentication with credentials stored in the database.
We were not able to dynamically assign backend user roles to Budibase roles for accessing different screens. The API 
endpoints have been secured in the backend, but we were not able to store a cookie in Budibase, we were also not able to 
consistently store the base64 representation of username and password for further API calls, and even after calculating
the base64 value in the backend, the assignment to a dynamic variable was inconsistent and not working correctly for us.
To ensure proper functioning, we would have needed to implement a JWT in the backend and use a bearer token in the frontend.

We also faced further issues with dynamic variables. After receiving a response from the backend, we have not been able to
consistently store information in a variable to use it for following http requests. For example:
The database schema requires us to provide a FK workout ID for storing exercises. After creating a workout, we need to
be able to store the workout ID in the frontend to add exercises to that workout. When saving the workout ID in a dynamic variable,
the variable sometimes either automatically triggered a new API call, or would not update consistently. Retriggering requests
is documented in the official docs, but the documentation was not helpful in resolving our issues. We needed to implement
another API endpoint to receive the workout ID by workout name, as well as change our database schema and
placeholder data to make the workout name unique. Even after that, we still had problems working with Budibase tables, forms and
variables.

Mundane tasks like working with variables or updating table data is complicated and not very intuitive.
There is no obvious possibility to check what is stored in a variable without developer tools in the browser. 
Debugging is difficult and lacks transparency to a big degree. More than once we were confronted with random bugs where dynamic variables don't appear and 
therefore cannot be used. The overall functionality seemed buggy and the workflow logic was not clear and intuitive. 
Furthermore, we struggled to find online resources to guide us through our problems in an effective and efficient manner.

It would have been better to implement the frontend ourselves with JS, HTML and CSS, but this has not been possible 
due to time constraints after investing a lot of time in our Budibase project. 
We are therefore just providing a basic functionality of the frontend, not using the whole capability 
of the backend. We would not use Budibase in such a project again and the experience was not very pleasant.

---

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

*→ For this project the user type "Admin" was not implemented, therefore the attributes "last-login" and "user_status" was not covered.* <br>
*→ The ER-Model is a simple visualization of this project scope and could be extended for future projects.*

## 🔧 Wireframe

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

*→ Implementation of User Role only in this project. Optional User Stories were not implemented, could be part of a future project or enhancement of the current one.*
