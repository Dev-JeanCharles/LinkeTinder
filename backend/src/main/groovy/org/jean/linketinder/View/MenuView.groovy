package org.jean.linketinder.View

import org.jean.linketinder.Controller.CandidateController
import org.jean.linketinder.Controller.CompanyController
import org.jean.linketinder.Controller.VacancyController

class MenuView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
    private static final PrintMenuView print = new PrintMenuView()

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
                    new CandidateController().getCandidate()
                    break
                case '2' :
                    new CompanyController().getCompany()
                    break
                case '3':
                    new VacancyController().getVacancy()
                    break
                case '4' :
                    new CandidateController().createCandidate()
                    break
                case '5':
                    new CompanyController().createCompany()
                    break
                case '6':
                    new VacancyController().createVacancy()
                    break
                case '7' :
                    new CandidateController().updateCandidate()
                    break
                case '8':
                    new CompanyController().updateCompany()
                    break
                case '9':
                    new VacancyController().updateVacancy()
                    break
                case '10' :
                    new CandidateController().deleteCandidate()
                    break
                case '11':
                    new CompanyController().deleteCompany()
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
