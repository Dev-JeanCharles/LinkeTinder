package org.jean.linketinder.Interfaces.DB

import java.sql.Connection
import java.sql.DriverManager

class DBConnection implements  DatabaseConnection {

    private static DBConnection instance
    private Connection connection

    private DBConnection() {
        String dbname = "linkertinder"
        String user = "postgres"
        String password = "123"

        try {
            Class.forName("org.postgresql.Driver")
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/$dbname", user, password)
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection()
        }
        return instance
    }

    @Override
    Connection connect() {
        return connection
    }
}
