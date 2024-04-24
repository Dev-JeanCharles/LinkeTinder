package org.jean.linketinder.Interfaces.Implementation

import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Interfaces.Repository.CandidateRepository
import org.jean.linketinder.Interfaces.Repository.DBCandidateRepository

class CandidateRepositoryImplementation implements CandidateRepository{
    private final DBCandidateRepository dbCandidateRepository

    CandidateRepositoryImplementation(DBCandidateRepository dbCandidateRepository) {
        this.dbCandidateRepository = dbCandidateRepository
    }

    @Override
    void create(Candidate candidate) {
    dbCandidateRepository.insertCandidate(candidate)
    }

    @Override
    List<Candidate> getAll() {
        return null
    }

    @Override
    void update(String cpf, Candidate candidate) {

    }

    @Override
    void delete(String cpf) {

    }
}
