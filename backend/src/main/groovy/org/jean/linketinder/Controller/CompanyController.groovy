package org.jean.linketinder.Controller

import com.google.gson.Gson
import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Interfaces.Implementation.CompanyImplementation
import org.jean.linketinder.Model.Entity.Company
import org.jean.linketinder.Service.CompanyService

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/company"])
class CompanyController extends HttpServlet implements CompanyImplementation.CompanyControllerInterface{

    private final CompanyService companyService
    private final CompanyDAO companyDAO
    private final Scanner scanner
    private final Gson gson = new Gson()

    CompanyController() {
        this.scanner = new Scanner(System.in)
        this.companyDAO = new CompanyDAO()
        this.companyService = new CompanyService(companyDAO)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader()
            Company company = gson.fromJson(reader, Company)
            companyDAO.create(company)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write(gson.toJson(company))

        } catch (Exception e) {
            e.printStackTrace()
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar a empresa!")
        }
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


