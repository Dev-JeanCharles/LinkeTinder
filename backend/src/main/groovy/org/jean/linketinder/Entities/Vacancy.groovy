package org.jean.linketinder.Entities

class Vacancy {

    UUID id = UUID.randomUUID()
    String name
    String locality
    String description
    List<String> skills

    Vacancy(UUID id, String name, String locality, String description, List<String> skills) {
        this.id = id
        this.name = name
        this.locality = locality
        this.description = description
        this.skills = skills
    }
}
