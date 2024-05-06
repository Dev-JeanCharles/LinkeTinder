package org.jean.linketinder.Controller

import com.google.gson.Gson
import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Exceptions.CandidateControllerException
import org.jean.linketinder.Interfaces.Implementation.CandidateImplementation
import org.jean.linketinder.Model.Entity.Candidate
import org.jean.linketinder.Service.CandidateService

import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["/candidate"])
class CandidateController extends HttpServlet implements CandidateImplementation.CandidateControllerInterface {

    private final CandidateService candidateService
    private final CandidateDAO candidateDAO
    private final Scanner scanner
    private final Gson gson = new Gson()

    CandidateController() {
        this.scanner = new Scanner(System.in)
        this.candidateDAO = new CandidateDAO()
        this.candidateService = new CandidateService(candidateDAO)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BufferedReader reader = request.getReader()
            Candidate candidate = gson.fromJson(reader, Candidate)
            candidateDAO.create(candidate)

            response.setStatus(HttpServletResponse.SC_CREATED)
            response.getWriter().write(gson.toJson(candidate))

        } catch (Exception e) {
            e.printStackTrace()
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar Candidato!")
        }
    }

    @Override
    void createCandidate() throws CandidateControllerException {
        try {
            candidateService.createCandidate(scanner)

        } catch (Exception e) {
            throw new CandidateControllerException("Erro ao inserir um novo candidato", e)
        }
    }

    @Override
    void getCandidate() throws CandidateControllerException {
        try {
            candidateService.displayCandidates()

        } catch (Exception e) {
            throw new CandidateControllerException("Erro ao buscar um candidato", e)
        }
    }

    @Override
    void updateCandidate() throws CandidateControllerException {
        try {
            candidateService.updateCandidate(scanner)

        } catch (Exception e ) {
            throw new CandidateControllerException("Erro ao atualizar um candidato", e)
        }
    }

    @Override
    void deleteCandidate() throws CandidateControllerException {
        try {
            candidateService.deleteCandidate(scanner)

        } catch (Exception e) {
            throw new CandidateControllerException("Erro ao deletar um candidato", e)
        }
    }
}
