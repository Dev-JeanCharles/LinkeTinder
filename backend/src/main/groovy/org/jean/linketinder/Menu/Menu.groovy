package org.jean.linketinder.Menu
import org.jean.linketinder.Controller.CandidateController
import org.jean.linketinder.Controller.CompanyController

import javax.swing.ButtonGroup

class Menu {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))

    static void menuHome(String options) throws IOException{

        do {
            println("Escolha uma das opções a seguir:")
            println("[1] Iniciar a aplicação")
            println("[2] Sair da aplicação")

            options = reader.readLine()

            switch (options) {
                case '1':
                    startMenuOperations(options)
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

    static void startMenuOperations(String options) throws IOException{

        do {
            println("Seja bem-vindo ao LinkeTinder!")
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

            options = reader.readLine()

            switch (options){
                case '1' :
                    new CandidateController().getCandidate()
                    break
                case '2' :
                    new CompanyController().getCompany()
                    break
                case '3' :
                    new CandidateController().createCandidate()
                    break
                case '4':
                    new CompanyController().createCompany()
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
                    println("Você saiu do LinkeTinder!")
                    break
                default:
                    println("Opção Inválida. Tente novamente!")
                    break
            }
        } while (options != '9')
    }
}
