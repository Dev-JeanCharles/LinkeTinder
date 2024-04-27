package org.jean.linketinder.DAO

import groovy.sql.Sql
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Interfaces.Repository.VacancyRepository
import org.jean.linketinder.Queries.VacancyQueries

import java.sql.PreparedStatement
import java.sql.SQLException

class VacancyDAO implements VacancyRepository {
    private final HandleException exception
    private final Sql sql
    private final VacancyQueries vacancyQueries

    VacancyDAO(DBConnection dbConnection, HandleException exception, VacancyQueries vacancyQueries) {
        this.vacancyQueries = vacancyQueries
        this.exception = exception
        this.sql = new Sql(dbConnection.connect())
    }

    @Override
    List<Vacancy> getAll() {

        List<Vacancy> vacancies = []

        try {
            List<Map<String, Object>> rows = sql.rows(vacancyQueries.GET_ALL_VACANCIES_QUERY)

            rows.each { Map<String, Object> row ->
                Vacancy vacancy = createVacancyFromRow(row, vacancies)
                addSkillToVacancy(row, vacancy)
                addCompanyToVacancy(row, vacancy)
            }

            vacancies.removeIf { vacancy -> vacancy.company == null }

            return vacancies

        } catch (SQLException e) {
            exception.handleException("Erro ao obter todas as vagas", e)
            return []
        }
    }

    private static Vacancy createVacancyFromRow(Map<String, Object> row, List<Vacancy> vacancies) {

        Integer vacancyId = row['vacancy_id'] as Integer

        Vacancy vacancy = vacancies.find { it.id == vacancyId }

        if (!vacancy) {
            vacancy = new Vacancy(
                    vacancyId as Integer,
                    row['name'] as String,
                    row['locality'] as String,
                    row['description'] as String,
                    []
            )
            vacancies.add(vacancy)
        }
        return vacancy
    }

    private static void addSkillToVacancy(Map<String, Object> row, Vacancy vacancy) {
        if (row['skill_name']) {
            Skill skill = new Skill(row['skill_name'] as String)
            vacancy.skills.add(skill)
        }
    }

    private static void addCompanyToVacancy(Map<String, Object> row, Vacancy vacancy) {
        if (row['company_id']) {
            Company company = createCompanyFromRow(row)
            vacancy.setCompany(company)
        }
    }

    private static Company createCompanyFromRow(Map<String, Object> row) {

        return new Company(
                null,
                row['company_name'] as String,
                null,
                null,
                null,
                null,
                null,
                null
        )
    }

    @Override
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

        List<Object> parameters = [vacancy.name, vacancy.locality, vacancy.description]

        try {
            sql.executeInsert(vacancyQueries.INSERT_VACANCY_QUERY, parameters)

            return (sql.firstRow(vacancyQueries.GET_ID_QUERY)?.id as Integer)

        } catch (SQLException e) {
            exception.handleException("Erro ao inserir a vaga", e)
        }
    }

    private void insertVacancySkills(Integer vacancyId, List<Skill> skills) {
        try {
            skills.each { skill ->
                String skillName = skill instanceof Skill ? skill.name : skill

                Integer skillId = getOrCreateSkillId(skillName as String)

                if (skillId) {
                    sql.execute(vacancyQueries.INSERT_VACANCY_SKILL_QUERY, [vacancyId, skillId])
                }
            }

        } catch (SQLException e) {
            exception.handleException("Erro ao adicionar habilidades à vaga", e)
        }
    }

    private void associateVacancyWithCompany(Integer companyId, Integer vacancyId) {
        try {
            if (companyId != null && vacancyId != null) {
                PreparedStatement statement = sql.getConnection().prepareStatement(vacancyQueries.INSERT_VACANCY_COMPANY_QUERY) as PreparedStatement
                statement.setInt(1, companyId)
                statement.setInt(2, vacancyId)
                statement.execute()
                println "Vaga associada à empresa com sucesso."

            } else {
                println "IDs de empresa ou vaga nulos. A vaga não pode ser associada à empresa."
            }

        } catch (SQLException e) {
            exception.handleException("Erro ao associar vaga à empresa", e)
        }
    }

    private Integer getOrCreateSkillId(String skillName) {
        try {
            Integer skillId = sql.firstRow(vacancyQueries.GET_SKILL_ID_QUERY, [skillName])?.skill_id as Integer

            if (skillId == null) {
                sql.execute(vacancyQueries.INSERT_SKILL_QUERY, [skillName])
                skillId = sql.firstRow(vacancyQueries.GET_ID_QUERY)?.id as Integer
            }
            return skillId

        } catch (SQLException e) {
            exception.handleException("Erro ao obter ou criar habilidade", e)
            return null
        }
    }

    @Override
    void update(Integer vacancyId, Vacancy vacancy) {
        try {
            if (vacancyId) {
                updateVacancyBasicInfo(vacancy)

                addNewSkillsToVacancy(vacancyId, vacancy.skills)
            } else {
                println "ID da vaga não fornecido. Não é possível atualizar."
            }

        } catch (Exception e) {
            exception.handleException("Erro ao atualizar a vaga", e)
        }
    }

    private void updateVacancyBasicInfo(Vacancy vacancy) {

        List<Object> parameters = [vacancy.name, vacancy.locality, vacancy.description, vacancy.id]

        try {
            sql.execute(vacancyQueries.UPDATE_VACANCY_INFO_QUERY, parameters)

        } catch (SQLException e) {
            exception.handleException("Erro ao atualizar informações básicas da vaga", e)
        }
    }

    private void addNewSkillsToVacancy(Integer vacancyId, List<Skill> skills) {
        try {
            skills.each { skill ->
                String skillName = skill instanceof Skill ? skill.name : skill
                Integer skillId = getOrCreateSkillId(skillName as String)

                if (skillId) {
                    sql.execute(vacancyQueries.INSERT_VACANCY_SKILL_QUERY, [vacancyId, skillId])
                }
            }

        } catch (SQLException e) {
            exception.handleException("Erro ao adicionar novas habilidades à vaga", e)
        }
    }

    void deleteByCompanyId(Integer companyId) {
        try {
            sql.execute(vacancyQueries.DELETE_COMPANY_ASSOCIATE_QUERY, [companyId])
            println "Vagas associadas à empresa removidas com sucesso."

        } catch (SQLException e) {
            exception.handleException("Erro ao remover vagas associadas à empresa", e)
        }
    }
}
