package org.jean.linketinder.DAO

import java.sql.Connection
import java.sql.DriverManager

interface DatabaseConnection {
    Connection connect()
}

class DBConnection implements  DatabaseConnection {

    static Connection connection = null

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