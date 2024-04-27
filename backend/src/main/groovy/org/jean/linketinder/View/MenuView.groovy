package org.jean.linketinder.View

import org.jean.linketinder.Controller.CandidateController
import org.jean.linketinder.Controller.CompanyController
import org.jean.linketinder.Controller.VacancyController
import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.Queries.CandidateQueries
import org.jean.linketinder.Queries.CompanyQueries
import org.jean.linketinder.Queries.VacancyQueries

class MenuView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
    private static final PrintMenuView print = new PrintMenuView()
    private static final DBConnection dbConnection = DBConnection.getInstance()
    private static final HandleException handleException = new HandleException()
    private static final CandidateQueries candidateQueries = new CandidateQueries()
    private static final CompanyQueries companyQueries = new CompanyQueries()
    private static final VacancyQueries vacancyQueries = new VacancyQueries()
    private static final CandidateDAO candidateDAO = new CandidateDAO(dbConnection, handleException, candidateQueries)
    private static final VacancyDAO vacancyDAO = new VacancyDAO(dbConnection, handleException, vacancyQueries)
    private static final CompanyDAO companyDAO = new CompanyDAO(dbConnection, handleException, vacancyDAO, companyQueries)

    static void menuHome(String options) throws IOException{

        while (options != '2'){
            print.initialOptionsMenu()

            options = reader.readLine()

            switch (options) {
                case '1':
                    startMenuOperations(options)
                    break
                case '2':
                    print.exitInitialMenu()
                    break
                default:
                    print.invalidOptions()
                    break
            }
        }
    }

    static void startMenuOperations(String options) throws IOException{

        while (options != '12') {
            print.initialOperationsMenu()

            options = reader.readLine()

            switch (options){
                case '1' :
                    new CandidateController(candidateDAO).getCandidate()
                    break
                case '2' :
                    new CompanyController(companyDAO).getCompany()
                    break
                case '3':
                    new VacancyController(vacancyDAO, companyDAO).getVacancy()
                    break
                case '4' :
                    new CandidateController(candidateDAO).createCandidate()
                    break
                case '5':
                    new CompanyController(companyDAO).createCompany()
                    break
                case '6':
                    new VacancyController(vacancyDAO, companyDAO).createVacancy()
                    break
                case '7' :
                    new CandidateController(candidateDAO).updateCandidate()
                    break
                case '8':
                    new CompanyController(companyDAO).updateCompany()
                    break
                case '9':
                    new VacancyController(vacancyDAO, companyDAO).updateVacancy()
                    break
                case '10' :
                    new CandidateController(candidateDAO).deleteCandidate()
                    break
                case '11':
                    new CompanyController(companyDAO).deleteCompany()
                    break
                case '12' :
                    print.exitOperationsMenu()
                    break
                default:
                    print.invalidOptions()
                    break
            }
        }
    }
}
