package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Interfaces.Implementation.CandidateImplementation
import org.jean.linketinder.View.PrintOperationsView

class CandidateService implements CandidateImplementation.CandidateOperationsInterface{
    private PrintOperationsView printView = new PrintOperationsView()
    private CandidateDAO candidateDAO = new CandidateDAO()
    private Scanner scanner = new Scanner(System.in)

    CandidateService(PrintOperationsView printView, CandidateDAO candidateDAO, Scanner scanner) {
        this.printView = printView
        this.candidateDAO = candidateDAO
        this.scanner = scanner
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
