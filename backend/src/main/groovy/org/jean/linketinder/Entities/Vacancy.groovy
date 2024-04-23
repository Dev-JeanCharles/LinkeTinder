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

    Vacancy(Map<String, Object> attributes) {
        this.id = attributes.id as Integer
        this.name = attributes.name as String
        this.locality = attributes.locality as String
        this.description = attributes.description as String
        this.skills = attributes.skills as List<Skill>
    }

    void setCompany(Company company) {
        this.company = company
    }
}
