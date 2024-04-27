package org.jean.linketinder.Interfaces.Repository

import org.jean.linketinder.Model.Entity.Company

interface CompanyRepository {
    Company create(Company company)
    List<Company> getAll()
    Integer getCompanyIdByCnpj(String cnpj)
    void update(String cnpj, Company company)
    void delete(String cnpj)
}