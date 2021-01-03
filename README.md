# Automatic Timetable Solver

### Intro ###

This is a Java Project for the School Timetable Generation solution.  The solving of scheduling activities method that being used here is Constraint Programming or CP for short, and the CP library is <a href="https://github.com/chocoteam/choco-solver">Choco-Solver</a>.


This repository is an Eclipse Dynamic Web Module, hence to clone this you need to do it within Eclipse.

The mysqldump file is located in the <b>\dbdump</b> folder.  Use this to create your database with some data in it.

Edit the database connection settings in the <b>dbpersistence.properties</b> file.

```
title.default=TIMETABLE
driver.default=com.mysql.jdbc.Driver
user.default=root
password.default=mypassword
url.default=jdbc:mysql://localhost:3306/timetable
```

Make sure your Eclipse has the Apache Tomcat Server integration done.

You can run this project within your Eclipse environment by selecting the <b>Run As -> Run on Server</b> on this project.  


by
Shamsul Bahrin
