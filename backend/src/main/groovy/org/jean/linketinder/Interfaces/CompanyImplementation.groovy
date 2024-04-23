package org.jean.linketinder.Interfaces

class CompanyImplementation {

    interface CompanyControllerInterface {
        void createCompany()
        void getCompany()
        void updateCompany()
        void deleteCompany()
    }

    interface CompanyOperationsInterface {
        void createCompany()
        void displayCompany()
        void update()
        void delete()
    }
}
