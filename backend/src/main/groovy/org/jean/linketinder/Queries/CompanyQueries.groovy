package org.jean.linketinder.Queries

class CompanyQueries {
    static String GET_ALL_COMPANIES_QUERY = "SELECT * FROM companies"
    static String GET_ID_QUERY = "SELECT lastval() as id"
    static String GET_CNPJ_COMPANY_QUERY = "SELECT id FROM companies WHERE cnpj = ?"
    static String INSERT_COMPANY_QUERY = "INSERT INTO companies (name, email, cnpj, country, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)"
    static String UPDATE_COMPANY_QUERY = "UPDATE companies SET name = ?, email = ?, cnpj = ?, country = ?, state = ?, cep = ?, description = ? WHERE cnpj = ?"
    static String DELETE_COMPANY_QUERY = "DELETE FROM companies WHERE cnpj = ?"
}
