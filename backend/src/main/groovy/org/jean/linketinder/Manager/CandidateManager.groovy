package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.View.PrintOperationsView

class CandidateManager {
    private static PrintOperationsView printView = new PrintOperationsView()
    private static CandidateDAO candidateDAO = new CandidateDAO()
    private static Scanner scanner = new Scanner(System.in)

    static void createCandidate() {
        Candidate newCandidate = printView.createCandidate(scanner)
        candidateDAO.create(newCandidate)
    }

    static void displayCandidates() {
        List<Candidate> candidates = candidateDAO.getAll()

        if (candidates.isEmpty()) {
            println "Não há candidatos cadastrados."
            return
        }

        println "Candidatos cadastrados:"
        candidates.each { candidate ->
            printView.displayCandidateInfo(candidate)
        }
    }

    static void updateCandidate(){
        Candidate candidate = printView.updateCandidate(scanner)
        candidateDAO.update(candidate.cpf, candidate)
    }

    static void deleteCandidate(){
        String cpf = printView.deleteCandidate(scanner)
        candidateDAO.delete(cpf)
    }
}
