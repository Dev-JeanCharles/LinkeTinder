package Menu

import Controller.CandidateController
import Controller.CompanyController

class Menu {

    static void menuPrincipal() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

        def options;

        do {
            println("Seja bem-vindo ao LinkeTinder!")
            println("Escolha uma das opções a seguir:")
            println("[1] Iniciar a aplicação")
            println("[2] Sair da aplicação")

            options = br.readLine()

            switch (options) {
                case '1':
                    startMenu()
                    break
                case '2':
                    println("Até logo!")
                    break
                default:
                    println("Opção Inválida. Tente novamente!")
                    break
            }
        }while (options != '2')
    }

    static void startMenu(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))

        def options;

        do {
            println("Escolha uma das opções a seguir:")
            println("[1] Listar Todos os Candidatos")
            println("[2] Listar Todas as Empresas")
            println("[3] Cadastrar Candidato")
            println("[4] Cadastrar Empresa")
            println("[5] Atualizar Candidato")
            println("[6] Atualizar Empresa")
            println("[7] Excluir Candidato")
            println("[8] Excluir Empresa")
            println("[9] Sair")

            options = br.readLine()

            switch (options){
                case '1' :
                    new CandidateController().getCandidate()
                    break
                case '2' :
                    new CompanyController().getCompany()
                    break
                case '3' :
                    new CandidateController().insertCandidate()
                    break
                case '4':
                    new CompanyController().insertCompany()
                    break
                case '5' :
                    new CandidateController().updateCandidate()
                    break
                case '6':
                    new CompanyController().updateCompany()
                    break
                case '7' :
                    new CandidateController().deleteCandidate()
                    break
                case '8':
                    new CompanyController().deleteCompany()
                    break
                case '9' :
                    println("Você saiu do LinkeTinder :(")
                    break
                default:
                    println("Opção Inválida. Tente novamente!")
                    break
            }
        } while (options != '9')
    }
}

