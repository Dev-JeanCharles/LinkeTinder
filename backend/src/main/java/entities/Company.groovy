package entities

import enums.Skills

class Company extends Users{
    String cnpj, country
    List<Skills> skillsList = []

    Company(String name, String email, String cnpj, String country, String state, String cep, String description, List<Skills> skillsList) {
        super(name, email, state, cep, description, skillsList)
        this.cnpj = cnpj
        this.country = country
    }
}
