
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class MySQLConnect {

    String user = "root";

    String password = "";
    String url = "jdbc:mysql://localhost:3306/trungtamtienganh?useUnicode=yes&characterEncoding=UTF-8";
    public static Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public MySQLConnect() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            } catch (SQLException e) {

                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    void MySQLDisconnect() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public Connection getConnect() {
        return this.conn;
    }

}
