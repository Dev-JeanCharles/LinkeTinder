import groovy.sql.Sql
import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Queries.CompanyQueries
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import spock.lang.Specification

import java.sql.Connection

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class CompanyDAOTest extends Specification{

    Sql sql
    CompanyDAO companyDAO

    @BeforeEach
    void setup() {
        sql = mock(Sql)
        HandleException handleException = mock(HandleException)
        DBConnection dbConnection = mock(DBConnection)
        VacancyDAO vacancyDAO = mock(VacancyDAO)
        CompanyQueries companyQueries = mock(CompanyQueries)

        when(dbConnection.connect()).thenReturn(mock(Connection))

        companyDAO = new CompanyDAO(dbConnection, handleException, vacancyDAO, companyQueries)

    }

    @Test
    void CreateCompanyDAOTest() {
        given:
        Company company = new Company(
                3,
                "Tech solutions",
                "tech@solutions.com.br",
                "12.345.678/0001-00",
                "Brasil",
                "SP",
                "01000-000",
                "Descrição da empresa"
        )

        when:
        companyDAO.create(company)

        then:
        assertEquals(3, company.id)
        assertEquals("Tech solutions", company.name)
        assertEquals("tech@solutions.com.br", company.email)
        assertEquals("12.345.678/0001-00", company.cnpj)
        assertEquals("Brasil", company.country)
        assertEquals("SP", company.state)
        assertEquals("01000-000", company.cep)
        assertEquals("Descrição da empresa", company.description)
    }
}
