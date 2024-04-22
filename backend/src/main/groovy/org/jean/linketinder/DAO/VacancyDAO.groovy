package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException

import java.sql.PreparedStatement

class VacancyDAO {
    private static final String INSERT_VACANCY_QUERY = "INSERT INTO vacancies (name, locality, description) VALUES (?, ?, ?)"
    private static final String INSERT_VACANCY_SKILL_QUERY = "INSERT INTO vacancy_skills (vacancy_id, skill_id) VALUES (?, ?)"
    private static final String INSERT_SKILL_QUERY = "INSERT INTO skills (name) VALUES (?)"
    private static final String INSERT_VACANCY_COMPANY_QUERY = "INSERT INTO vacancy_companies (company_id, vacancy_id) VALUES (?, ?)"
    private static final String SELECT_ALL_VACANCIES_QUERY = "SELECT * FROM vacancies INNER JOIN vacancy_companies ON vacancies.id = vacancy_companies.vacancy_id INNER JOIN companies ON vacancy_companies.company_id = companies.id"

    private HandleException exception = new HandleException()
    private Sql sql = Sql.newInstance(DBConection.conect())


    List<Vacancy> getAll() {
        try {
            List<Vacancy> vacancies = []
            def rows = sql.rows(SELECT_ALL_VACANCIES_QUERY)
            rows.each { row ->
                Vacancy vacancy = new Vacancy(
                        row.id as Integer,
                        row.name as String,
                        row.locality as String,
                        row.description as String,
                        []
                )
                vacancies.add(vacancy)
            }
            return vacancies
        } catch (Exception e) {
            exception.handleException("Erro ao obter todas as vagas", e)
            return []
        }
    }

    void create(Vacancy vacancy, Company company) {
        try {
            println "Criando nova vaga: ${vacancy.name}"

            Integer vacancyId = insertVacancy(vacancy)
            if (vacancyId != null) {
                insertVacancySkills(vacancyId, vacancy.skills)
                associateVacancyWithCompany(company.id, vacancyId)
            } else {
                println "Erro ao criar a vaga. O ID retornado é nulo."
            }
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar a vaga", e)
        }
    }

    private Integer insertVacancy(Vacancy vacancy) {
        try {
            List<Object> parameters = [vacancy.name, vacancy.locality, vacancy.description]
            sql.executeInsert(INSERT_VACANCY_QUERY, parameters)

            return (sql.firstRow("SELECT lastval() as id")?.id as Integer)
        } catch (Exception e) {
            exception.handleException("Erro ao inserir a vaga", e)
            return null
        }
    }

    private void insertVacancySkills(Integer vacancyId, List<Skill> skills) {
        try {
            skills.each { skill ->
                String skillName = skill instanceof Skill ? skill.name : skill
                Integer skillId = getOrCreateSkillId(skillName as String)
                if (skillId) {
                    sql.execute(INSERT_VACANCY_SKILL_QUERY, [vacancyId, skillId])
                }
            }
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar habilidades à vaga", e)
        }
    }

    private void associateVacancyWithCompany(Integer companyId, Integer vacancyId) {
        try {
            if (companyId != null && vacancyId != null) {
                PreparedStatement statement = sql.getConnection().prepareStatement(INSERT_VACANCY_COMPANY_QUERY) as PreparedStatement
                statement.setInt(1, companyId)
                statement.setInt(2, vacancyId)
                statement.execute()
                println "Vaga associada à empresa com sucesso."
            } else {
                println "IDs de empresa ou vaga nulos. A vaga não pode ser associada à empresa."
            }
        } catch (Exception e) {
            exception.handleException("Erro ao associar vaga à empresa", e)
        }
    }

    private Integer getOrCreateSkillId(String skillName) {
        try {
            Integer skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id as Integer
            if (skillId == null) {
                sql.execute(INSERT_SKILL_QUERY, [skillName])
                skillId = sql.firstRow("SELECT lastval() as id")?.id as Integer
            }
            return skillId
        } catch (Exception e) {
            exception.handleException("Erro ao obter ou criar habilidade", e)
            return null
        }
    }
}
