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
        println("[1] Listar Todos os Candidatos")
        println("[2] Listar Todas as Empresas")
        println("[3] Cadastrar Candidato")
        println("[4] Cadastrar Empresa")
        println("[5] Atualizar Candidato")
        println("[6] Atualizar Empresa")
        println("[7] Excluir Candidato")
        println("[8] Excluir Empresa")
        println("[9] Sair")
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
