package Controller

import Manager.CompanyManager

class CompanyController {

    static void insertCompany() {
        new CompanyManager().create()
    }

    static void getCompany() {
        new CompanyManager().get()
    }

    static void updateCompany() {
        new CompanyManager().update()
    }

    static void deleteCompany() {
        new CompanyManager().delete()
    }
}


