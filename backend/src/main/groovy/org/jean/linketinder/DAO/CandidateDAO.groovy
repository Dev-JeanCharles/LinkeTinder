package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill

class CandidateDAO {
    Sql sql = Sql.newInstance(DBConection.conect()) as Sql

    void create(Candidate candidate) {
        try {
            sql.execute("INSERT INTO candidates (name, email, cpf, age, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    [candidate.name, candidate.email, candidate.cpf, candidate.age, candidate.state, candidate.cep, candidate.description])

            candidate.skills.each { skill ->
                sql.execute("INSERT INTO skills (name) VALUES (?)", [skill])
            }

            def id = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [candidate.cpf]).id

            candidate.skills.each { skill ->
                sql.execute("INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, (SELECT skill_id FROM skills WHERE name = ? LIMIT 1))",
                        [id, skill])
            }

            println("Candidato adicionado com sucesso!")

        }catch (Exception e) {
            println("Erro ao adicionar candidate: ${e.message}")
        }
    }

    List<Candidate> getAll() {
        try {
            List<Candidate> candidates = []
            List<Map<String, Object>> rows = sql.rows("SELECT * FROM candidates")

            rows.each { row ->
                List<String> skills = sql.rows("SELECT name FROM skills " +
                        "INNER JOIN candidate_skills ON skills.skill_id = candidate_skills.skill_id " +
                        "WHERE candidate_skills.candidate_id = ?", [row.id]).collect(({ it.name } as Closure<String>))

                Candidate candidate = new Candidate(
                        row.name as String,
                        row.email as String,
                        row.state as String,
                        row.cep as String,
                        row.description as String,
                        skills,
                        row.id as String,
                        row.cpf as String,
                        row.age as Integer,
                        []
                )

                candidates.add(candidate)
            }

            return candidates
        } catch (Exception e) {
            e.printStackTrace()
            return []
        }
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

            def idRow = sql.firstRow("SELECT id FROM candidates WHERE cpf = ?", [cpf])
            if (idRow) {
                def id = idRow.id

                sql.execute("DELETE FROM candidate_skills WHERE candidate_id = ?", [id])

                candidate.skills.each { skill ->
                    def skillId = null
                    if (skill instanceof String) {
                        def skillName = skill
                        skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id
                        if (skillId == null) {
                            sql.execute("INSERT INTO skills (name) VALUES (?)", [skillName])
                            skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id
                        }
                    } else if (skill instanceof Skill) {
                        def skillName = skill.name
                        skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id
                        if (skillId == null) {
                            sql.execute("INSERT INTO skills (name) VALUES (?)", [skillName])
                            skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id
                        }
                    }

                    if (skillId != null) {
                        sql.execute("INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, ?)", [id, skillId])
                    }
                }
                println("Candidato atualizado com sucesso!")
            } else {
                println "Candidato com CPF $cpf não encontrado."
            }
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