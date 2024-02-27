package entities

import enums.Skills

class Candidates extends Users implements Serializable{

    String cpf
    Integer age
    String personalDescription
    List<Candidates> candidatesList = []
    List<Skills> skillsList = []

    Candidates() {}

    Candidates(String name, String email, String state, String cep, String cpf, Integer age, String personalDescription) {
        super(name, email, state, cep)
        this.cpf = cpf
        this.age = age
        this.personalDescription = personalDescription
    }

    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        this.cpf = cpf
    }

    Integer getAge() {
        return age
    }

    void setAge(Integer age) {
        this.age = age
    }

    String getPersonalDescription() {
        return personalDescription
    }

    void setPersonalDescription(String personalDescription) {
        this.personalDescription = personalDescription
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append("Nome: ").append(name).append("\n")
        stringBuilder.append("E-mail: ").append(email).append("\n")
        stringBuilder.append("Estado: ").append(state).append("\n")
        stringBuilder.append("CEP: ").append(cep).append("\n")
        stringBuilder.append("CPF: ").append(cpf).append("\n")
        stringBuilder.append("Idade: ").append(age).append("\n")
        stringBuilder.append("Descrição Pessoal: ").append(personalDescription).append("\n")
        stringBuilder.append("Habilidades: ").append(skillsList).append("\n")
        return stringBuilder.toString()
    }
}
