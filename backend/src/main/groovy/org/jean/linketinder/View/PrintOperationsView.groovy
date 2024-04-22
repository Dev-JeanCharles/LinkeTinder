package org.jean.linketinder.View

import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy

class PrintOperationsView {
    static Candidate createCandidate(Scanner scanner) {
        println "Digite o nome do candidato: "
        String name = scanner.nextLine()

        println "Digite o email do candidato: "
        String email = scanner.nextLine()

        println "Digite o CPF do candidato: "
        String cpf = scanner.nextLine()

        println "Digite a idade do candidato: "
        int age = scanner.nextInt()

        scanner.nextLine()
        println "Digite o estado do candidato: "
        String state = scanner.nextLine()

        println "Digite o CEP do candidato: "
        String cep = scanner.nextLine()

        println "Digite a descrição do candidato: "
        String description = scanner.nextLine()

        println "Digite as competências do candidato (separadas por vírgula): "
        List<String> skills = parseSkills(scanner.nextLine()) as List<String>

        Candidate candidate = new Candidate(name, email, state, cep, description, skills as List<Skill>, null, cpf, age, null)
        return candidate
    }

    static Candidate updateCandidate(Scanner scanner) {
        print "Digite o CPF do candidato que deseja atualizar: "
        String cpf = scanner.nextLine()

        print "Digite o novo nome do candidato: "
        String name = scanner.nextLine()

        print "Digite o novo email do candidato: "
        String email = scanner.nextLine()

        print "Digite a nova idade do candidato: "
        int age = scanner.nextInt()
        scanner.nextLine()

        print "Digite o novo estado do candidato: "
        String state = scanner.nextLine()

        print "Digite o novo CEP do candidato: "
        String cep = scanner.nextLine()

        print "Digite a nova descrição do candidato: "
        String description = scanner.nextLine()

        print "Digite as novas competências do candidato (separadas por vírgula): "
        List<String> skillNames = parseSkills(scanner.nextLine()) as List<String>

        List<Skill> skills = skillNames.collect { new Skill(it) }

        Candidate candidate = new Candidate(name, email, state, cep, description, skills as List<String> as List<Skill>, null, cpf, age, null)
        return candidate
    }

    static String deleteCandidate(Scanner scanner) {
        print "Digite o CPF do candidato que deseja deletar: "
        return scanner.nextLine()
    }

    static void displayCandidateInfo(Candidate candidate) {
        println("Nome: ${candidate.getName()}" +
                "\nEmail: ${candidate.getEmail()}" +
                "\nCPF: ${candidate.getCpf()}" +
                "\nIdade: ${candidate.getAge()}" +
                "\nEstado: ${candidate.getState()}" +
                "\nCEP: ${candidate.getCep()}" +
                "\nDescrição Pessoal: ${candidate.getDescription()}")

        if (!candidate.skills.empty) {
            println "Competências: ${candidate.skills.join(', ')}"
            println("")
        } else {
            println "Nenhuma competência cadastrada para este candidato."
        }
    }

    static Company createCompany(Scanner scanner) {
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

        Company company = new Company(null, name, email, cnpj, country, state, cep, description)

        return company
    }

    static void displayCompanyInfo(Company company) {
        println("Nome: ${company.getName()}" +
                "\nEmail: ${company.getEmail()}" +
                "\nCNPJ: ${company.getCnpj()}" +
                "\nPaís: ${company.getCountry()}" +
                "\nEstado: ${company.getState()}" +
                "\nCEP: ${company.getCep()}" +
                "\nDescrição Pessoal: ${company.getDescription()}"+
                "\n")
    }

    static Company updateCompany(Scanner scanner) {
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

        Company company = new Company(null, name, email, cnpj, country, state, cep, description)

        return company
    }

    static String deleteCompany(Scanner scanner) {
        print "Digite o CNPJ da empresa que deseja deletar: "
        return scanner.nextLine()
    }

    static Vacancy createVacancy(Scanner scanner, Company selectedCompany, List<Skill> skills) {
        println "Digite o nome da vaga: "
        String name = scanner.nextLine()

        println "Digite a localidade da vaga: "
        String locality = scanner.nextLine()

        println "Digite a descrição da vaga: "
        String description = scanner.nextLine()

        List<String> skillNames = skills.collect { it.getName() }

        Vacancy vacancy = new Vacancy(null, name, locality, description, skillNames as List<Skill>)

        if (selectedCompany?.id != null) {
            vacancy.setCompany(selectedCompany)
        } else {
            println "O ID da empresa não está definido. A vaga não será associada à empresa."
        }
        return vacancy
    }

    static Company selectExistingCompany(Scanner scanner, List<Company> companies) {
        println "Selecione a empresa da lista abaixo:"
        displayCompanies(companies)

        if (companies.isEmpty()) {
            println "Nenhuma empresa disponível para seleção."
            return null
        }
        print "Digite o número correspondente à empresa selecionada: "
        int companyIndex = scanner.nextInt() - 1
        scanner.nextLine()

        if (companyIndex >= 0 && companyIndex < companies.size()) {
            return companies[companyIndex]
        } else {
            println "Empresa selecionada inválida."
            return null
        }
    }

    static void displayCompanies(List<Company> companies) {
        companies.eachWithIndex { company, index ->
            println "${index + 1}. ${company.getName()}"
        }
    }

    static List<String> parseSkills(String skillsInput) {
        String[] skillsArray = skillsInput.split(",")
        List<String> skillNames = new ArrayList<>()
        for (String skill : skillsArray) {
            skillNames.add(skill.trim())
        }
        return skillNames
    }


}
