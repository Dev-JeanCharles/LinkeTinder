package Manager

import entities.Candidates
import entities.Company

static void menuManager() {
    Scanner scanner = new Scanner(System.in)

    Candidates candidates = new Candidates()
    Company companies = new Company()

    while (true) {
        println("Escolha uma opção:")
        println("[1] Iniciar o LinkeTinder")
        println("[2] Sair")

        println("Digite o número da opção desejada:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                linkerTinderManager(candidates, companies)
                break
            case 2:
                println("Até mais!")
                return
            default:
                println("Opção invalida, escolha uma opção válida.")
        }
    }
}
static void linkerTinderManager(Candidates candidates, Company companies) {
    Scanner scanner = new Scanner(System.in)
    while (true) {
        println("Escolha uma opção:")
        println("[1] Listar todas as empresas")
        println("[2] Listar todos os candidatos")
        println("[3] Criar um novo candidato")
        println("[4] Criar uma nova empresa")
        println("[5] Voltar para o menu principal")

        println("Digite o número da opção desejada:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                CompanyManager.getAllCompany(companies)
                break
            case 2:
                CandidatesManager.getAllCandidates(candidates)
                break
            case 3:
                CandidatesManager.insertNewCandidate(candidates)
                break
            case 4:
                CompanyManager.insertNewCompany(companies)
                break
            case 5:
                return
            default:
                println("Opção invalida, escolha uma opção válida.")
        }
    }
}

