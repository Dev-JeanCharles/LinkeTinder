package entities

import enums.Skills

class Company extends Users implements Serializable{
    String cnpj, country, companyDescription
    List<Company> companyList = []
    List<Skills> skillsList = []

    Company() {

    }

    Company(String name, String email, String state, String cep, String cnpj, String country, String companyDescription) {
        super(name, email, state, cep)
        this.cnpj = cnpj
        this.country = country
        this.companyDescription = companyDescription
    }

    String getCnpj() {
        return cnpj
    }

    void setCnpj(Long cnpj) {
        this.cnpj = cnpj
    }

    String getCountry() {
        return country
    }

    void setCountry(String country) {
        this.country = country
    }

    String getCompanyDescription() {
        return companyDescription
    }

    void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append("Nome: ").append(name).append("\n")
        stringBuilder.append("E-mail: ").append(email).append("\n")
        stringBuilder.append("Estado: ").append(state).append("\n")
        stringBuilder.append("CEP: ").append(cep).append("\n")
        stringBuilder.append("CNPJ: ").append(cnpj).append("\n")
        stringBuilder.append("Country: ").append(country).append("\n")
        stringBuilder.append("Company Description: ").append(companyDescription).append("\n")
        stringBuilder.append("O que esperamos que os candidatos tenham como habilidade: ").append(skillsList).append("\n")
        return stringBuilder.toString()
    }
}
