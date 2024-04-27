package org.jean.linketinder.Interfaces.DB

import java.sql.Connection
import java.sql.DriverManager

class DBConnection implements  DatabaseConnection {

    Connection connection = null

    @Override
    Connection connect() {
        String dbname = "linkertinder"
        String user = "postgres"
        String password = "123"

        try {
            Class.forName("org.postgresql.Driver")

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/$dbname", user, password)

        } catch (Exception e) {
            e.printStackTrace()
        }
        return connection
    }
}
