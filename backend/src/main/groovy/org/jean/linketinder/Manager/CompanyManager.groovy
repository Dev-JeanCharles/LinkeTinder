package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.View.PrintOperationsView

class CompanyManager {

    private PrintOperationsView printView = new PrintOperationsView()
    private CompanyDAO companyDAO = new CompanyDAO() as CompanyDAO
    private Scanner scanner = new Scanner(System.in)

    CompanyManager(PrintOperationsView printView, CompanyDAO companyDAO) {
        this.printView = printView
        this.companyDAO = companyDAO
    }

    void createCompany() {
        Company newCompany = printView.createCompany(scanner)
        companyDAO.create(newCompany)
    }

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

    void update() {
        Company company = printView.updateCompany(scanner)
        companyDAO.update(company.cnpj, company)
    }

    void delete() {
        String cnpj = printView.deleteCompany(scanner)
        companyDAO.delete(cnpj)
    }
}
