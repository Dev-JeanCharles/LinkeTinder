package org.jean.linketinder.Controller

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Interfaces.Implementation.CompanyImplementation
import org.jean.linketinder.Service.CompanyService
import org.jean.linketinder.View.PrintOperationsView

class CompanyController implements CompanyImplementation.CompanyControllerInterface{

    private final CompanyService companyService
    private final Scanner scanner = new Scanner(System.in)

    CompanyController(CompanyDAO companyDAO) {
        this.companyService = new CompanyService(companyDAO)
    }

    @Override
    void createCompany() throws CompanyControllerException{
        try {
            companyService.createCompany(scanner)

        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao inserir uma nova empresa", e)
        }
    }

    @Override
    void getCompany() throws CompanyControllerException{
        try {
            companyService.displayCompany()

        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao buscar uma empresa", e)
        }
    }

    @Override
    void updateCompany() throws CompanyControllerException{
        try {
            companyService.update(scanner)

        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao atualizar uma empresa", e)
        }
    }

    @Override
    void deleteCompany() throws CompanyControllerException{
        try {
            companyService.delete(scanner)

        }catch (Exception e) {
            throw new CompanyControllerException("Erro ao deletar uma empresa", e)
        }
    }
}


