package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Entities.Company

class CompanyManager {
    CompanyDAO companyDTO = new CompanyDAO()

    void create() {
        Scanner scanner = new Scanner(System.in)

        print "Digite o nome da empresa: "
        String name = scanner.nextLine()

        print "Digite o email da empresa: "
        String email = scanner.nextLine()

        print "Digite o CNPJ da empresa: "
        String cnpj = scanner.nextLine()

        print "Digite o país da empresa: "
        String country = scanner.nextLine()

        print "Digite o estado da empresa: "
        String state = scanner.nextLine()

        print "Digite o CEP da empresa: "
        String cep = scanner.nextLine()

        print "Digite a descrição da empresa: "
        String description = scanner.nextLine()

        print "Digite as competências da empresa (separadas por vírgula): "
        List<String> skills = scanner.nextLine().split(',').collect { it.trim() }

        Company company = new Company(name, email, cnpj, country, state, cep, description, skills as List<Skills>)
        companyDTO.Create(company)
    }
    void get() {
        List<Company> companies = companyDTO.Get()

        if (companies.isEmpty()) {
            println "Não há empresas cadastradas."
        } else {
            println "Empresas cadastradas:"
            companies.each { company ->
                print "Empresa: ${company.name}\nEmail Corporativo: ${company.email}\nCNPJ: ${company.cnpj}\nPais: ${company.country}\nEstado: ${company.state}\nCEP: ${company.cep}\nDescrição da Empresa: ${company.description}\n\n"
            }
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

        Company company = new Company(name, email, cnpj, country, state, cep, description, skills as List<Skills>)
        companyDTO.Update(cnpj, company)
    }

    void delete() {
        Scanner scanner = new Scanner(System.in)

        print "Digite o CNPJ da empresa que deseja deletar: "
        String cnpj = scanner.nextLine()

        companyDTO.Delete(cnpj)
    }
}
