package org.jean.linketinder.Interfaces.DB

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DBConnection implements  DatabaseConnection {

    //volatile fará com que a instância seja sempre lida diretamente da memória principal.
    private static volatile DBConnection instance

    Connection connection = null

    private DBConnection() {
        String dbname = "linketinder"
        String user = "postgres"
        String password = "123"

        try {
            Class.forName("org.postgresql.Driver")
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/$dbname", user, password)
        } catch (ClassCastException | SQLException e) {
            e.printStackTrace()
        }
    }

    static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection) {

                if (instance == null) {
                 instance = new DBConnection()
                }
            }
        }
        return instance
    }

    @Override
    Connection connect() {
        return connection
    }
}
