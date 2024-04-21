package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Service.CandidateService
import org.jean.linketinder.View.PrintOperationsView

class CandidateController {

    private final CandidateService candidateService

    CandidateController(PrintOperationsView printView, CandidateDAO candidateDAO) {
        this.candidateService = new CandidateService(printView, candidateDAO)
    }

    void createCandidate() throws CandidateControllerException{
        try {
            candidateService.createCandidate()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    void getCandidate() throws CandidateControllerException {
        try {
            candidateService.displayCandidates()
        } catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    void updateCandidate() throws CandidateControllerException{
        try {
            candidateService.updateCandidate()
        }catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    void deleteCandidate() throws CandidateControllerException{
        try {
            candidateService.deleteCandidate()
        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}

