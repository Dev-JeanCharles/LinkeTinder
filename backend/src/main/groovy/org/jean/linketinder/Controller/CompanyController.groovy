package org.jean.linketinder.Controller


import org.jean.linketinder.Service.CompanyService

class CompanyController {
    private final CompanyService companyService

    CompanyController(CompanyService companyService) {
        this.companyService = companyService
    }

    void createCompany() {
        companyService.createCompany()
    }

    void getCompany() {
        companyService.displayCompany()
    }

    void updateCompany() {
        companyService.update()
    }

    void deleteCompany() {
        companyService.delete()
    }
}


