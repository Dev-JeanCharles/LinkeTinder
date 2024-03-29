package entities

import enums.Skills

class Users {

    String name, email, state, cep, description
    List<Skills> skillsList = []


    Users(String name, String email, String state, String cep, String description, List<Skills> skillsList) {
        this.name = name
        this.email = email
        this.state = state
        this.cep = cep
        this.description = description
        this.skillsList = skillsList

    }
}
