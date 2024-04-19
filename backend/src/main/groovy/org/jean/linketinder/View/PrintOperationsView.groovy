package org.jean.linketinder.View


import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Enum.Skills

class PrintOperationsView {

    static CreateCandidate(Scanner scanner) {
        print "Digite o nome do candidato: "
        String name = scanner.nextLine()

        print "Digite o email do candidato: "
        String email = scanner.nextLine()

        print "Digite o CPF do candidato: "
        String cpf = scanner.nextLine()

        print "Digite a idade do candidato: "
        int age = scanner.nextInt()
        scanner.nextLine()

        print "Digite o estado do candidato: "
        String state = scanner.nextLine()

        print "Digite o CEP do candidato: "
        String cep = scanner.nextLine()

        print "Digite a descrição do candidato: "
        String description = scanner.nextLine()

        print "Digite as competências do candidato (separadas por vírgula): "
        List<String> skills = scanner.nextLine().split(',').collect { it.trim() }

        return new Candidate(name, email, cpf, age, state, cep, description, skills as List<Skills>)
    }
}
