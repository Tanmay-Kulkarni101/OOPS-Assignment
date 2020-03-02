# Question Bank Database
It is a stand alone Java Application that interacts with a sqlite database to create a question bank. It makes use of swing to render the GUI.

# Features 

  - Has separate admin and user controls so that users can be separatately managed by the admin.
  - Can insert new question into the database in three formats:
  	- Fill in the blanks.
  	- Multiple Choice Quesitons
  	- True or False Questions
  - Delete questions or modify questions based on the the checkboxes rendered on screen.
 
## Program Flow
The order of invokation is as follows:
  1. The Driver Class
  2. The Security Class which verifies the username and the password
  3. The AdminControls Class which helps the adminstrator of the system perform the necessary functions.
      - Add new users 
      - Delete existing users
  4. For an ordinary user, the Main Screen Class loads, which displays all the options available:
      - Insert new question
      - Delete existing question
      - Modify existing question
      - Export questions to File

# Dependency
- Java
- SqlLite
