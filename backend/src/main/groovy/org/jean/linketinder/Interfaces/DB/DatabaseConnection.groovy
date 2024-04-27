package org.jean.linketinder.Interfaces.DB

import java.sql.Connection

interface DatabaseConnection {
    Connection connect()
}