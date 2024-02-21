package entities

class Company extends Users implements Serializable{

    Long cnpj
    String country, companyDescription

    Company(String name, String email, String state, Long cep, Long cnpj, String country, String companyDescription) {
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
        return "Company{" +
                "cnpj=" + cnpj +
                ", country='" + country + '\'' +
                ", companyDescription='" + companyDescription + '\'' +
                '}';
    }
}
