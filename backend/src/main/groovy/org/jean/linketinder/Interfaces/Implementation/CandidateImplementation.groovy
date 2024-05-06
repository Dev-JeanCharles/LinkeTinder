package org.jean.linketinder.Interfaces.Implementation

import org.jean.linketinder.Exceptions.CandidateControllerException

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CandidateImplementation {

    interface CandidateOperationsInterface {
        void createCandidate(Scanner scanner)
        void displayCandidates()
        void updateCandidate(Scanner scanner)
        void deleteCandidate(Scanner scanner)

    }

    interface CandidateControllerInterface {
        void createCandidate() throws CandidateControllerException
        void getCandidate() throws CandidateControllerException
        void updateCandidate() throws CandidateControllerException
        void deleteCandidate() throws CandidateControllerException
    }

}