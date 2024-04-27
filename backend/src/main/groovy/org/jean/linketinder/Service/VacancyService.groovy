package org.jean.linketinder.Service

import org.jean.linketinder.DAO.CompanyDAO
import org.jean.linketinder.DAO.VacancyDAO
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill
import org.jean.linketinder.Entities.Vacancy
import org.jean.linketinder.Interfaces.Implementation.VacancyImplementation
import org.jean.linketinder.View.PrintOperationsView

class VacancyService implements VacancyImplementation.VacancyOperationsInterface{

    private PrintOperationsView printView = new PrintOperationsView()
    private final VacancyDAO vacancyDAO
    private final CompanyDAO companyDAO

    VacancyService(VacancyDAO vacancyDAO, CompanyDAO companyDAO) {
        this.vacancyDAO = vacancyDAO
        this.companyDAO = companyDAO
    }

    @Override
    void createVacancy(Scanner scanner) {
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

    @Override
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

    @Override
    void updateVacancy(Scanner scanner) {
        Vacancy vacancy = printView.updateVacancy(scanner)
        vacancyDAO.update(vacancy.id, vacancy)
    }
}
