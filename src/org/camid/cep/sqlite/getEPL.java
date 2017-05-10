package org.camid.cep.sqlite;

import java.sql.*;

public class getEPL
{
  public static void main( String args[] )
  {
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:epl.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM EPL;" );
      while ( rs.next() ) {
         String  name = rs.getString("name");
         String  statement = rs.getString("statement");
         String  listener = rs.getString("listener");
         System.out.println( "NAME = " + name );
         System.out.println( "statement = " + statement );
         System.out.println( "listener = " + listener );
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
}