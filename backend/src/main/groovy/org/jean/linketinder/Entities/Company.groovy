package org.jean.linketinder.Entities

class Company extends Users {
    Integer id
    String cnpj, country

    Company(Integer id, String name, String email, String cnpj, String country, String state, String cep, String description) {
        super(name, email, state, cep, description, null)
        this.id = id
        this.cnpj = cnpj
        this.country = country
    }

    Company(Map<String, Object> attributes) {
        super(
                attributes.name as String,
                attributes.email as String,
                attributes.state as String,
                attributes.cep as String,
                attributes.description as String,
                null
        )
        this.id = attributes.id as Integer
        this.cnpj = attributes.cnpj as String
        this.country = attributes.country as String
    }
}
