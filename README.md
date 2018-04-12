# Jsp_Servlets_Project

This project utilizes a MVC framework



Web Browser <=>  View (JSP pages) <=> Controller (Servlets) <=> (Helper)Database

The browser requests information from the servlets which in turn fetches the data from the database and sends it to the JSP page which creates the HTML page on the fly.


-	The JSP page consists of the add-student-form.jsp and the list-students.jsp in the web-inf directory
-	The Controller section of mvc is the studentcontroller.java class in the src file directory is the servlet and handles the underlying business logic
-	The helper class is the studentDButil.java class in the src director and makes connection the database
-	-The doget method in the controller class creates the link between the controller and the jsp page







