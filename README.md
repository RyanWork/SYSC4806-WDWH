# SYSC4806-WDWH
We Do Work Here ( ͡° ͜ʖ ͡°)

**Learning Outcomes management system:**  
The goal is to track, edit and update the Learning Outcomes (i.e., the skills a student should acquire after completing a given course. For example, for this course: "is able to deploy a web app to the cloud", "knows enterprise design patterns", "is able to understand SaaS literature") for all the programs in our department. Each course has a set of learning objectives in a given year (these may be changed on a year-by-year basis).
Each learning objective belongs to a Category. There's a limited list of Categories which can be edited by the Administrator (such as "Design", "Communication Skills").
A course belongs to one or many programs, and a program has many courses (in a given year). A course is taught in a given "year" (1st, 2nd etc., not school year)

A user should be able to list, for a given year and for a given program: all the learning outcomes, all the categories, all the learning outcomes within a given category, all the courses covering a given category, all the learning outcomes of a given course. The user should also be able to export any of those results as a CSV (comma-separated) file. An administrator should be able to edit everything.  

**Travis CI** https://travis-ci.org/sysc4806/SYSC4806-WDWH

**_Current State of the Project_** <br>
The features that have been implemented are: <br>
  * filter by program, year, course, categories
  * sort & search through data
  * export to CSV
  * ADMIN FEATURES: add, edit, delete program, course, category, LO

<br>**_DB Schema_**<br>
![image](https://user-images.githubusercontent.com/15951317/53824917-0faf0d80-3f43-11e9-8e2b-ee262940208a.png)

<br>**_UML Class Diagram_**<br>
![umlClassDiagram](https://raw.githubusercontent.com/sysc4806/SYSC4806-WDWH/master/Images/UMLClassDiagram.png)
