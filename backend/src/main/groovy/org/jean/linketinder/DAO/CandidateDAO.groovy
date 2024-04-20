package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate

class CandidateDAO {
    Sql sql = Sql.newInstance(DBConection.conect()) as Sql

    void create(Candidate candidate) {
        try {
            sql.execute("INSERT INTO candidates (name, email, cpf, age, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    [candidate.name, candidate.email, candidate.cpf, candidate.age, candidate.state, candidate.cep, candidate.description])

            candidate.skillsList.each { skill ->
                sql.execute("INSERT INTO skills (name) VALUES (?)", [skill])
            }

            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [candidate.cpf]).id

            candidate.skillsList.each { skill ->
                sql.execute("INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, (SELECT skill_id FROM skills WHERE name = ? LIMIT 1))",
                        [id, skill])
            }

            println("Candidato adicionado com sucesso!")

        }catch (Exception e) {
            println("Erro ao adicionar candidate: ${e.message}")
        }
    }
    List<Candidate> getAll() {
        List<Candidate> candidates = []

        sql.eachRow("SELECT id, name, email, cpf, age, state, cep, description FROM candidates") { row ->
            def skillsForCandidate = getSkillsForCandidate(row.id.toLong()) ?: []
            Candidate candidate = new Candidate(
                    row.name,
                    row.email,
                    row.cpf,
                    row.age as Integer,
                    row.state,
                    row.cep,
                    row.description,
                    skillsForCandidate as List<Skills>
            )
            candidates.add(candidate)
        }
        return candidates
    }

    private List<Skills> getSkillsForCandidate(Long candidateId) {
        List<Skills> skillsForCandidate = []

        sql.eachRow("SELECT name FROM skills " +
                "INNER JOIN candidate_skills ON skills.skill_id = candidate_skills.skill_id " +
                "WHERE candidate_skills.candidate_id = ?", [candidateId]) { row ->
            skillsForCandidate.add(Skills.valueOf(row.name))
        }

        return skillsForCandidate
    }

    void update(String cpf, Candidate candidate) {
        try {
            sql.execute("UPDATE candidates SET name = ?, email = ?, cpf = ?, age = ?, state = ?, cep = ?, description = ? WHERE cpf = ?", [
                    candidate.name,
                    candidate.email,
                    candidate.cpf,
                    candidate.age,
                    candidate.state,
                    candidate.cep,
                    candidate.description,
                    cpf
            ])

            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [cpf]).id
            sql.execute("DELETE FROM candidate_skills WHERE candidate_id = ?", [id])

            candidate.skillsList.each { skill ->
                def skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skill])?.skill_id
                if (skillId == null) {
                    sql.execute("INSERT INTO skills (name) VALUES (?)", [skill])
                    skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skill]).skill_id
                }
                sql.execute("INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, ?)", [id, skillId])
            }

            println("Candidato e suas competências atualizados com sucesso!")

        } catch (Exception e) {
            println("Erro ao atualizar candidato: ${e.message}")
        }
    }
    void delete(String cpf) {
        try {
            def idRow = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [cpf])
            if (idRow) {
                def id = idRow.id

                sql.execute("DELETE FROM candidate_skills WHERE candidate_id = ?", [id])
                sql.execute("DELETE FROM candidates WHERE cpf = ?", [cpf])

                println("Candidato removido com sucesso!")
            } else {
                println "Candidato com CPF $cpf não encontrado."
            }
        } catch (Exception e) {
            println("Erro ao remover candidato: ${e.message}")
        }
    }
}