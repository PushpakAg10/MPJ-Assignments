# Employee Registration System
### JSP + Servlet + JDBC | MIT-WPU MPJ Assignment 5

---

## Tech Stack
| Layer       | Technology                              |
|-------------|----------------------------------------|
| Frontend    | JSP 2.2, JSTL 1.2, HTML5, CSS3        |
| Backend     | Java Servlet 2.5 (JDK 1.8+)           |
| Database    | MySQL 8.x, mysql-connector-java-8.0.13 |
| Server      | Apache Tomcat 8.5                      |
| Build Tool  | Maven 3.x                              |

## Features
- Register new employees (full form with validation)
- View list of all employees
- View detailed employee profile
- Edit/update employee records
- Delete employees
- Flash messages for all operations
- Responsive UI

---

## Prerequisites
- JDK 1.8 or later
- Maven 3.x (`mvn -v` to check)
- MySQL Server running on localhost:3306
- Apache Tomcat 8.5 (only needed for WAR deploy option)

---

## Quick Start (3 Steps)

### Step 1 — Set up the Database
Open MySQL CLI or MySQL Workbench and run:
```sql
source /path/to/EmployeeRegistration/database.sql
```
Or paste the contents of `database.sql` directly.

**Default DB credentials used in the app:**
- Host: `localhost:3306`
- Database: `employee_db`
- Username: `root`
- Password: `root`

> If your MySQL password is different, edit line 8 in:
> `src/main/java/com/employee/dao/DBConnection.java`

---

### Step 2 — Build the project
```bash
cd EmployeeRegistration
mvn clean package
```
This creates `target/EmployeeRegistration.war`

---

### Step 3 — Run Options

#### Option A: Maven Tomcat Plugin (Easiest — no Tomcat install needed)
```bash
mvn tomcat7:run
```
Open browser: **http://localhost:8080/EmployeeRegistration**

#### Option B: Deploy to Tomcat manually
1. Copy `target/EmployeeRegistration.war` to `TOMCAT_HOME/webapps/`
2. Start Tomcat: `TOMCAT_HOME/bin/startup.bat` (Windows) or `startup.sh` (Linux/Mac)
3. Open: **http://localhost:8080/EmployeeRegistration**

#### Option C: Import into Eclipse/STS
1. File → Import → Existing Maven Projects
2. Browse to `EmployeeRegistration` folder → Finish
3. Right-click project → Run As → Run on Server (select Tomcat 8.5)

---

## Project Structure
```
EmployeeRegistration/
├── pom.xml                          ← Maven config (all dependencies)
├── database.sql                     ← Run this first!
└── src/main/
    ├── java/com/employee/
    │   ├── model/Employee.java      ← Bean / POJO
    │   ├── dao/DBConnection.java    ← JDBC connection utility
    │   ├── dao/EmployeeDAO.java     ← All CRUD SQL operations
    │   └── servlet/EmployeeServlet.java  ← Front Controller
    └── webapp/
        ├── index.jsp                ← Redirects to list
        ├── css/style.css            ← Stylesheet
        └── WEB-INF/
            ├── web.xml              ← Servlet config
            └── views/
                ├── employee-list.jsp   ← Table of all employees
                ├── employee-form.jsp   ← Add / Edit form
                └── employee-view.jsp   ← Detail view
```

---

## URL Reference
| URL                                         | Action            |
|---------------------------------------------|-------------------|
| `/employee?action=list`                     | List all employees|
| `/employee?action=new`                      | Registration form |
| `/employee?action=view&id=1`               | View details      |
| `/employee?action=edit&id=1`               | Edit form         |
| `/employee?action=delete&id=1`             | Delete employee   |
| POST `/employee` (action=add)              | Save new employee |
| POST `/employee` (action=update)           | Update employee   |
