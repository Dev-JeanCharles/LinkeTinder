package org.jean.linketinder.Interfaces.Repository

import org.jean.linketinder.Model.Entity.Candidate

interface CandidateRepository {
    void create(Candidate candidate)
    List<Candidate> getAll()
    void update(String cpf, Candidate candidate)
    void delete(String cpf)
}

interface SkillRepository {
    Integer getOrCreateSkillId(Object skill)
    List<String> getSkillsForCandidate(Integer candidateId)
}