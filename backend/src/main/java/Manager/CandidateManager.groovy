package Manager

import DTO.CandidateDTO
import entities.Candidate

class CandidateManager {
    private CandidateDTO candidateDTO;

    CandidateManager() {
        this.candidateDTO = new CandidateDTO();
    }

    static void create() {
        Scanner scanner = new Scanner(System.in)

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

        Candidate candidate = new Candidate(name, email, cpf, age, state, cep, description, skills)
        new CandidateDTO().Create(candidate)
    }

    void get() {
        List<Candidate> candidates = candidateDTO.Get()

        if (candidates.isEmpty()) {
            println "Não há candidatos cadastrados."
        } else {
            println "Candidatos cadastrados:"
            candidates.each { candidate ->
                print "Nome: ${candidate.name}\nEmail: ${candidate.email}\nCPF: ${candidate.cpf}\nIdade: ${candidate.age}\nEstado: ${candidate.state}\nCEP: ${candidates.cep}\nDescrição Pessoal: ${candidate.description}\n"

                if (!candidate.skillsList.empty) {
                    println "Competências: ${candidate.skillsList.collect { it }.join(', ')}"
                } else {
                    println "Nenhuma competência cadastrada para este candidato."
                }
                println ""
            }
        }
    }

    void update(){
        Scanner scanner = new Scanner(System.in)

        print "Digite o CPF do candidato que deseja atualizar: "
        String cpf = scanner.nextLine()

        print "Digite o novo nome do candidato: "
        String name = scanner.nextLine()

        print "Digite o novo email do candidato: "
        String email = scanner.nextLine()

        print "Digite a nova idade do candidato: "
        int age = scanner.nextInt()
        scanner.nextLine()

        print "Digite o novo estado do candidato: "
        String state = scanner.nextLine()

        print "Digite o novo CEP do candidato: "
        String cep = scanner.nextLine()

        print "Digite a nova descrição do candidato: "
        String description = scanner.nextLine()

        print "Digite as novas competências do candidato (separadas por vírgula): "
        List<String> skills = Arrays.asList(scanner.nextLine().split(',')).collect { it.trim() }

        Candidate candidate = new Candidate(name, email, cpf, age, state, cep, description, skills)
        candidateDTO.Update(cpf, candidate)
    }

    void delete(){
        Scanner scanner = new Scanner(System.in)

        print "Digite o CPF do candidato que deseja deletar: "
        String cpf = scanner.nextLine()

        candidateDTO.Delete(cpf)
    }
}
