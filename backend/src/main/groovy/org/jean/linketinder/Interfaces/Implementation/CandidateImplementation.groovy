package org.jean.linketinder.Interfaces.Implementation

import org.jean.linketinder.Exceptions.CandidateControllerException

class CandidateImplementation {

    interface CandidateOperationsInterface {
        void createCandidate()
        void displayCandidates()
        void updateCandidate()
        void deleteCandidate()
    }

    interface CandidateControllerInterface {
        void createCandidate() throws CandidateControllerException
        void getCandidate() throws CandidateControllerException
        void updateCandidate() throws CandidateControllerException
        void deleteCandidate() throws CandidateControllerException
    }

}