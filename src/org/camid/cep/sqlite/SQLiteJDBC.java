package org.camid.cep.sqlite;

import java.sql.*;

public class SQLiteJDBC
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
//    createtable();
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:epl.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql2 = "DELETE from EPL where name= 'sensor1c';";
      stmt.executeUpdate(sql2);
//      String epl="insert into Humidity select * from sensor(dataType=''Humidity'')";
//      String sql = "INSERT INTO EPL (NAME,STATEMENT,LISTENER) " +
//                   "VALUES ('Paul','insert into Humidity select * from sensor(dataType=''Humidity'')', 'NUll' );"; 
//      stmt.executeUpdate(sql);

//      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
//      stmt.executeUpdate(sql);
//
//      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//            "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );"; 
//      stmt.executeUpdate(sql);
//
//      sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
//            "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );"; 
//      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
    System.out.println("Records created successfully");
  }
  
  public static void createtable(){
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:epl.db");
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      String sql = "CREATE TABLE IF NOT EXISTS EPL " +
	                   "(NAME 	NVARCHAR(80)    NOT NULL, " + 
	                   " STATEMENT      TEXT     NOT NULL, " + 
	                   " LISTENER        NVARCHAR(80) NOT NULL)"; 
	      System.out.println(sql);
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Table created successfully");
	  }
  
  
}