package org.jean.linketinder.Interfaces.Implementation

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Interfaces.Repository.DBCandidateRepository

class DBCandidateImplementation implements DBCandidateRepository{
    private final Sql sql

    DBCandidateImplementation(DBConnection dbConnection) {
        this.sql = Sql.newInstance(dbConnection.connection)
    }

    @Override
    void insertCandidate(Candidate candidate) {

    }

    @Override
    void insertCandidateSkills(String cpf, List<Skill> skills) {

    }
}
