package org.jean.linketinder.Menu

import org.jean.linketinder.Controller.CandidateController
import org.jean.linketinder.Controller.CompanyController
import org.jean.linketinder.Controller.VacancyController
import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Exceptions.HandleException
import org.jean.linketinder.Interfaces.DB.DBConnection
import org.jean.linketinder.View.PrintMenuView
import org.jean.linketinder.View.PrintOperationsView

class Menu {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
    private static final Scanner scanner = new Scanner(System.in)
    private static final PrintMenuView print = new PrintMenuView()
    private static final DBConnection dbConnection = new DBConnection()
    private static final HandleException handleException = new HandleException()
    private static final PrintOperationsView printOperationsView = new PrintOperationsView()
    private static final CandidateDAO candidateDAO = new CandidateDAO(dbConnection, handleException)
    private static final CompanyDAO companyDAO = new CompanyDAO()
    private static final VacancyDAO vacancyDAO = new VacancyDAO()

    static void menuHome(String options) throws IOException{

        do {
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
        }while (options != '2')
    }

    static void startMenuOperations(String options) throws IOException{

        do {
            print.initialOperationsMenu()

            options = reader.readLine()

            switch (options){
                case '1' :
                    new CandidateController(printOperationsView, candidateDAO).getCandidate()
                    break
                case '2' :
                    new CompanyController(printOperationsView, companyDAO, scanner).getCompany()
                    break
                case '3':
                    new VacancyController(printOperationsView, vacancyDAO, companyDAO).getVacancy()
                    break
                case '4' :
                    new CandidateController(printOperationsView, candidateDAO).createCandidate()
                    break
                case '5':
                    new CompanyController(printOperationsView, companyDAO, scanner).createCompany()
                    break
                case '6':
                    new VacancyController(printOperationsView, vacancyDAO, companyDAO).createVacancy()
                    break
                case '7' :
                    new CandidateController(printOperationsView, candidateDAO).updateCandidate()
                    break
                case '8':
                    new CompanyController(printOperationsView, companyDAO, scanner).updateCompany()
                    break
                case '9':
                    new VacancyController(printOperationsView, vacancyDAO, companyDAO).updateVacancy()
                    break
                case '10' :
                    new CandidateController(printOperationsView, candidateDAO).deleteCandidate()
                    break
                case '11':
                    new CompanyController(printOperationsView, companyDAO, scanner).deleteCompany()
                    break
                case '12' :
                    print.exitOperationsMenu()
                    break
                default:
                    print.invalidOptions()
                    break
            }
        } while (options != '12')
    }
}
