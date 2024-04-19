package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Enum.Skills
import org.jean.linketinder.View.PrintOperationsView

class CandidateManager {
    private static Candidate candidate
    private static PrintOperationsView print
    private static final Scanner scanner = new Scanner(System.in)
    private static final CandidateDAO candidateDAO = new CandidateDAO();
    private static final List<Candidate> candidates = candidateDAO.Get()



    static void create() {
        candidate = print.CreateCandidate(scanner)
        candidateDAO.Create(candidate)
    }

    static void get() {

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

        Candidate candidate = new Candidate(name, email, cpf, age, state, cep, description, skills as List<Skills>)
        candidateDTO.Update(cpf, candidate)
    }

    void delete(){
        Scanner scanner = new Scanner(System.in)

        print "Digite o CPF do candidato que deseja deletar: "
        String cpf = scanner.nextLine()

        candidateDTO.Delete(cpf)
    }
}

