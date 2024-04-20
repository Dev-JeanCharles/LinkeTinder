package org.jean.linketinder.Entities


class Company extends Users{
    String cnpj, country

    Company(String name, String email, String cnpj, String country, String state, String cep, String description, List<Skill> skillsList) {
        super(name, email, state, cep, description, skillsList as List<String>)
        this.cnpj = cnpj
        this.country = country
    }
}

