package DTO

import java.sql.Connection
import java.sql.DriverManager

public class ConectionDTO {

    static Connection connection = null

    static Connection conect() {

        String dbname = "linkertinder"
        String user = "postgres"
        String password = "123"

        try {
            Class.forName("org.postgresql.Driver")
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/$dbname", user, password)
            if (connection != null) {
                println("Conexão estabelecida!")
            } else {
                println("Falha na conexão!")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
        return connection
    }
    static void Close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close()
                println("Conexão encerrada!")
            }
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}

