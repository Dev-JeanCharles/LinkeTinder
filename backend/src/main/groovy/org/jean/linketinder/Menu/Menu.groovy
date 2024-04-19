package org.jean.linketinder.Menu

import org.jean.linketinder.Controller.CandidateController
import org.jean.linketinder.Controller.CompanyController
import org.jean.linketinder.View.PrintMenuView

class Menu {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
    private static final PrintMenuView print = new PrintMenuView()

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
                    new CandidateController().getCandidate()
                    break
                case '2' :
                    new CompanyController().getCompany()
                    break
                case '3' :
                    new CandidateController().createCandidate()
                    break
                case '4':
                    new CompanyController().createCompany()
                    break
                case '5' :
                    new CandidateController().updateCandidate()
                    break
                case '6':
                    new CompanyController().updateCompany()
                    break
                case '7' :
                    new CandidateController().deleteCandidate()
                    break
                case '8':
                    new CompanyController().deleteCompany()
                    break
                case '9' :
                    print.exitOperationsMenu()
                    break
                default:
                    print.invalidOptions()
                    break
            }
        } while (options != '9')
    }
}
