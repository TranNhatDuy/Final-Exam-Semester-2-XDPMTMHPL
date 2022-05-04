/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;
import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author Win 10
 */
public class ConnectionUtils {
    public Connection getMyConnection() throws SQLException,
		ClassNotFoundException{
		return MySQLConnecUtils.getMYSQLConnection();
	}
}
