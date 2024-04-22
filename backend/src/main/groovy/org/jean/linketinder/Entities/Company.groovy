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
    // Construtor para criar uma instância de Company apenas com o nome
    Company(String name) {
        super(name, null, null, null, null, null)
    }
}
