package org.jean.linketinder.Queries

class VacancyQueries {

    static String GET_ID_QUERY = "SELECT lastval() as id"
    static String GET_SKILL_ID_QUERY = "SELECT skill_id FROM skills WHERE name = ?"
    static String GET_ALL_VACANCIES_QUERY = """
    SELECT v.vacancy_id, v.name, v.locality, v.description, 
           s.name as skill_name, vc.company_id, c.name as company_name
    FROM vacancies v
    LEFT JOIN vacancy_skills vs ON v.vacancy_id = vs.vacancy_id
    LEFT JOIN skills s ON vs.skill_id = s.skill_id
    LEFT JOIN vacancy_companies vc ON v.vacancy_id = vc.vacancy_id
    LEFT JOIN companies c ON vc.company_id = c.id
    """
    static String INSERT_VACANCY_QUERY = "INSERT INTO vacancies (name, locality, description) VALUES (?, ?, ?)"
    static String INSERT_VACANCY_SKILL_QUERY = "INSERT INTO vacancy_skills (vacancy_id, skill_id) VALUES (?, ?)"
    static String INSERT_SKILL_QUERY = "INSERT INTO skills (name) VALUES (?)"
    static String INSERT_VACANCY_COMPANY_QUERY = "INSERT INTO vacancy_companies (company_id, vacancy_id) VALUES (?, ?)"
    static String UPDATE_VACANCY_INFO_QUERY = "UPDATE vacancies SET name=?, locality=?, description=? WHERE vacancy_id=?"
    static String DELETE_COMPANY_ASSOCIATE_QUERY = "DELETE FROM vacancy_companies WHERE company_id = ?"
    static String DELETE_VACANCY_SKILLS_QUERY = "DELETE FROM vacancy_skills WHERE vacancy_id = ?"

}
