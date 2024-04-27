package org.jean.linketinder.Queries

class CandidateQueries {
    static String GET_ID_CANDIDATE_QUERY  = "SELECT id FROM candidates WHERE cpf = ?"
    static String GET_ID_SKILLS_QUERY = "SELECT skill_id FROM skills WHERE name = ?"
    static String GET_ALL_CANDIDATES_QUERY = "SELECT * FROM candidates"
    static String GET_SKILLS_FOR_CANDIDATE_QUERY = "SELECT name FROM skills " + "INNER JOIN candidate_skills ON skills.skill_id = candidate_skills.skill_id " + "WHERE candidate_skills.candidate_id = ?"
    static String INSERT_CANDIDATE_QUERY = "INSERT INTO candidates (name, email, cpf, age, state, cep, description) VALUES (?, ?, ?, ?, ?, ?, ?)"
    static String INSERT_SKILL_QUERY = "INSERT INTO skills (name) VALUES (?)"
    static String INSERT_CANDIDATE_SKILL_QUERY = "INSERT INTO candidate_skills (candidate_id, skill_id) VALUES (?, ?)"
    static String UPDATE_CANDIDATE_QUERY = "UPDATE candidates SET name = ?, email = ?, cpf = ?, age = ?, state = ?, cep = ?, description = ? WHERE cpf = ?"
    static String DELETE_CANDIDATE_SKILLS_QUERY = "DELETE FROM candidate_skills WHERE candidate_id = ?"
    static String DELETE_CANDIDATE_QUERY = "DELETE FROM candidates WHERE cpf = ?"
}
