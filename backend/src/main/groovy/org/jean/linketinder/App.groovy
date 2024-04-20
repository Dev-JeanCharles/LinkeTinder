package org.jean.linketinder

import groovy.sql.Sql
import org.jean.linketinder.DAO.DBConection
import org.jean.linketinder.DAO.DBOperations
import org.jean.linketinder.Menu.Menu

class App {
    static void main(String[] args) {
        createDatabaseTables()
        startMenu()
    }

    static void createDatabaseTables() {
        DBConection conectDTO = new DBConection()
        Sql instance = new Sql(conectDTO.conect())
        DBOperations operationsDTO = new DBOperations(instance)

        createTableCandidates(operationsDTO)
        createTableSkills(operationsDTO)
        createTableCandidateSkills(operationsDTO)
        createTableCompanies(operationsDTO)
        createTableVacancies(operationsDTO)
        createTableVacancyCompanies(operationsDTO)
    }

    static void createTableCandidates(DBOperations operationsDTO) {
        List<String> fieldCandidates = [
                "id SERIAL PRIMARY KEY",
                "name VARCHAR(100)",
                "email VARCHAR(255)",
                "cpf VARCHAR(15) UNIQUE",
                "age INTEGER",
                "state VARCHAR(100)",
                "cep VARCHAR(15)",
                "description TEXT"
        ]
        operationsDTO.createTable("candidates", fieldCandidates)
    }

    static void createTableSkills(DBOperations operationsDTO) {
        List<String> fieldSkills = [
                "skill_id SERIAL PRIMARY KEY",
                "name VARCHAR(100)"
        ]
        operationsDTO.createTable("skills", fieldSkills)
    }

    static void createTableCandidateSkills(DBOperations operationsDTO) {
        List<String> candidateSkills = [
                "candidate_id INTEGER REFERENCES candidates(id)",
                "skill_id INTEGER REFERENCES skills(skill_id)"
        ]
        operationsDTO.createTable("candidate_skills", candidateSkills)
    }

    static void createTableCompanies(DBOperations operationsDTO) {
        List<String> fieldCompanies = [
                "id SERIAL PRIMARY KEY",
                "name VARCHAR(100)",
                "cnpj VARCHAR(50) UNIQUE",
                "email VARCHAR(255)",
                "state VARCHAR(100)",
                "country VARCHAR(100)",
                "cep VARCHAR(15)",
                "description TEXT"
        ]
        operationsDTO.createTable("companies", fieldCompanies)
    }

    static void createTableVacancies(DBOperations operationsDTO) {
        List<String> fieldVacancies = [
                "vacancy_id SERIAL PRIMARY KEY",
                "name VARCHAR(100)",
                "locality VARCHAR(100)",
                "description TEXT"
        ]
        operationsDTO.createTable("vacancies", fieldVacancies)
    }

    static void createTableVacancyCompanies(DBOperations operationsDTO) {
        List<String> companyVacancies = [
                "company_id INTEGER REFERENCES companies(id)",
                "vacancy_id INTEGER REFERENCES vacancies(vacancy_id)"
        ]
        operationsDTO.createTable("vacancy_companies", companyVacancies)
    }

    static void startMenu() {
        new Menu().menuHome()
    }
}
