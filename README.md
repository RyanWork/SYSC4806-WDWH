# SYSC4806-WDWH
We Do Work Here ( ͡° ͜ʖ ͡°)

**Learning Outcomes management system:**  
The goal is to track, edit and update the Learning Outcomes (i.e., the skills a student should acquire after completing a given course. For example, for this course: "is able to deploy a web app to the cloud", "knows enterprise design patterns", "is able to understand SaaS literature") for all the programs in our department. Each course has a set of learning objectives in a given year (these may be changed on a year-by-year basis).
Each learning objective belongs to a Category. There's a limited list of Categories which can be edited by the Administrator (such as "Design", "Communication Skills").
A course belongs to one or many programs, and a program has many courses (in a given year). A course is taught in a given "year" (1st, 2nd etc., not school year)

A user should be able to list, for a given year and for a given program: all the learning outcomes, all the categories, all the learning outcomes within a given category, all the courses covering a given category, all the learning outcomes of a given course. The user should also be able to export any of those results as a CSV (comma-separated) file. An administrator should be able to edit everything.  

**Travis CI** https://travis-ci.org/sysc4806/SYSC4806-WDWH

**_Current State of the Project_**

|      Current State of the Project (Done)                                         | Plan for Next Sprint              |
|:--------------------------------------------------------------------------------:|----------------------------------:|
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/1">#1 Implement CI</a> | improve test cases              |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/2">#2 Deploy on Heroku</a> | discuss persisiting data with PostgreSQL or using data.sql |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/4">#4 Create Kanban</a> | complete diagrams |               
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/5">#5 Build DB Schema</a> | reload view upon form submit |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/6">#6 Create Feature Branches</a> | organize tables in view |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/7">#7 Scrum, Feb 25</a> | <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/19">#19 Filtering</a> |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/11">#11 Features/UI Breakdown</a>| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/20">#20 Sorting</a> |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/12">#12 Travis Build failing</a> | <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/21">#21 Admin Rights</a>|
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/15">#15 Implement DB Schema</a> | <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/16">#16 Export to CSV</a> |
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/17">#17 Create Skeleton Models</a> |
|<a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/18">#18 Create Model DB Connections</a>|
|<a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/22">#22 Populate DB with Data</a>|
| <a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/24">#24 Create Main Feature View</a> |
|<a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/26">#26 Scrum March 1</a>|
|<a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/28">#28 Test Cases for Model DB Connections</a>|
|<a href="https://github.com/sysc4806/SYSC4806-WDWH/issues/28">#33 Connect DB to View</a>|

<br>**_Current DB Schema_**<br>
![image](https://user-images.githubusercontent.com/15951317/53824917-0faf0d80-3f43-11e9-8e2b-ee262940208a.png)

<br>**_UML Class Diagram_**<br>
![umlClassDiagram](https://raw.githubusercontent.com/sysc4806/SYSC4806-WDWH/master/Images/UMLClassDiagram.png)