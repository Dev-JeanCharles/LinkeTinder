package org.jean.linketinder.DAO

import groovy.sql.Sql

class DBOperations {

    private Sql sql

    DBOperations(Sql instance) {
        this.sql = instance
    }

    void createTable(String nameTable, List<String> fields) {
        try {
            if (ExistTable(nameTable)) {
                println("A tabela $nameTable já existe.")
                return
            }
            String fieldsString = fields.join(", ")
            String query = "CREATE TABLE $nameTable ($fieldsString)"
            sql.execute(query)

            println("Tabela $nameTable criada com sucesso!")

        } catch (Exception e) {
            println("Erro ao criar tabela $nameTable: ${e.message}")
        }
    }

    private boolean ExistTable(String nameTable) {
        try {
            def result = sql.firstRow("""
            SELECT EXISTS(
                SELECT 1
                FROM   information_schema.tables
                WHERE  table_name = ?
            )""", [nameTable])
            return result?.EXISTS
        } catch (Exception e) {
            println("Erro ao verificar a existência da tabela $nameTable: ${e.message}")
            return false
        }
    }
}



