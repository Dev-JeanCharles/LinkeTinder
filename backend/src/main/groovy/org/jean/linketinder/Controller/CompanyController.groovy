package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Manager.CompanyManager
import org.jean.linketinder.View.PrintOperationsView

class CompanyController {

    private final CompanyManager companyManager

    CompanyController(PrintOperationsView printView, CompanyDAO companyDAO) {
        this.companyManager = new CompanyManager(printView, companyDAO)
    }

    void createCompany() throws CompanyControllerException{
        try {
            companyManager.createCompany()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao inserir uma nova empresa", e)
        }
    }

    void getCompany() throws CompanyControllerException{
        try {
            companyManager.displayCompany()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao buscar uma empresa", e)
        }
    }

    void updateCompany() throws CompanyControllerException{
        try {
            companyManager.update()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao atualizar uma empresa", e)
        }
    }

    void deleteCompany() throws CompanyControllerException{
        try {
            companyManager.delete()
        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao deletar uma empresa", e)
        }
    }
}

