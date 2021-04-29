NumberSectorProject 

Author: Giuseppe Fregapane Pires - giuseppefpires@gmail.com

### Description
This is an NumberSector application where the user can see the matching date(s) between a candidate and one or more interviewers. 

### Building and Running
In order to build and run the solution you will need JDK 8+ and Maven installed. Then, follow the steps: 
 - Clone the repository
 - Through a terminal go to the main directory of the source and run the commands:
 - `mvn clean package `
 - `java -jar target/NumberSectorProject-0.0.1-SNAPSHOT.jar`
 - In the root  of the sistem i put the file interviewCalendarAPI.postman_collection.json that can be import into postman with some examples of requests.Feel free to use if you want.

### Design Decisions
- For this Rest Application, i used spring boot.
- I use the H2 as a database to simplify the persistence layer.
- No autentication and authorization was implemented.
