package org.jean.linketinder.Model.Entity

class Users {
    String name, email, state, cep, description
    List<String> skills

    Users() {}

    Users(String name, String email, String state, String cep, String description, List<String> skills) {
        this.name = name
        this.email = email
        this.state = state
        this.cep = cep
        this.description = description
        this.skills = skills
    }
}
