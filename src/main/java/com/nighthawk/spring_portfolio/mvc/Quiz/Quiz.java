package com.nighthawk.spring_portfolio.mvc.Quiz;

import java.sql.*;

public class Quiz {

    public void insert() {
        Connection c = null;
        Statement stmt = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:volumes/quiz.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");
  
           stmt = c.createStatement();
           String sql = "INSERT INTO QUIZ (USER,ATTEMPTS,PLAYER) " +
                          "VALUES ('him', 4, 'messi' );"; 
           stmt.executeUpdate(sql);
  
        //    sql = "INSERT INTO QUIZ (ID,NAME,AGE,ADDRESS,SALARY) " +
        //             "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
        //    stmt.executeUpdate(sql);
  
        //    sql = "INSERT INTO QUIZ (ID,NAME,AGE,ADDRESS,SALARY) " +
        //             "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
        //    stmt.executeUpdate(sql);
  
        //    sql = "INSERT INTO QUIZ (ID,NAME,AGE,ADDRESS,SALARY) " +
        //             "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
        //    stmt.executeUpdate(sql);
  
           stmt.close();
           c.commit();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Records created successfully");
  
    }
   public static void main( String args[] ) {
        Quiz quiz = new Quiz();
        quiz.insert();
   }
}

// String sql = "CREATE TABLE QUIZ " +
// "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
// " USER           TEXT    NOT NULL, " + 
// " ATTEMPTS            INT     NOT NULL, " + 
// " PLAYER         TEXT NOT NULL)"; 
