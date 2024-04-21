package org.jean.linketinder.Entities

class Vacancy {

    String name
    String state
    String cnpj
    String description
    List<String> skills

    Vacancy(String name, String state, String cnpj, String description, List<String> skills) {
        this.name = name
        this.state = state
        this.cnpj = cpf
        this.description = description
        this.skills = skills
    }
}
