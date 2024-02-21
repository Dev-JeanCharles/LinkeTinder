package Manager

import entities.Candidates
import enums.Skills

static void getAllCandidates() {
    insertCandidates()

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

static void insertCandidates() {
    def candidates = new Candidates()

    candidates.candidatesList << new Candidates("Jean", "jean@gmail.com", "RJ", "24890-000", "15851468-00", 19, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    candidates.candidatesList << new Candidates("Vitor", "vitor@gmail.com", "SP", "07932-000", "485.592.050-98", 32, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    candidates.candidatesList << new Candidates("Cláudio", "claudio@gmail.com", "RS", "92425-280", "277.654.070-15", 20, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    candidates.candidatesList << new Candidates("Thales", "thales@gmail.com", "MG", "35660-301", "058.776.730-84", 21, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    candidates.candidatesList << new Candidates("Maycon", "maycon@gmail.com", "SC", "89052-340", "789.223.650-30", 23, "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")


    candidates.candidatesList[0].skillsList << Skills.Java
    candidates.candidatesList[0].skillsList << Skills.Groovy
    candidates.candidatesList[1].skillsList << Skills.Python
    candidates.candidatesList[2].skillsList << Skills.Java
    candidates.candidatesList[2].skillsList << Skills.Ruby
    candidates.candidatesList[2].skillsList << Skills.Spring
    candidates.candidatesList[3].skillsList << Skills.Python
    candidates.candidatesList[3].skillsList << Skills.Groovy
    candidates.candidatesList[3].skillsList << Skills.TDD
    candidates.candidatesList[4].skillsList << Skills.Java
    candidates.candidatesList[4].skillsList << Skills.Angular

    candidates.candidatesList.each { candidate ->
        println(candidate.toString())
    }
}






