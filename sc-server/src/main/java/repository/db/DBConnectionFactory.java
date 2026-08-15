package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import konfiguracija.Konfiguracija;

/**
 * Singleton fabrika JDBC konekcije ka MySQL bazi.
 */
public class DBConnectionFactory {
    private static DBConnectionFactory instance;
    private Connection connection;

    private DBConnectionFactory() {
        try {
            String url = Konfiguracija.getInstance().getProperty("url");
            String username = Konfiguracija.getInstance().getProperty("username");
            String password = Konfiguracija.getInstance().getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            connection = null;
            Logger.getLogger(DBConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DBConnectionFactory getInstance() {
        if (instance == null) {
            instance = new DBConnectionFactory();
        }
        return instance;
    }

    /**
     * Vraća trenutnu konekciju (može biti {@code null} ako uspostavljanje nije uspelo).
     * Za integracione testove koji preskaču rad bez baze.
     *
     * @return JDBC konekcija ili {@code null}
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Vraća aktivnu konekciju ili baca izuzetak sa jasnom porukom.
     *
     * @return otvorena JDBC konekcija
     * @throws Exception ako konekcija nije uspostavljena ili je zatvorena
     */
    public Connection requireConnection() throws Exception {
        try {
            if (connection == null || connection.isClosed()) {
                throw new Exception(
                        "Sistem ne može da uspostavi konekciju sa bazom podataka. "
                                + "Proverite konfiguraciju i da li je MySQL pokrenut.");
            }
            return connection;
        } catch (SQLException ex) {
            throw new Exception(
                    "Sistem ne može da uspostavi konekciju sa bazom podataka. "
                            + "Proverite konfiguraciju i da li je MySQL pokrenut.",
                    ex);
        }
    }
}
