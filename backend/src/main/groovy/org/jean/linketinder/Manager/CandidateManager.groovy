package org.jean.linketinder.Manager

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.View.PrintOperationsView

class CandidateManager {
    private PrintOperationsView printView = new PrintOperationsView()
    private CandidateDAO candidateDAO = new CandidateDAO()
    private Scanner scanner = new Scanner(System.in)

    CandidateManager(PrintOperationsView printView, CandidateDAO candidateDAO) {
        this.printView = printView
        this.candidateDAO = candidateDAO
    }

    void createCandidate() {
        Candidate newCandidate = printView.createCandidate(scanner)
        candidateDAO.create(newCandidate)
    }

     void displayCandidates() {
        List<Candidate> candidates = candidateDAO.getAll()

        if (candidates) {
            println("Lista de candidatos:")
            for (Candidate candidate : candidates) {
                printView.displayCandidateInfo(candidate)
            }

        } else {
            println("Nenhum candidato encontrado.")
        }
    }

    void updateCandidate(){
        Candidate candidate = printView.updateCandidate(scanner)
        candidateDAO.update(candidate.cpf, candidate)
    }

    void deleteCandidate(){
        String cpf = printView.deleteCandidate(scanner)
        candidateDAO.delete(cpf)
    }
}
