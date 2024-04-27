package org.jean.linketinder.Model.Entity

class Candidate extends Users {
    String id
    String cpf
    Integer age

    Candidate(String name, String email, String state, String cep, String description, List<Skill> skills, String id, String cpf, Integer age) {
        super(name, email, state, cep, description, skills as List<String>)
        this.id = id
        this.cpf = cpf
        this.age = age
    }

    Candidate(String name, String email, String cpf, Integer age, String state, String cep, String description, List<Skill> skillNames) {
        super(name, email, state, cep, description, skillNames as List<String>)
        this.cpf = cpf
        this.age = age
    }

}
