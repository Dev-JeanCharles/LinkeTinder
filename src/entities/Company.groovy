package entities

class Company extends Users implements Serializable{

    Long cnpj
    String country, companyDescription

    Company() {

    }

    Company(String name, String email, String state, String cep, Long cnpj, String country, String companyDescription) {
        super(name, email, state, cep)
        this.cnpj = cnpj
        this.country = country
        this.companyDescription = companyDescription
    }

    Long getCnpj() {
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
        stringBuilder.append("Country: ").append(country).append("\n")
        stringBuilder.append("Company Description: ").append(companyDescription).append("\n")
        return stringBuilder.toString()
    }
}
