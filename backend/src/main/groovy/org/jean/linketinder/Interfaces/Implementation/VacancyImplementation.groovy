package org.jean.linketinder.Interfaces.Implementation

class VacancyImplementation {

    interface VacancyControllerInterface {
        void createVacancy()
        void getVacancy()
        void updateVacancy()
    }

    interface VacancyOperationsInterface {
        void createVacancy(Scanner scanner)
        void displayVacancies()
        void updateVacancy(Scanner scanner)
    }
}
