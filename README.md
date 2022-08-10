# Annah's CPSC 210 Project

## Project Introduction

*What will the application do?*

The current plan for this project is to create a task manager that can also double as a calendar schedule. A user will be 
able to enter in different classes and other commitments like jobs or volunteering. Within each of these commitments a user
will be able to enter in individual tasks or deliverables and the due dates attached to them. Users then can view the tasks 
in an organized list under individualized commitment tabs, all together in one list, or in calendar form with the attached 
dates that were entered. 
 
*Who will use it?*

This application is directed at students and people in task-based careers, although it can also be used in general for daily life. 

*Why is this project of interest to you?*

As an engineering student, I have to deal with lots of busy days, and I need to be able to manage my time well and keep 
track of all my assignment due dates and exam dates. I have tried a variety of scheduling software programs and calendar 
apps; however, none of them have the exact specifications I am looking for. Thus, I concluded that I should build my own 
because I would understand my needs the best.

## User Stories

* As a user, I want to be able to add different commitments such as classes, jobs, or volunteering to my agenda.
* As a user, I want to be able to add different tasks and an attached date to each of my added commitments.
* As a user, I want to be able to view a list of my tasks either organized within classes or altogether.
* As a user, I want to be able to separate my commitments into three categories, classes, jobs, and volunteering.
* As a user, I want to be able to close the task viewer and have my entered commitments and tasks saved without being prompted to save them.
* As a user, I want to be able to reopen the application and see the previously entered commitments and tasks.

## Instructions for Grader

1. You can generate the first required event by clicking on the add commitment button or add task button, if commitments are already added
2. You can generate the second required event by clicking the show commitment button.
3. You can locate my visual component by clicking the save file button.
4. You can save the state of my application by clicking the save file button.
5. You can reload the state of my application by clicking the load file button.

## Phase 4: Task 2

An example of the events that are printed can be seen below:

added a task named Phase 3 Project to CPSC 210 \
added commitment named CPSC 210\
added commitment named MATH 100 TA\
added a task named Paper 1 to ENGL 100\
added commitment named ENGL 100\
loaded information from saved file\
displayed commitment list for Annah's Task Viewer\
added commitment named CPSC 210\
added commitment named MATH 100 TA\
added commitment named ENGL 100\
saved information to file\

## Phase 4: Task 3

*If you had more time to work on the project, is there any refactoring you would do to improve your design? If so, describe the changes you would make.*

I could potentially create enums for commitment category and task type instead of making them open to any string. Additionally,
I could've created an overarching abstract class or interface that both commitment and task could extend/implement, since they
have somewhat similar fields. 

In terms of in the UI class, I put a fair amount of functionality within the GUI code, so I could refactor and add more methods
into the commitment and task classes which would allow me to choose which commitment to add or show tasks to, rather than putting
all that code in an abstract task button class. In addition, there are some errors that I choose to fix in console or GUI class
due to the time constraint, that should have been implemented as new methods or improved in existing methods in the model package.
