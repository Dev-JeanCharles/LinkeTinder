package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException

class VacancyDAO {
    private static final INSERT_VACANCY_QUERY = "INSERT INTO vacancies (name, locality, description) VALUES (?, ?, ?)"
    private static final INSERT_VACANCY_SKILL_QUERY = "INSERT INTO vacancy_skills (vacancy_id, skill_id) VALUES (?, ?)"
    private static final INSERT_SKILL_QUERY = "INSERT INTO skills (name) VALUES (?)"
    private static final SELECT_ALL_CANDIDATES_QUERY = "SELECT * FROM vacancies"
    private static final UPDATE_CANDIDATE_QUERY = "UPDATE vacancies SET name = ?, locality = ?, description = ? WHERE vacancy_id = ?"

    private static HandleException exception

    Sql sql = Sql.newInstance(DBConection.conect())

    void create(Vacancy vacancy) {
        try {
            insertVacancy(vacancy)
            insertVacancySkills(vacancy)
            println("Vaga adicionada com sucesso!")
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar a vaga", e)
        }
    }

    private void insertVacancy(Vacancy vacancy) {
        sql.execute(INSERT_VACANCY_QUERY, [
                vacancy.name,
                vacancy.skills,
                vacancy.locality,
                vacancy.description,
        ])
    }

    private void insertVacancySkills(Vacancy vacancy) {
        vacancy.skills.each { skill ->
            Integer skillId = getOrCreateSkillId(skill)
            sql.execute(INSERT_VACANCY_SKILL_QUERY, [getVacancyId(vacancy.id as Integer), skillId])
        }
    }

    private Integer getVacancyId(Integer id) {
        Map<String, Object> idRow = sql.firstRow("SELECT vacancy_id FROM vacancies WHERE vacancy_id = ?", [id])
        return idRow?.id as Integer
    }

    private Integer getOrCreateSkillId(skill) {
        String skillName = (skill instanceof String) ? skill : skill.name
        Integer skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id as Integer
        if (skillId == null) {
            sql.execute(INSERT_SKILL_QUERY, [skillName])
            skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id as Integer
        }
        return skillId
    }
}
