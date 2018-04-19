package am.lavshuka.lad.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by @Author David Karchikyan on 4/19/2018.
 */
public class DBconn {

    private static DBconn instance;
    private Connection conn;

    private DBconn() throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("db");
        String host = rb.getString("host");
        String port = rb.getString("port");
        String db = rb.getString("db");
        String user = rb.getString("user");
        String password = rb.getString("password");
        String jdbcUrlMessageFormat = MessageFormat.format(
                "jdbc:mysql://{0}:{1}/{2}",
                host, port, db);
        conn = DriverManager.getConnection(jdbcUrlMessageFormat, user, password);
    }

    public static DBconn getInstance() {
        if (instance == null)
            try {
                instance = new DBconn();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return instance;
    }

    public Connection connection() {
        return conn;
    }

}
