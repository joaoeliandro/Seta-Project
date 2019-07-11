package model.modelSQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eliandro
 */
public class SQLBase {

    protected static Connection connect;

    public static Connection open() {
        try {
            connect = DriverManager.getConnection("jdbc:sqlite:mydb_ProjectSeta");
            return connect;
        } catch (SQLException ex) {
            Logger.getLogger(SQLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void close() {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
