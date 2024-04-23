package org.jean.linketinder.Entities

class Candidate extends Users {
    String id
    String cpf
    Integer age
    List<Integer> skillIds

    Candidate(String name, String email, String state, String cep, String description, List<Skill> skills, String id, String cpf, Integer age, List<Integer> skillIds) {
        super(name, email, state, cep, description, skills as List<String>)
        this.id = id
        this.cpf = cpf
        this.age = age
        this.skillIds = skillIds
    }

    Candidate(String name, String email, String cpf, Integer age, String state, String cep, String description, List<Skill> skillNames) {
        super(name, email, state, cep, description, skillNames)
        this.cpf = cpf
        this.age = age
    }

}
