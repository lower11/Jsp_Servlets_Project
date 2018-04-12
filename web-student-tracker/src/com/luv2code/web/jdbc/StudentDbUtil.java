package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;


public class StudentDbUtil{
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
		//get a connection 
		myConn = dataSource.getConnection();
		
		//create a sql statememt
		String sql = "select * from student order by last_name";
		myStmt = myConn.createStatement();
		
		//execute query
		myRs = myStmt.executeQuery(sql);
		
		//process result set
		while(myRs.next()) {
			
			//retrieve data from result set row
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			
			//create new student object
			Student tempStudent = new Student(id, firstName, lastName, email);
			
			//add it to the list of students
			students.add(tempStudent);
			
			
			
		}
		
			return students;
			
		}
		
		finally {
			//close jdbc objects
			close(myConn, myStmt, myRs);
		}
		
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		
		try {
			
			if(myRs != null) {
				myRs.close();
				
			}
			
			if(myStmt != null) {
				myStmt.close();
				
			}
			
			if(myConn!=null) {
				
				myConn.close();//puts it back in the connection pool for reuse
			}
			
			
		}
		
		catch(Exception exc) {
			exc.printStackTrace();
			
			
		}
		
	}

	public void addStudent(Student theStudent) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
		//get the db connection
			myConn = dataSource.getConnection();
			
		//create sql statement for insert
			String sql = "insert into student" 
					+ "(first_name, last_name, email)"
					+ "values (?,?,?)";
			
			myStmt = myConn.prepareStatement(sql);
		
		//set the parameter values for the students
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
		
		//execute the sql insert
			myStmt.execute();
			
		} finally {
		
		//clean up jdbc object
			close(myConn, myStmt, null);
		}
		
	}

	public Student getStudent(String theStudentId) throws Exception{
		Student theStudent = null;
		
		Connection myConn  = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);
			
			// get connection to database
			
			myConn = dataSource.getConnection();
			
			
			// create sql to get selected student
			String sql = "select * form student where id=?";
			
			//create prepared statement 
			
			myStmt = myConn.prepareStatement(sql);
			
			//set the parameters
			myStmt.setInt(1, studentId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			
			//retrieve data from result set row
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("flast_name");
				String email = myRs.getString("email");
				
				//use the studentId during construct
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("could not find student id:" + studentId);
			}
			
			return theStudent;
		} finally {
			//clean up jdbc object
			
			close(myConn, myStmt, myRs);
			
		}
		
		
		
	}
	
	
}