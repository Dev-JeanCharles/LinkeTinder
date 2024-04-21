package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException

class VacancyDAO {
    private static final INSERT_QUERY = "INSERT INTO vacancies (name, locality, description) VALUES (?, ?, ?)"
    private static final SELECT_ALL_QUERY = "SELECT * FROM vacancies"
    private static final UPDATE_QUERY = "UPDATE vacancies SET name = ?, locality = ?, description = ? WHERE vacancy_id = ?"

    private static HandleException exception

    Sql sql = Sql.newInstance(DBConection.conect())

    void create(Vacancy vacancy) {
        try {
            insertVacancy(candidate)
            insertVacancySkills(vacancy)
            println("Vaga adicionada com sucesso!")
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar a vaga", e)
        }
    }

    private void insertVacancy(Candidate candidate) {
        sql.execute(INSERT_CANDIDATE_QUERY, [
                candidate.name,
                candidate.email,
                candidate.cpf,
                candidate.age,
                candidate.state,
                candidate.cep,
                candidate.description
        ])
    }
}
