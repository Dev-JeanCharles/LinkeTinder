package Manager

import entities.Candidates
import enums.Skills
import java.util.Scanner

static void getAllCandidates(Candidates candidates) {
    candidates.getCandidatesList().forEach { println(it)}
    Scanner scanner = new Scanner(System.in)

    while (true) {
        println("Digite 1 para sair:")
        int option = scanner.nextInt()

        switch (option) {
            case 1:
                return
            default:
                println("Opção inválida, escolha uma opção válida.")
        }
    }
}

static insertNewCandidate(Candidates candidates) {
    Scanner sc = new Scanner(System.in)

    println("Insira um nome:")
    String name = sc.nextLine()

    println("Insira um email:")
    String email = sc.nextLine()

    println("Insira o seu estado de nascimento:")
    String state = sc.nextLine()

    println("Insira o seu cep:")
    String cep = sc.nextLine()

    println("Insira o seu CPF:")
    String cpf = sc.nextLine()

    println("Insira a sua idade:")
    Integer age = sc.nextInt()

    println("Insira uma descrição para o seu perfil:")
    sc.nextLine()
    String personalDescription = sc.nextLine()

    Candidates newCandidates = new Candidates(name, email, state, cep, cpf, age, personalDescription)

    println("Insira as suas habilidades (Insira o número correspondente, separe as múltiplas habilidades com vírgulas)")
    Skills[] skills = Skills.values()
    for (int i = 0; i < skills.length; i++) {
        println("$i: ${skills[i]}")
    }
    String skillsInput = sc.nextLine()
    String[] SkillsIndex = skillsInput.split(",")
    for (String skillIndexString : SkillsIndex) {
        Integer skillIndex = Integer.parseInt(skillIndexString.trim())
        if (skillIndex >= 0 && skillIndex < skills.length) {
            newCandidates.getSkillsList().add(skills[skillIndex])
        } else {
            println("Inseriu uma habilidade incorreta: $skillIndex")
        }
    }

    candidates.getCandidatesList().add(newCandidates)

    println("Seu perfil de candidato foi criado com sucesso!")
    println(newCandidates.toString())

    return newCandidates
}
