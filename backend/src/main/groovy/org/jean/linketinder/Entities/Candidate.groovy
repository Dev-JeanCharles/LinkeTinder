package org.jean.linketinder.Entities

class Candidate extends Users {
    String id
    String cpf
    Integer age
    List<Integer> skillIds

    Candidate(String name, String email, String state, String cep, String description, List<String> skills, String id, String cpf, Integer age, List<Integer> skillIds) {
        super(name, email, state, cep, description, skills)
        this.id = id
        this.cpf = cpf
        this.age = age
        this.skillIds = skillIds
    }

    Candidate(String name, String email, String state, String cep, String description, List<String> skills) {
        super(name, email, state, cep, description, skills)
    }
}
