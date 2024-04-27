package org.jean.linketinder.Interfaces.Implementation

class CompanyImplementation {

    interface CompanyControllerInterface {
        void createCompany()
        void getCompany()
        void updateCompany()
        void deleteCompany()
    }

    interface CompanyOperationsInterface {
        void createCompany(Scanner scanner)
        void displayCompany()
        void update(Scanner scanner)
        void delete(Scanner scanner)
    }
}
