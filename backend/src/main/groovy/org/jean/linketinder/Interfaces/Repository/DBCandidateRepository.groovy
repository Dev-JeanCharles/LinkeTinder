package org.jean.linketinder.Interfaces.Repository

import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill

interface DBCandidateRepository {
    void insertCandidate(Candidate candidate);
    void insertCandidateSkills(String cpf, List<Skill> skills);
}