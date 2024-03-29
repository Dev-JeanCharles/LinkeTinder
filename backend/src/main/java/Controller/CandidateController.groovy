package Controller

import Manager.CandidateManager

class CandidateController {

    static void insertCandidate() {
    new CandidateManager().create()
    }

    static void getCandidate() {
        new CandidateManager().get()
    }

    static void updateCandidate() {
        new CandidateManager().update()
    }

    static void deleteCandidate() {
        new CandidateManager().delete()
    }

}


