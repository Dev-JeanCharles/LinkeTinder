import groovy.sql.Sql
import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Queries.CandidateQueries
import spock.lang.Specification

import java.sql.Connection

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CandidateDAOTest extends Specification {

    Sql sql
    CandidateDAO candidateDAO

    void setup() {
        sql = mock(Sql)
        HandleException handleException = mock(HandleException)
        DBConnection dbConnection = mock(DBConnection)
        CandidateQueries candidateQueries = mock(CandidateQueries)

        when(dbConnection.connect()).thenReturn(mock(Connection))

        candidateDAO = new CandidateDAO(dbConnection, handleException, candidateQueries)
    }

    void "CreateCandidate"() {
        given:
        List<String> skillNames = ["Java", "Python"]
        List<Skill> skills = skillNames.collect { new Skill(it) }
        Candidate candidate = new Candidate(
                "John Doe",
                "john.doe@example.com",
                "123.456.789-00",
                30,
                "California",
                "12345-678",
                "Experienced software engineer",
                skills
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
