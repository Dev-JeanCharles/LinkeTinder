package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Factory.Factory
import org.jean.linketinder.Interfaces.Repository.CompanyRepository
import org.jean.linketinder.Queries.CompanyQueries

import java.sql.SQLException

class CompanyDAO implements CompanyRepository{


    private final HandleException exception
    private final Sql sql
    private final VacancyDAO vacancyDAO
    private final CompanyQueries companyQueries

    CompanyDAO() {
        this.exception = Factory.createHandleException()
        this.sql = new Sql(Factory.createDBConnection().connect())
        this.vacancyDAO = Factory.createVacancyDAO()
        this.companyQueries = Factory.createCompanyQueries()
    }

    @Override
    Company create(Company company) {

        List<String> parameters = [
                company.name,
                company.email,
                company.cnpj,
                company.country,
                company.state,
                company.cep,
                company.description
        ]

        try {
            sql.executeInsert(companyQueries.INSERT_COMPANY_QUERY, parameters)

            Integer generatedId = sql.firstRow(companyQueries.GET_ID_QUERY)?.id as Integer

            if (generatedId != null) {
                company.id = generatedId
                println("Empresa adicionada com sucesso!")
                return company

            } else {
                println("Erro ao recuperar o ID da empresa recém-adicionada.")
                return null
            }

            } catch (SQLException e) {
                exception.handleException("Erro ao adicionar empresa", e)
            }
        }

    @Override
    List<Company> getAll() {

        List<Company> companies = []

        try {
            List<Map<String, Object>> rows = sql.rows(companyQueries.GET_ALL_COMPANIES_QUERY)

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

        } catch (SQLException e) {
            exception.handleException("Erro ao recuperar empresas", e)
        }
        return companies
    }

    @Override
    Integer getCompanyIdByCnpj(String cnpj) {
        try {
            Map<String, Object> result = sql.firstRow(companyQueries.GET_CNPJ_COMPANY_QUERY, [cnpj])

            if (result) {
                return result.id as Integer
            } else {
                println("Empresa com CNPJ $cnpj não encontrada.")
                return null
            }

        } catch (SQLException e) {
            exception.handleException("Erro ao obter o ID da empresa pelo CNPJ", e)
            return null
        }
    }

    @Override
    void update(String cnpj, Company company) {
        try {
            sql.execute(companyQueries.UPDATE_COMPANY_QUERY, [
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

        } catch (SQLException e) {
            exception.handleException("Erro ao atualizar empresa", e)
        }
    }

    @Override
    void delete(String cnpj) {

        Integer companyId = getCompanyIdByCnpj(cnpj)

        try {
            if (companyId != null) {
            vacancyDAO.deleteByCompanyId(companyId)
            }

            sql.execute(companyQueries.DELETE_COMPANY_QUERY, [cnpj])

            println("Empresa removida com sucesso!")

        } catch (SQLException e) {
            exception.handleException("Erro ao remover empresa", e)
        }
    }
}
