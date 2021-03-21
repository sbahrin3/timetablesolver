# Automatic Timetable Solver

### Intro ###

This is a Java Project for the School Timetable Generation solution.  The solving of scheduling activities method that being used here is Constraint Programming or CP for short, and the CP library is <a href="https://github.com/chocoteam/choco-solver">Choco-Solver</a>.


This repository is an Eclipse Dynamic Web Module, hence to clone this you need to do it within Eclipse.


## Embedded Database - H2 ##

This project comes with an embedded H2 database, so you are not required to set up any database server to run it.

The setting for H2 is located in the <b>dbpersistence.properties</b> file:

```
title.default=Timetable
driver.default=org.h2.Driver
dialect.default=org.hibernate.dialect.H2Dialect
user.default=sa
password.default=
url.default=jdbc:h2:file:~/h2db/timetabledb
```

## MySQL Database ##

However, if you want to use MySQL database instead (which is not needed!), follow this instructions:

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


### Timetable Constraints Solver ###

As mentioned above, the CP solver for this timetable scheduling is using an open-source Choco-Solver library.

For this simple demo project, I have only create a solver for teacher's constraint, i.e. a teacher must not exits in the same time slot.

There are many others constraints variables that need to be considered next.  This is an on going project, therefore, I will enhance and add more constraints to this.

The Java class that deals with scheduling solver is <b>TimetableSolver.java</b>.  You can find this Java program in the <b>src</b> folder within in the <b>my.timetable.module</b> package.

by
Shamsul Bahrin
