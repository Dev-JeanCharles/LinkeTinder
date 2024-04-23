import groovy.sql.Sql
import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Exceptions.HandleException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spock.lang.Specification

import static org.junit.jupiter.api.Assertions.assertEquals

class CandidateDAOTest extends Specification {

    Sql sql
    CandidateDAO candidateDAO

    @BeforeEach
    void setup() {
        sql = Mock(Sql)
        candidateDAO = new CandidateDAO(sql: sql)
        candidateDAO.exception = new HandleException()
    }

    @Test
    void CreateCandidate() {
        given:
        List<String> skillNames = ["Java", "Python"]
        Candidate candidate = new Candidate(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                30,
                "California",
                "12345-678",
                "Experienced software engineer",
                skillNames as List<Skill>
        )

        when:
        candidateDAO.create(candidate)

        then:
        assertEquals("John Doe", candidate.name)
        assertEquals("john.doe@example.com", candidate.email)
        assertEquals("123.456.789-00", candidate.cpf)
        assertEquals(30, candidate.age)
        assertEquals("California", candidate.state)
        assertEquals("12345-678", candidate.cep)
        assertEquals("Experienced software engineer", candidate.description)
        assertEquals(2, candidate.skills.size())
    }
}
