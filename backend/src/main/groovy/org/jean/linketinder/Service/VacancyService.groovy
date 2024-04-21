package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CandidateDAO
import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.View.PrintOperationsView

class VacancyService {

    private PrintOperationsView printView = new PrintOperationsView()
    private VacancyDAO vacancyDAO = new VacancyDAO()
    private Scanner scanner = new Scanner(System.in)

    VacancyService(PrintOperationsView printView, CandidateDAO candidateDAO) {
        this.printView = printView
        this.vacancyDAO = candidateDAO
    }

    void createVacancy() {
        Vacancy newVacancy = printView.createVacancy(scanner)
        vacancyDAO.create(newVacancy)
    }

    void displayVacancies() {
        List<Vacancy> vacancies = vacancyDAO.getAll()

        if (vacancies) {
            println("Lista de vagas:")
            for (Vacancy vacancy : vacancies) {
                printView.displayVacancyInfo(vacancy)
            }

        } else {
            println("Nenhuma vaga encontrada.")
        }
    }

    void updateVacancy(){
        Vacancy vacancy = printView.updateVacancy(scanner)
        vacancyDAO.update(vacancy.cnpj, vacancy)
    }
}
