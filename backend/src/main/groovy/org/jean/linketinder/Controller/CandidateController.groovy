package org.jean.linketinder.Controller


import org.jean.linketinder.Service.CandidateService

class CandidateController {
    private final CandidateService candidateService

    CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService
    }

    void createCandidate() {
        candidateService.createCandidate()
    }

    void getCandidate() {
        candidateService.displayCandidates()
    }

    void updateCandidate() {
        candidateService.updateCandidate()
    }

    void deleteCandidate() {
        candidateService.deleteCandidate()
    }
}

