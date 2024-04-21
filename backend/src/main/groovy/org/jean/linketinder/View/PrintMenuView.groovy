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
        println("[1] Listar todos os Candidatos")
        println("[2] Listar todas as Empresas")
        println("[3] Listar todas as vagas")
        println("[4] Cadastrar Candidato")
        println("[5] Atualizar Candidato")
        println("[6] Excluir Candidato")
        println("[7] Cadastrar Empresa")
        println("[8] Atualizar Empresa")
        println("[9] Excluir Empresa")
        println("[10] Cadastrar Vaga")
        println("[11] Atualizar Vaga")
        println("[12] Excluir Vaga")
        println("[13] Sair")
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
