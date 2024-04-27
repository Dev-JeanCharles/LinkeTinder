package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Interfaces.Repository.CandidateRepository
import org.jean.linketinder.Interfaces.Repository.SkillRepository
import org.jean.linketinder.Queries.CandidateQueries

import java.sql.SQLException

class CandidateDAO implements CandidateRepository, SkillRepository{

    private final CandidateQueries candidateQueries
    private final HandleException exception
    private final Sql sql

    CandidateDAO(DBConnection dbConnection, HandleException exception, CandidateQueries candidateQueries) {
        this.exception = exception
        this.candidateQueries = candidateQueries
        this.sql = dbConnection.connect() ? Sql.newInstance(dbConnection.connect()) : null
    }

    @Override
    void create(Candidate candidate) {
        try {
            insertCandidate(candidate)
            insertCandidateSkills(candidate)
            println("Candidato adicionado com sucesso!")

        } catch (Exception e) {
            exception.handleException("Erro ao adicionar candidato", e)
        }
    }

    private void insertCandidate(Candidate candidate) {
        try {
            sql.execute(candidateQueries.INSERT_CANDIDATE_QUERY, [
                    candidate.name,
                    candidate.email,
                    candidate.cpf,
                    candidate.age,
                    candidate.state,
                    candidate.cep,
                    candidate.description
            ])
        } catch (SQLException e) {
            exception.handleException("Erro ao inserir candidato", e)
        }
    }

    private void insertCandidateSkills(Candidate candidate) {
        try {
            candidate.skills.each { skill ->
                Integer skillId = getOrCreateSkillId(skill)
                Integer candidateId = getCandidateId(candidate.cpf)

                sql.execute(candidateQueries.INSERT_CANDIDATE_SKILL_QUERY, [candidateId, skillId])
            }
        }catch (SQLException e ) {
            exception.handleException("Erro ao inserir a habilidade do candidato", e)
        }

    }

    private Integer getCandidateId(String cpf) {
        try {
            Map<String, Object> idRow = sql.firstRow(candidateQueries.GET_ID_CANDIDATE_QUERY, [cpf])
            return idRow?.id as Integer

        }catch (SQLException e) {
            exception.handleException("Erro ao buscar um candidato pelo id", e)
        }

    }

    @Override
    Integer getOrCreateSkillId(skill) {

        String skillName = (skill instanceof String) ? skill : skill.name

        try {
            Integer skillId = sql.firstRow(candidateQueries.GET_ID_SKILLS_QUERY, [skillName])?.skill_id as Integer

            if (skillId == null) {
                sql.execute(candidateQueries.INSERT_SKILL_QUERY, [skillName])
                skillId = sql.firstRow(candidateQueries.GET_ID_SKILLS_QUERY, [skillName])?.skill_id as Integer
            }
            return skillId

        }catch (SQLException e) {
            exception.handleException("Erro ao buscar ou criar uma nova habilidade", e)
        }
    }

    @Override
    List<Candidate> getAll() {

        List<Candidate> candidates = []

        try {
            List<Map<String, Object>> rows = sql.rows(candidateQueries.GET_ALL_CANDIDATES_QUERY)

            rows.each { row ->
                List<String> skills = getSkillsForCandidate(row.id as Integer)

                Candidate candidate = new Candidate(
                        row.name as String,
                        row.email as String,
                        row.state as String,
                        row.cep as String,
                        row.description as String,
                        skills as List<Skill>,
                        row.id as String,
                        row.cpf as String,
                        row.age as Integer,
                )
                candidates.add(candidate)
            }
            return candidates

        } catch (SQLException e) {
            exception.handleException("Erro ao recuperar os candidatos", e)
            return []
        }
    }

    @Override
    List<String> getSkillsForCandidate(Integer candidateId) {
        try {
            List<Map<String, Object>> skillRows = sql.rows(candidateQueries.GET_SKILLS_FOR_CANDIDATE_QUERY, [candidateId])
            return skillRows.collect(({ it.name } as Closure<String>))

        }catch (SQLException e) {
            exception.handleException("Erro ao buscar as habilidades do candidato", e)
        }
    }

    @Override
    void update(String cpf, Candidate candidate) {
        try {
            updateCandidate(cpf, candidate)
            println("Candidato atualizado com sucesso!")

        } catch (Exception e) {
            exception.handleException("Erro ao atualizar candidato", e)
        }
    }

    private void updateCandidate(String cpf, Candidate candidate) {
        try {
            sql.execute(candidateQueries.UPDATE_CANDIDATE_QUERY, [
                    candidate.name,
                    candidate.email,
                    candidate.cpf,
                    candidate.age,
                    candidate.state,
                    candidate.cep,
                    candidate.description,
                    cpf
            ])

            Integer id = getCandidateId(cpf)

            if (id != null) {
                sql.execute(candidateQueries.DELETE_CANDIDATE_SKILLS_QUERY, [id])
                insertCandidateSkills(candidate)
            } else {
                println "Candidato com CPF $cpf não encontrado."
            }

        }catch (SQLException e) {
            exception.handleException("Erro ao atualizar o candidato", e)
        }
    }

    @Override
    void delete(String cpf) {

        Integer id = getCandidateId(cpf)

        try {
            if (id != null) {

                sql.execute(candidateQueries.DELETE_CANDIDATE_SKILLS_QUERY, [id])

                sql.execute(candidateQueries.DELETE_CANDIDATE_QUERY, [cpf])

                println("Candidato removido com sucesso!")
            } else {
                println "Candidato com CPF $cpf não encontrado."
            }

        } catch (SQLException e) {
            exception.handleException("Erro ao remover candidato", e)
        }
    }
}
