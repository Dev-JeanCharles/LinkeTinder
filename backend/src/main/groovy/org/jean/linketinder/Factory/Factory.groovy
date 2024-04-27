package org.jean.linketinder.Factory

import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Queries.CandidateQueries
import org.jean.linketinder.Queries.CompanyQueries
import org.jean.linketinder.Queries.VacancyQueries

class Factory {

    static VacancyDAO createVacancyDAO() {
        return new VacancyDAO()
    }

    static DBConnection createDBConnection() {
        return DBConnection.getInstance()
    }

    static HandleException createHandleException() {
        return new HandleException()
    }

    static CandidateQueries createCandidateQueries() {
        return new CandidateQueries()
    }

    static CompanyQueries createCompanyQueries() {
        return new CompanyQueries()
    }

    static VacancyQueries createVacancyQueries() {
        return new VacancyQueries()
    }
}
