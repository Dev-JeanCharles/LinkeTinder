package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Enum.Skills

class CandidateDAO {
    Sql sql = Sql.newInstance(DBConection.conect()) as Sql

    void Create(Candidate candidate) {
        try {
            sql.execute("INSERT INTO candidates (name, email, cpf, age, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    [candidate.name, candidate.email, candidate.cpf, candidate.age, candidate.state, candidate.cep, candidate.description])

            candidate.skillsList.each { skill ->
                sql.execute("INSERT INTO skills (name) VALUES (?)", [skill])
            }

            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [candidate.cpf]).id

            candidate.skillsList.each { skill ->
                sql.execute("INSERT INTO candidate_companies (candidate_id, skill_id) VALUES (?, (SELECT skill_id FROM skills WHERE name = ? LIMIT 1))",
                        [id, skill])
            }

            println("Candidato adicionado com sucesso!")

        }catch (Exception e) {
            println("Erro ao adicionar candidate: ${e.message}")
        }
    }
    List<Candidate> Get() {
        List<Candidate> candidates = sql.rows("SELECT * FROM candidates") as List<Candidate>

        candidates.each { candidate ->

            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [candidate.cpf]).id

            candidate.skillsList = sql.rows("SELECT name FROM skills " +
                    "INNER JOIN candidate_companies ON skills.skill_id = candidate_companies.skill_id " +
                    "WHERE candidate_companies.candidate_id = ?", [id]).collect(({ it.name } as Closure<Skills>))
        }

        return candidates
    }

    void Update(String cpf, Candidate candidate) {
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
            sql.execute("DELETE FROM candidate_companies WHERE candidate_id = ?", [id])

            candidate.skillsList.each { skill ->
                def skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skill])?.skill_id
                if (skillId == null) {
                    sql.execute("INSERT INTO skills (name) VALUES (?)", [skill])
                    skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skill]).skill_id
                }
                sql.execute("INSERT INTO candidate_companies (candidate_id, skill_id) VALUES (?, ?)", [id, skillId])
            }

            println("Candidato e suas competências atualizados com sucesso!")

        } catch (Exception e) {
            println("Erro ao atualizar candidato: ${e.message}")
        }
    }
    void Delete(String cpf) {
        try {
            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [cpf]).id

            sql.execute("DELETE FROM candidate_companies WHERE candidate_id = ?", [id])

            sql.execute("DELETE FROM candidates WHERE cpf = ?", [cpf])

            println("Candidato removido com sucesso!")

        } catch (Exception e) {
            println("Erro ao remover candidato: ${e.message}")
        }
    }
}






