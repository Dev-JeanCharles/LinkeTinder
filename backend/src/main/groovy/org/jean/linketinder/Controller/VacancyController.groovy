package org.jean.linketinder.Controller


import org.jean.linketinder.Service.VacancyService

class VacancyController {
    private final VacancyService vacancyService

    VacancyController(VacancyService vacancyService) {
        this.vacancyService = vacancyService
    }

    void createVacancy() {
        vacancyService.createVacancy()
    }

    void getVacancy() {
        vacancyService.displayVacancies()
    }

    void updateVacancy() {
        vacancyService.updateVacancy()
    }
}

