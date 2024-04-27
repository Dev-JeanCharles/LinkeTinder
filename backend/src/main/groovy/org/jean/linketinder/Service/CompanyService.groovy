package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Model.Entity.Company
import org.jean.linketinder.Interfaces.Implementation.CompanyImplementation
import org.jean.linketinder.View.PrintOperationsView

class CompanyService implements CompanyImplementation.CompanyOperationsInterface{

    private PrintOperationsView printView = new PrintOperationsView()
    private CompanyDAO companyDAO

    CompanyService(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO
    }

    @Override
    void createCompany(Scanner scanner) {
        Company newCompany = printView.createCompany(scanner)
        companyDAO.create(newCompany)
    }

    @Override
    void displayCompany() {

        List<Company> companies = companyDAO.getAll()

        if (companies) {
            println("Lista de empresas:")
            for (Company company : companies) {
                printView.displayCompanyInfo(company)
            }
        } else {
            println("Nenhuma empresa encontrada.")
        }
    }

    @Override
    void update(Scanner scanner) {
        Company company = printView.updateCompany(scanner)
        companyDAO.update(company.cnpj, company)
    }

    @Override
    void delete(Scanner scanner) {
        String cnpj = printView.deleteCompany(scanner)
        companyDAO.delete(cnpj)
    }
}
