package org.jean.linketinder.Controller

import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Manager.CandidateManager

class CandidateController {

    private final CandidateManager candidateManager

    CandidateController() {
        this.candidateManager = new CandidateManager()
    }

    void insertCandidate() throws CandidateControllerException{
        try {
            candidateManager.create()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    void getCandidate() throws CandidateControllerException{
        try {
            candidateManager.get()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    void updateCandidate() throws CandidateControllerException{
        try {
            candidateManager.update()
        }catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    void deleteCandidate() throws CandidateControllerException{
        try {
            candidateManager.delete()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}

