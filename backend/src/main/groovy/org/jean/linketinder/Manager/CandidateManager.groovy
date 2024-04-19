package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.View.PrintOperationsView

class CandidateManager {
    private static Candidate candidate
    private static PrintOperationsView print = new PrintOperationsView()
    private static Scanner scanner = new Scanner(System.in)
    private static CandidateDAO candidateDAO = new CandidateDAO()
    private static List<Candidate> candidates = candidateDAO.Get()

    static void createCandidate() {
        candidate = print.CreateCandidate(scanner)
        candidateDAO.Create(candidate)
    }

    static void getCandidate() {
        candidates = candidateDAO.Get()

        checkIfNotCandidate()

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

    static void checkIfNotCandidate() {
        if (candidates.isEmpty()) {
            println "Não há candidatos cadastrados."
        }
    }

    static void updateCandidate(){
        candidate = print.UpdateCandidate(scanner)

        String cpf = candidate.cpf

        candidateDAO.Update(cpf,candidate)
    }

    static void deleteCandidate(){

        print "Digite o CPF do candidato que deseja deletar: "
        String cpf = scanner.nextLine()

        candidateDAO.Delete(cpf)
    }
}
