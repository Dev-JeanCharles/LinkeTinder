package Manager


import entities.Company
import enums.Skills

static void getAllCompany() {
    insertCompany()

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

static void insertCompany() {
    def company = new Company()

    company.companyList << new Company("TechSolutions", "techsolutions@gmail.com", "RJ", "24890-000", "06.924.730/0001-65", "Brasil", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    company.companyList << new Company("InnovateTech", "innovatetech@gmail.com", "SP", "07932-000", "80.559.285/0001-34", "Brasil", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    company.companyList << new Company("TechGenius", "techgenius@gmail.com", "RS", "92425-280", "42.460.971/0001-64", "Brasil", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    company.companyList << new Company("DigitalPulse", "digitalpulse@gmail.com", "MG", "35660-301", "61.474.636/0001-60", "Brasil", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    company.companyList << new Company("NexTech", "nextech@gmail.com", "SC", "89052-340", "95.621.897/0001-87", "Brasil", "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")

    company.companyList[0].skillsList << Skills.Java
    company.companyList[0].skillsList << Skills.Groovy
    company.companyList[1].skillsList << Skills.Python
    company.companyList[2].skillsList << Skills.Java
    company.companyList[2].skillsList << Skills.Ruby
    company.companyList[2].skillsList << Skills.Spring
    company.companyList[3].skillsList << Skills.Python
    company.companyList[3].skillsList << Skills.Groovy
    company.companyList[3].skillsList << Skills.TDD
    company.companyList[4].skillsList << Skills.Java
    company.companyList[4].skillsList << Skills.Angular

    company.companyList.each { companies ->
        println(companies.toString())
    }

}

