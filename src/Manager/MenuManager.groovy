package Manager

import entities.Company

static void menuManager() {
    Scanner scanner = new Scanner(System.in)

    while (true) {
        println("Escolha uma opção:")
        println("[1] Iniciar o LinkeTinder")
        println("[2] Sair")

        println("Digite o número da opção desejada:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                linkerTinderManager()
                break
            case 2:
                println("Até mais!")
                return
            default:
                println("Opção invalida, escolha uma opção válida.")
        }
    }
}
static void linkerTinderManager() {
    Scanner scanner = new Scanner(System.in)
    while (true) {
        println("Escolha uma opção:")
        println("[1] Listar todas as empresas")
        println("[2] Listar todos os candidatos")
        println("[3] Voltar para o menu principal")

        println("Digite o número da opção desejada:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                CompanyManager.getAllCompany()
                break
            case 2:
                CandidatesManager.getAllCandidates()
                break
            case 3:
                return
            default:
                println("Opção invalida, escolha uma opção válida.")
        }
    }
}

