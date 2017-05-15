package org.camid.cep.sqlite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.camid.cep.configure.EPLUnitConfig;
import org.camid.cep.configure.EPLinformation;

public class DatabaseManager {

	private static Log log = LogFactory.getLog(DatabaseManager.class);
	private Connection c;
	private Statement stmt;

	public static void setUpDatabase() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:epl.db");
			System.out.println("Opened database successfully");
			log.info("Connected database");
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS EPL " + "(NAME 	NVARCHAR(80)    NOT NULL, "
					+ " STATEMENT      TEXT     NOT NULL, " + " LISTENER        NVARCHAR(80) NOT NULL)";

			log.info("Create table name is EPL");
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public static void getEPL() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:epl.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			log.info("Connected database");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM EPL;");
			List<EPLinformation> EplConfig = new ArrayList<EPLinformation>();
			while (rs.next()) {
				String name = rs.getString("name");
				String statement = rs.getString("statement");
				String listener = rs.getString("listener");
				System.out.println("NAME = " + name);
				System.out.println("statement = " + statement);
				System.out.println("listener = " + listener);
				System.out.println();
				EPLinformation epl = new EPLinformation(name, statement, listener);
				EplConfig.add(epl);
			}
			EPLUnitConfig.EPLinformation = EplConfig;

			log.info("Get EPL from database:"+EplConfig.toString());
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public static void insertEPL(String name, String expression, String listener) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:epl.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			log.info("Connected database");
			stmt = c.createStatement();
			String sql = "INSERT INTO EPL (NAME,STATEMENT,LISTENER) " + "VALUES ('" + name + "','" + expression + "', '"
					+ listener + "' );";
			
			
			stmt.executeUpdate(sql);

			log.info("Insert EPL into databse: "+sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void deleteEPL(String name) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:epl.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			log.info("Connected database");
			stmt = c.createStatement();
			String sql = "DELETE from EPL where name= '" + name + "';";
			stmt.executeUpdate(sql);

			log.info("Delect the EPL from database:"+ sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

}