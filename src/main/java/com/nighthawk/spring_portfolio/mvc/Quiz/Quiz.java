package com.nighthawk.spring_portfolio.mvc.Quiz;

import java.sql.*;

public class Quiz {

    public void insert(String user, int attempts, String player) {
        Connection c = null;
        Statement stmt = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:volumes/quiz.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");
  
           stmt = c.createStatement();
           String sql = String.format("INSERT INTO QUIZ (USER,ATTEMPTS,PLAYER) " +
                          "VALUES ('%s', %d, '%s' );",user,attempts,player); 
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
    public void get_data() {
        Connection c = null;
        Statement stmt = null;
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:volumes/quiz.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");
     
           stmt = c.createStatement();
           ResultSet rs = stmt.executeQuery( "SELECT * FROM QUIZ;" );
           
           while ( rs.next() ) {
              int id = rs.getInt("id");
              String  name = rs.getString("user");
              int age  = rs.getInt("attempts");
              String  address = rs.getString("player");
              
              System.out.println( "ID = " + id );
              System.out.println( "NAME = " + name );
              System.out.println( "ADDRESS = " + address );
              System.out.println();
           }
           rs.close();
           stmt.close();
           c.close();
        } catch ( Exception e ) {
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
           System.exit(0);
        }
        System.out.println("Operation done successfully");
     
    }
   public static void main( String args[] ) {
        Quiz quiz = new Quiz();
        quiz.get_data();
   }
}

// String sql = "CREATE TABLE QUIZ " +
// "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
// " USER           TEXT    NOT NULL, " + 
// " ATTEMPTS            INT     NOT NULL, " + 
// " PLAYER         TEXT NOT NULL)"; 
