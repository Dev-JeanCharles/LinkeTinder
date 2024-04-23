import groovy.sql.Sql
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Exceptions.HandleException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertEquals

class VacancyDAOTest extends Specification {

    Sql sql
    VacancyDAO vacancyDAO

    @BeforeEach
    void setup() {
        sql = Mock(Sql)
        vacancyDAO = new VacancyDAO(sql: sql)
        vacancyDAO.exception = new HandleException()
    }

    @Test
    void CreateVacancyDAOTest() {
        given:
        List<Skill> skillNames = ["Java", "Python", "Groovy"].collect { new Skill(it) }
        Company company = new Company(id: 1, name: "Tech Solutions")
        Vacancy vacancy = new Vacancy(
                id: 4,
                name: "Estágio Desenvolvedor Backend Java",
                locality: "Paraná",
                description: "Desenvolver Software Desktop",
                skills: skillNames
        )

        when:
        vacancyDAO.create(vacancy, company)

        then:
        assertEquals(1, company.id)
        assertEquals("Tech Solutions", company.name)
        assertEquals(4, vacancy.id)
        assertEquals("Estágio Desenvolvedor Backend Java", vacancy.name)
        assertEquals("Paraná", vacancy.locality)
        assertEquals("Desenvolver Software Desktop", vacancy.description)
        assertEquals(3, vacancy.skills.size())

    }
}
