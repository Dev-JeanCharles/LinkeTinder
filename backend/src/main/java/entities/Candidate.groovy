package entities

import enums.Skills

class Candidate extends Users{

    String cpf
    Integer age

    Candidate(String name, String email, String cpf, int age, String state, String cep, String description, List<Skills> skillsList) {
        super(name, email, state, cep, description, skillsList)
        this.cpf = cpf
        this.age = age
    }


}
