package org.jean.linketinder.Controller

import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Manager.CandidateManager

class CandidateController {

    private static final CandidateManager candidateManager

    static void createCandidate() throws CandidateControllerException{
        try {
            candidateManager.create()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    static void getCandidate() throws CandidateControllerException{
        try {
            candidateManager.get()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    static void updateCandidate() throws CandidateControllerException{
        try {
            candidateManager.update()
        }catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    static void deleteCandidate() throws CandidateControllerException{
        try {
            candidateManager.delete()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}

