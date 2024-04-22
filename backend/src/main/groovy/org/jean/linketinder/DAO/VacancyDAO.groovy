package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException

class VacancyDAO {
    private static final INSERT_VACANCY_QUERY = "INSERT INTO vacancies (name, locality, description) VALUES (?, ?, ?)"
    private static final INSERT_VACANCY_SKILL_QUERY = "INSERT INTO vacancy_skills (vacancy_id, skill_id) VALUES (?, ?)"
    private static final INSERT_SKILL_QUERY = "INSERT INTO skills (name) VALUES (?)"
    private static final INSERT_VACANCY_COMPANY_QUERY = "INSERT INTO vacancy_companies (company_id, vacancy_id) VALUES (?, ?)"

    private HandleException exception = new HandleException()
    private Sql sql = Sql.newInstance(DBConection.conect())

    void create(Vacancy vacancy, Company company) {
        try {
            println "Criando nova vaga: ${vacancy.name}"

            Integer vacancyId = insertVacancy(vacancy)
            if (vacancyId != null) {
                insertVacancySkills(vacancyId, vacancy.skills)

                println "Associando vaga à empresa..."
                if (company.id != null) {
                    insertVacancyCompany(company.id, vacancyId)
                    println "Vaga associada à empresa com sucesso."
                } else {
                    println "A empresa não possui um ID válido. A vaga não pode ser associada."
                }
            } else {
                println "Erro ao criar a vaga. O ID retornado é nulo."
            }
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar a vaga", e)
        }
    }


    private Integer insertVacancy(Vacancy vacancy) {
        try {
            def parameters = [vacancy.name, vacancy.locality, vacancy.description]
            sql.executeInsert(INSERT_VACANCY_QUERY, parameters)

            Integer vacancyId = sql.firstRow("SELECT lastval() as id")?.id as Integer

            if (vacancyId != null) {
                return vacancyId
            } else {
                throw new Exception("Erro ao inserir a vaga. O ID retornado é nulo.")
            }
        } catch (Exception e) {
            exception.handleException("Erro ao inserir a vaga", e)
        }
    }

    private void insertVacancySkills(Integer vacancyId, List<Skill> skills) {
        try {
            println "Iniciando inserção de habilidades..."
            skills.each { skill ->
                Integer skillId = null
                if (skill instanceof Skill && skill.name) {
                    skillId = getOrCreateSkillId(skill.name)
                } else if (skill instanceof String) {
                    skillId = getOrCreateSkillId(skill)
                }
                if (skillId != null) {
                    sql.execute(INSERT_VACANCY_SKILL_QUERY, [vacancyId, skillId] as Object[])
                }
            }
        } catch (Exception e) {
            exception.handleException("Erro ao adicionar habilidades à vaga", e)
        }
    }

    private void insertVacancyCompany(Integer companyId, Integer vacancyId) {
        try {
            if (companyId != null && vacancyId != null) {
                println "CompanyId: $companyId, VacancyId: $vacancyId"
                def statement = sql.getConnection().prepareStatement(INSERT_VACANCY_COMPANY_QUERY)
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
        Integer skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id as Integer
        if (skillId == null) {
            sql.execute(INSERT_SKILL_QUERY, [skillName])
            skillId = sql.firstRow("SELECT skill_id FROM skills WHERE name = ?", [skillName])?.skill_id as Integer
        }
        return skillId
    }
}
