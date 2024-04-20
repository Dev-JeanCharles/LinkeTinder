package org.jean.linketinder

import groovy.sql.Sql

import org.jean.linketinder.DAO.DBConection
import org.jean.linketinder.DAO.DBOperations
import org.jean.linketinder.Menu.Menu

conectDTO = new DBConection()
Instance = new Sql(conectDTO.conect())
operationsDTO = new DBOperations(Instance)

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

List<String> candidateSkills = [
        "candidate_id INTEGER REFERENCES candidates(id)",
        "skill_id INTEGER REFERENCES skills(skill_id)"
]

List<String> fieldSkills = [
        "skill_id SERIAL PRIMARY KEY",
        "name VARCHAR(100)"
]

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

List<String> companyVacancies = [
        "company_id INTEGER REFERENCES companies(id)",
        "vacancy_id INTEGER REFERENCES vacancies(vacancy_id)"
]

List<String> fieldVacancies = [
        "vacancy_id SERIAL PRIMARY KEY",
        "name VARCHAR(100)",
        "locality VARCHAR(100)",
        "description TEXT"
]

operationsDTO.createTable("candidates", fieldCandidates)
operationsDTO.createTable("skills", fieldSkills)
operationsDTO.createTable("candidate_skills", candidateSkills)
operationsDTO.createTable("companies", fieldCompanies)
operationsDTO.createTable("vacancies", fieldVacancies)
operationsDTO.createTable("vacancy_companies", companyVacancies)

new Menu().menuHome()

