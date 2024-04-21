package org.jean.linketinder.Entities

class Vacancy {

    String name
    String state
    String cpf
    String description
    List<String> skills

    Vacancy(String name, String state, String cpf, String description, List<String> skills) {
        this.name = name
        this.state = state
        this.cpf = cpf
        this.description = description
        this.skills = skills
    }
}
