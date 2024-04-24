package org.jean.linketinder.Interfaces.Implementation

import org.jean.linketinder.Interfaces.Repository.DBCandidateRepository
import org.jean.linketinder.Interfaces.Repository.SkillRepository

class SkillRepositoryImplementation implements SkillRepository{

    private final DBCandidateRepository dbCandidateRepository

    SkillRepositoryImplementation(DBCandidateRepository dbCandidateRepository) {
        this.dbCandidateRepository = dbCandidateRepository
    }

    @Override
    Integer getOrCreateSkillId(Object skill) {
        return null
    }

    @Override
    List<String> getSkillsForCandidate(Integer candidateId) {
        return null
    }
}