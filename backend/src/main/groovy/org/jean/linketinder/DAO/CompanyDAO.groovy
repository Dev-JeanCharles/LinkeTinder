package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Exceptions.HandleException

class CompanyDAO {
    private static final String GET_ALL_COMPANIES_QUERY = "SELECT * FROM companies"
    private static final String GET_ID_QUERY = "SELECT lastval() as id"
    private static final String INSERT_COMPANY_QUERY = "INSERT INTO companies (name, email, cnpj, country, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)"
    private static final String UPDATE_COMPANY_QUERY = "UPDATE companies SET name = ?, email = ?, cnpj = ?, country = ?, state = ?, cep = ?, description = ? WHERE cnpj = ?"
    private static final String DELETE_COMPANY_QUERY = "DELETE FROM companies WHERE cnpj = ?"

    HandleException exception = new HandleException()
    Sql sql = Sql.newInstance(DBConection.conect())

    Company create(Company company) {
        try {
            List<String> parameters = [
                    company.name,
                    company.email,
                    company.cnpj,
                    company.country,
                    company.state,
                    company.cep,
                    company.description
            ]
            sql.executeInsert(INSERT_COMPANY_QUERY, parameters)

            Integer generatedId = sql.firstRow(GET_ID_QUERY)?.id as Integer

            if (generatedId != null) {
                company.id = generatedId
                println("Empresa adicionada com sucesso!")
                return company
            } else {
                println("Erro ao recuperar o ID da empresa recém-adicionada.")
                return null
                }
            } catch (Exception e) {
                exception.handleException("Erro ao adicionar empresa", e)
                return null
            }
        }

    List<Company> getAll() {
        List<Company> companies = []
        try {
            List<Map<String, Object>> rows = sql.rows(GET_ALL_COMPANIES_QUERY)
            rows.each { row ->
                companies.add(new Company(
                        row.id as Integer,
                        row.name as String,
                        row.email as String,
                        row.cnpj as String,
                        row.country as String,
                        row.state as String,
                        row.cep as String,
                        row.description as String
                ))
            }
        } catch (Exception e) {
            exception.handleException("Erro ao recuperar empresas", e)
        }
        return companies
    }

    void update(String cnpj, Company company) {
        try {
            sql.execute(UPDATE_COMPANY_QUERY, [
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
            exception.handleException("Erro ao atualizar empresa", e)
        }
    }

    void delete(String cnpj) {
        try {
            sql.execute(DELETE_COMPANY_QUERY, [cnpj])
            println("Empresa removida com sucesso!")
        } catch (Exception e) {
            exception.handleException("Erro ao remover empresa", e)
        }
    }
}
