package org.jean.linketinder.Controller

import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Manager.CandidateManager
import org.jean.linketinder.Manager.CompanyManager

class CandidateController {

    private final CandidateManager candidateManager

    CandidateController() {
        this.candidateManager = new CandidateManager()
    }

    void createCandidate() throws CandidateControllerException{
        try {
            candidateManager.createCandidate()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    void getCandidate() throws CandidateControllerException{
        try {
            candidateManager.displayCandidates()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    void updateCandidate() throws CandidateControllerException{
        try {
            candidateManager.updateCandidate()
        }catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    void deleteCandidate() throws CandidateControllerException{
        try {
            candidateManager.deleteCandidate()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}

