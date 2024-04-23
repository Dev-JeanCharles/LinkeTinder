package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Interfaces.VacancyImplementation
import org.jean.linketinder.View.PrintOperationsView

class VacancyService implements VacancyImplementation.VacancyOperationsInterface{

    private final PrintOperationsView printView
    private final VacancyDAO vacancyDAO
    private final CompanyDAO companyDAO
    private static final Scanner scanner = new Scanner(System.in)

    VacancyService(PrintOperationsView printView, VacancyDAO vacancyDAO, CompanyDAO companyDAO) {
        this.printView = printView
        this.vacancyDAO = vacancyDAO
        this.companyDAO = companyDAO
    }

    void createVacancy() {
        List<Company> companies = companyDAO.getAll()
        Company selectedCompany = printView.selectExistingCompany(scanner, companies)

        if (selectedCompany != null && selectedCompany.id != null) {
            println "Digite as competências da vaga (separadas por vírgula): "
            List<String> skillNames = scanner.nextLine().split(",").collect { it.trim() }

            List<Skill> skills = skillNames.collect {
                new Skill(it)
            }

            Vacancy newVacancy = printView.createVacancy(scanner, selectedCompany, skills)
            vacancyDAO.create(newVacancy, selectedCompany)
        } else {
            println "Nenhuma empresa selecionada ou a empresa selecionada não possui um ID válido. A vaga não foi criada."
        }
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

    void updateVacancy() {
        Vacancy vacancy = printView.updateVacancy(scanner)
        vacancyDAO.update(vacancy.id, vacancy)
    }
}
