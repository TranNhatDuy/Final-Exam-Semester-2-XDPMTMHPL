/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Win 10
 */
public class MySQLConnecUtils {
    public static Connection getMYSQLConnection() throws SQLException,
		ClassNotFoundException {
		String hostname = "localhost";
		String dbname = "trungtamtienganh";
		String username = "root";
		String password = "";
		return getMySQLConnection(hostname, dbname, username, password);
		
	}
	public static Connection getMySQLConnection(String hostname, String dbname, String username, String password) throws SQLException,
	ClassNotFoundException {
		String connectionURL = "jdbc:mysql://" + hostname + ":3306/" + dbname;
		Connection conn = DriverManager.getConnection(connectionURL ,username, password);
		return conn;
		
	}
}
