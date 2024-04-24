package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Exceptions.VacancyControllerException
import org.jean.linketinder.Interfaces.Implementation.VacancyImplementation
import org.jean.linketinder.Service.VacancyService
import org.jean.linketinder.View.PrintOperationsView

class VacancyController implements VacancyImplementation.VacancyControllerInterface{

    private final VacancyService vacancyService

    VacancyController(PrintOperationsView printView, VacancyDAO vacancyDAO, CompanyDAO companyDAO) {
        this.vacancyService = new VacancyService(printView, vacancyDAO, companyDAO)
    }

    void createVacancy() throws CompanyControllerException{
        try {
            vacancyService.createVacancy()
        }catch (Exception e) {
            throw new VacancyControllerException("Erro ao inserir uma nova vaga", e)
        }
    }

    void getVacancy() throws CompanyControllerException{
        try {
            vacancyService.displayVacancies()
        }catch (Exception e) {
            throw new VacancyControllerException("Erro ao buscar uma vaga", e)
        }
    }

    void updateVacancy() throws CompanyControllerException{
        try {
            vacancyService.updateVacancy()
        }catch (Exception e) {
            throw new VacancyControllerException("Erro ao atualizar uma vaga", e)
        }
    }
}

