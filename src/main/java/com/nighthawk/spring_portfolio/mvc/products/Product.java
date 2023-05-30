package com.nighthawk.spring_portfolio.mvc.products;

import java.sql.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Product {
   public void insert(String name, int price, String description,int inStock) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = String.format("INSERT INTO PRODUCT (NAME,PRICE,DESCRIPTION,INSTOCK) " +
         "VALUES ('%s', %d, '%s',%d );",name,price,description,inStock); 
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
         ResultSet rs = stmt.executeQuery( "SELECT * FROM PRODUCT;" );

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

   public JSONArray get_data(int id) {
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
   
         stmt = c.createStatement();
         ResultSet rs = stmt.executeQuery( String.format("SELECT * FROM PRODUCT WHERE ID=%d;",id) );

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





   
   public void updateData(int id, String name, int price, String description, int inStock) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql =String.format("UPDATE PRODUCT set NAME = '%s' where ID=%d;",name,id);
         stmt.executeUpdate(sql);

         sql =String.format("UPDATE PRODUCT set PRICE = %d where ID=%d;",price,id);
         stmt.executeUpdate(sql);
         sql =String.format("UPDATE PRODUCT set DESCRIPTION = '%s' where ID=%d;",description,id);
         stmt.executeUpdate(sql);
         sql =String.format("UPDATE PRODUCT set INSTOCK = %d where ID=%d;",inStock,id);
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

   public void deleteItem(int id) {
      Connection c = null;
      Statement stmt = null;
      
      try {
         Class.forName("org.sqlite.JDBC");
         c = DriverManager.getConnection("jdbc:sqlite:products.db");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         String sql = String.format("DELETE from PRODUCT where ID=%d;",id);
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
      Product product = new Product();
      System.out.println(product.get_data(2));
   }
}


// String sql = "CREATE TABLE PRODUCT " +
// "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
// " NAME           TEXT    NOT NULL, " + 
// " PRICE            INT     NOT NULL, " + 
// " DESCRIPTION         CHAR(200) NOT NULL),"+
//"INSTOCK INT NOT NULL";  