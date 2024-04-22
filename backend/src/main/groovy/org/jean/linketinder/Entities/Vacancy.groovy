package org.jean.linketinder.Entities

class Vacancy {
    Integer id
    String name
    String locality
    String description
    List<Skill> skills
    Company company

    Vacancy(Integer id, String name, String locality, String description, List<Skill> skills) {
        this.id = id
        this.name = name
        this.locality = locality
        this.description = description
        this.skills = skills
    }

    void setCompany(Company company) {
        this.company = company
    }
}
