package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Model.Entity.Candidate
import org.jean.linketinder.Interfaces.Implementation.CandidateImplementation
import org.jean.linketinder.View.PrintOperationsView

class CandidateService implements CandidateImplementation.CandidateOperationsInterface{
    private PrintOperationsView printView = new PrintOperationsView()
    private CandidateDAO candidateDAO

    CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    @Override
    void createCandidate(Scanner scanner) {
        Candidate newCandidate = printView.createCandidate(scanner)
        candidateDAO.create(newCandidate)
    }

    @Override
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

    @Override
    void updateCandidate(Scanner scanner){
        Candidate candidate = printView.updateCandidate(scanner)
        candidateDAO.update(candidate.cpf, candidate)
    }

    @Override
    void deleteCandidate(Scanner scanner){
        String cpf = printView.deleteCandidate(scanner)
        candidateDAO.delete(cpf)
    }
}
