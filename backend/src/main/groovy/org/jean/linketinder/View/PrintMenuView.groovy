package org.jean.linketinder.View

class PrintMenuView {

    static void initialOptionsMenu() {
        println("Escolha uma das opções a seguir:")
        println("[1] Iniciar a aplicação")
        println("[2] Sair da aplicação")
    }

    static void initialOperationsMenu() {

        println("Seja bem-vindo ao LinkeTinder!")
        println("Escolha uma das opções a seguir:")

        println("\nBuscar")

        println("[1] Listar todos os Candidatos")
        println("[2] Listar todas as Empresas")
        println("[3] Listar todas as vagas")

        println("\nCriar")

        println("[4] Cadastrar Candidato")
        println("[5] Cadastrar Empresa")
        println("[6] Cadastrar Vaga")

        println("\nAtualizar")

        println("[7] Atualizar Candidato")
        println("[8] Atualizar Empresa")
        println("[9] Atualizar Vaga")

        println("\nDeletar")

        println("[10] Excluir Candidato")
        println("[11] Excluir Empresa")

        println("\n[12] Sair")
    }

    static void exitInitialMenu() {
        println("Até logo!")
    }

    static void invalidOptions() {
        println("Opção Inválida. Tente novamente!")
    }

    static void exitOperationsMenu() {
        println("Você saiu do LinkeTinder!")
    }
}
