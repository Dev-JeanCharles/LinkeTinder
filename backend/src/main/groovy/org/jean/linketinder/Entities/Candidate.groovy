package org.jean.linketinder.Entities


class Candidate extends Users {

    String cpf
    Integer age

    Candidate(String name, String email, String cpf, Integer age, String state, String cep, String description, List<Skill> skillsList) {
        super(name, email, state, cep, description, skillsList as List<String>)
        this.cpf = cpf
        this.age = age
    }

}

