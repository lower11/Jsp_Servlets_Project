# Jsp_Servlets_Project

This project utilizes a MVC framework.

To play this program, just 'play' the add-student-form.jsp page (assuming you have tomcat configured) and it will provoke you to fill a form, which is the project. This project allows you to add, subtract and update the student form fill information and is a simple project that exhibits my understanding of jsp and servlets webpages. 

Web Browser <=>  View (JSP pages) <=> Controller (Servlets) <=> (Helper)Database

The browser requests information from the servlets which in turn fetches the data from the database and sends it to the JSP page which creates the HTML page on the fly. T

-	The JSP page consists of the add-student-form.jsp and the list-students.jsp in the web-inf WEB_CONTENT directory
-	The Controller section of mvc is the studentcontroller.java class in the src file directory is the servlet and handles the underlying business logic
-	The helper class is the studentDButil.java class in the src director and makes connection the database
-	-The doget() method in the controller class creates the link between the controller and the jsp page







