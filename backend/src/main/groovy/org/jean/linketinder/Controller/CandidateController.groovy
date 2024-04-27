package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Interfaces.Implementation.CandidateImplementation
import org.jean.linketinder.Service.CandidateService

class CandidateController implements CandidateImplementation.CandidateControllerInterface{

    private final CandidateService candidateService
    private final Scanner scanner = new Scanner(System.in)

    CandidateController(CandidateDAO candidateDAO) {
        this.candidateService = new CandidateService(candidateDAO)
    }

    @Override
    void createCandidate() throws CandidateControllerException{
        try {
            candidateService.createCandidate(scanner)

        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    @Override
    void getCandidate() throws CandidateControllerException {
        try {
            candidateService.displayCandidates()

        } catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    @Override
    void updateCandidate() throws CandidateControllerException{
        try {
            candidateService.updateCandidate(scanner)

        }catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    @Override
    void deleteCandidate() throws CandidateControllerException{
        try {
            candidateService.deleteCandidate(scanner)

        }catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}

