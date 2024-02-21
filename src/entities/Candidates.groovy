package entities

class Candidates extends Users implements Serializable{

    Long cpf
    Integer age
    String personalDescription

    Candidates(String name, String email, String state, Long cep, Long cpf, Integer age, String personalDescription) {
        super(name, email, state, cep)
        this.cpf = cpf
        this.age = age
        this.personalDescription = personalDescription
    }

    Long getCpf() {
        return cpf
    }

    void setCpf(Long cpf) {
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
        return "Candidates{" +
                "cpf=" + cpf +
                ", age=" + age +
                ", personalDescription='" + personalDescription + '\'' +
                '}';
    }
}
