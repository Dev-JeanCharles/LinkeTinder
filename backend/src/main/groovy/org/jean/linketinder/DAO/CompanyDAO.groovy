package org.jean.linketinder.DAO


import groovy.sql.Sql
import org.jean.linketinder.Entities.Company

class CompanyDAO {

    Sql sql = Sql.newInstance(DBConection.conect())

    void create(Company company) {
        try {
            sql.execute("INSERT INTO companies (name, email, cnpj, country, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)", [
                    company.name,
                    company.email,
                    company.cnpj,
                    company.country,
                    company.state,
                    company.cep,
                    company.description
            ])

            println("Empresa adicionada com sucesso!")

        } catch (Exception e) {
            println("Erro ao adicionar empresa: ${e.message}")
        }
    }

    List<Company> getAll() {
        return sql.rows("SELECT * FROM companies") as List<Company>
    }

    void update(String cnpj, Company company) {
        try {
            sql.execute("UPDATE companies SET name = ?, email = ?, cnpj = ?, country = ?, state = ?, cep = ?, description = ? WHERE cnpj = ?", [
                    company.name,
                    company.email,
                    company.cnpj,
                    company.country,
                    company.state,
                    company.cep,
                    company.description,
                    cnpj
            ])

            println("Empresa atualizada com sucesso!")

        } catch (Exception e) {
            println("Erro ao atualizar empresa: ${e.message}")
        }
    }
    void delete(String cnpj) {
        try {
            sql.execute("DELETE FROM companies WHERE cnpj = ?", [cnpj])

            println("Empresa removida com sucesso!")

        } catch (Exception e) {
            println("Erro ao remover empresa: ${e.message}")
        }
    }
}


