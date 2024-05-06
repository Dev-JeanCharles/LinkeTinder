package org.jean.linketinder.Controller

import com.google.gson.Gson
import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Exceptions.CompanyControllerException
import org.jean.linketinder.Exceptions.VacancyControllerException
import org.jean.linketinder.Interfaces.Implementation.VacancyImplementation
import org.jean.linketinder.Model.Entity.Company
import org.jean.linketinder.Model.Entity.Vacancy
import org.jean.linketinder.Service.VacancyService

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/vacancy"])
class VacancyController extends HttpServlet implements VacancyImplementation.VacancyControllerInterface{

    private final VacancyService vacancyService
    private final VacancyDAO vacancyDAO
    private final CompanyDAO companyDAO
    private final Scanner scanner
    private final Gson gson = new Gson()

    VacancyController() {
        this.scanner = new Scanner(System.in)
        this.companyDAO = new CompanyDAO()
        this.vacancyDAO = new VacancyDAO()
        this.vacancyService = new VacancyService(vacancyDAO, companyDAO)
    }

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) {

        try {
            BufferedReader reader = request.getReader()
            Vacancy vacancy = gson.fromJson(reader, Vacancy)

            String companyIdParam = request.getParameter("companyId");
            Company company = companyDAO.getCompanyIdByCnpj(companyIdParam) as Company

            vacancyDAO.create(vacancy, company)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write(gson.toJson(vacancy));

        } catch (Exception e) {
            e.printStackTrace()
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar a vaga!")
        }
    }

    void createVacancy() throws CompanyControllerException{
        try {
            vacancyService.createVacancy(scanner)

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
            vacancyService.updateVacancy(scanner)

        }catch (Exception e) {
            throw new VacancyControllerException("Erro ao atualizar uma vaga", e)
        }
    }
}

