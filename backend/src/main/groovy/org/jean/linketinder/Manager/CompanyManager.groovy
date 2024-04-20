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
        Scanner scanner = new Scanner(System.in)

        print "Digite o CNPJ da empresa que deseja atualizar: "
        String cnpj = scanner.nextLine()

        print "Digite o novo nome da empresa: "
        String name = scanner.nextLine()

        print "Digite o novo email da empresa: "
        String email = scanner.nextLine()

        print "Digite o novo país da empresa: "
        String country = scanner.nextLine()

        print "Digite o novo estado da empresa: "
        String state = scanner.nextLine()

        print "Digite o novo CEP da empresa: "
        String cep = scanner.nextLine()

        print "Digite a nova descrição da empresa: "
        String description = scanner.nextLine()

        print "Digite as novas competências da empresa (separadas por vírgula): "
        List<String> skills = scanner.nextLine().split(',').collect { it.trim() }

        Company company = new Company(name, email, cnpj, country, state, cep, description, skills as List<Skill>)
        companyDAO.Update(cnpj, company)
    }

    void delete() {
        Scanner scanner = new Scanner(System.in)

        print "Digite o CNPJ da empresa que deseja deletar: "
        String cnpj = scanner.nextLine()

        companyDAO.Delete(cnpj)
    }
}
