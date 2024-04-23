package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Interfaces.CompanyImplementation
import org.jean.linketinder.Service.CompanyService
import org.jean.linketinder.View.PrintOperationsView

class CompanyController implements CompanyImplementation.CompanyControllerInterface{

    private final CompanyService companyService
    private final Scanner scanner

    CompanyController(PrintOperationsView printView, CompanyDAO companyDAO) {
        this.companyService = new CompanyService(printView, companyDAO, scanner)
    }

    void createCompany() throws CompanyControllerException{
        try {
            companyService.createCompany()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao inserir uma nova empresa", e)
        }
    }

    void getCompany() throws CompanyControllerException{
        try {
            companyService.displayCompany()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao buscar uma empresa", e)
        }
    }

    void updateCompany() throws CompanyControllerException{
        try {
            companyService.update()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao atualizar uma empresa", e)
        }
    }

    void deleteCompany() throws CompanyControllerException{
        try {
            companyService.delete()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao deletar uma empresa", e)
        }
    }
}


