package com.nighthawk.spring_portfolio.mvc.cart;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Cart {
   
   public void insert(int item, String user, int quantity,int uid) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = String.format("INSERT INTO CART (ITEM,USER,QUANTITY,UID) " +
         "VALUES (%d, '%s', %d,%d );",item,user,quantity,uid); 
         stmt.executeUpdate(sql);


         stmt.close();
         c.commit();
         c.close();
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      System.out.println("Records created successfully");

   }

   public JSONArray get_data() {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
   
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( "SELECT * FROM CART;" );

         ResultSetMetaData md = rs.getMetaData();
         int numCols = md.getColumnCount();
         List<String> colNames = IntStream.range(0, numCols) //stack overflow
         .mapToObj(i -> {
             try {
                 return md.getColumnName(i + 1);
             } catch (SQLException e) {
                 e.printStackTrace();
                 return "?";
             }
         })
         .collect(Collectors.toList());
 
         JSONArray result = new JSONArray(); //stack overflow
         while (rs.next()) {
             JSONObject row = new JSONObject();
             colNames.forEach(cn -> {
                 try {
                     row.put(cn, rs.getObject(cn));
                 } catch (Exception e) {
                     ((Throwable) e).printStackTrace();
                 }
             });
             result.add(row);
             // return result;
         }
 
            rs.close();
            stmt.close();
            c.close();
         return result;
 
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return null;

   }

   public JSONArray get_data(int uid) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
   
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( String.format("SELECT * FROM CART WHERE UID=%d;",uid) );

         ResultSetMetaData md = rs.getMetaData();
         int numCols = md.getColumnCount();
         List<String> colNames = IntStream.range(0, numCols) //stack overflow
         .mapToObj(i -> {
             try {
                 return md.getColumnName(i + 1);
             } catch (SQLException e) {
                 e.printStackTrace();
                 return "?";
             }
         })
         .collect(Collectors.toList());
 
         JSONArray result = new JSONArray(); //stack overflow
         while (rs.next()) {
             JSONObject row = new JSONObject();
             colNames.forEach(cn -> {
                 try {
                     row.put(cn, rs.getObject(cn));
                 } catch (Exception e) {
                     ((Throwable) e).printStackTrace();
                 }
             });
             result.add(row);
             // return result;
         }
 
            rs.close();
            stmt.close();
            c.close();
         return result;
 
      } catch ( Exception e ) {
         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
         System.exit(0);
      }
      return null;

   }

   public void deleteItem(int id) {
    Connection c = null;
    Statement stmt = null;
    
    try {
       Class.forName("org.sqlite.JDBC");
       c = DriverManager.getConnection("jdbc:sqlite:products.db");
       c.setAutoCommit(false);
       System.out.println("Opened database successfully");

       stmt = c.createStatement();
       String sql = String.format("DELETE from CART where ID=%d;",id);
       stmt.executeUpdate(sql);
       
       stmt.close();
       c.commit();
       c.close();
    } catch ( Exception e ) {
       System.err.println( e.getClass().getName() + ": " + e.getMessage() );
       System.exit(0);
    }
    System.out.println("Records created successfully");
    }

    public void updateData(int id, int quantity) {
        Connection c = null;
        Statement stmt = null;
        
        try {
           Class.forName("org.sqlite.JDBC");
           c = DriverManager.getConnection("jdbc:sqlite:products.db");
           c.setAutoCommit(false);
           System.out.println("Opened database successfully");
  
           stmt = c.createStatement();
           String sql =String.format("UPDATE PRODUCT set QUANTITY = %d where ID=%d;",quantity,id);
           stmt.executeUpdate(sql);
  
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
      // Cart cart = new Cart();
      Connection c = null;
      Statement stmt = null;

      // System.out.println(cart.get_data());
    }
 }
//  "CREATE TABLE CART " +
//  "(ID INTEGER PRIMARY KEY  AUTOINCREMENT   NOT NULL," +
//  " ITEM           INT    NOT NULL, " + 
//  " USER            TEXT     NOT NULL, " + 
//  " QUANTITY        INT NOT NULL" + 
//  ")"; 