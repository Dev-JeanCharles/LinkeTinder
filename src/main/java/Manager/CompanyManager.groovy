package Manager

import entities.Company
import enums.Skills

static void getAllCompany(Company company) {
    company.getCompanyList().each { companies ->
        println(companies.toString())
    }

    Scanner scanner = new Scanner(System.in)

    while (true) {
        println("Digite 1 para sair:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                return
            default:
                println("Opção invalida, escolha uma opção válida.")
        }
    }
}

static insertNewCompany(Company company) {
    Scanner sc = new Scanner(System.in)

    println("Insira um nome de sua empresa:")
    String name = sc.nextLine()

    println("Insira o email de sua empresa:")
    String email = sc.nextLine()

    println("Insira o seu estado de sua empresa:")
    String state = sc.nextLine()

    println("Insira o seu cep de sua empresa:")
    String cep = sc.nextLine()

    println("Insira o CNPJ de sua empresa:")
    String cnpj = sc.nextLine()

    println("Insira o país de sua empresa:")
    String country = sc.nextLine()

    println("Insira a descrição da sua empresa:")
    String companyDescription = sc.nextLine()

    Company newCompany = new Company(name, email, state, cep, cnpj, country, companyDescription)

    println("Insira as habilidades obrigatórias para o candidato (Insira o número correspondente, separe as múltiplas habilidades com vírgulas)")
    Skills[] skills = Skills.values()
    for (int i = 0; i < skills.length; i++) {
        println("$i: ${skills[i]}")
    }
    String skillsInput = sc.nextLine()
    String[] SkillsIndex = skillsInput.split(",")
    for (String skillIndexString : SkillsIndex) {
        Integer skillIndex = Integer.parseInt(skillIndexString.trim())
        if (skillIndex >= 0 && skillIndex < skills.length) {
            newCompany.getSkillsList().add(skills[skillIndex])
        } else {
            println("Inseriu uma habilidade incorreta: $skillIndex")
        }
    }

    company.getCompanyList().add(newCompany)

    println("Seu perfil de candidato foi criado com sucesso!")
    println(newCompany.toString())

    return newCompany

}

