package Manager

import entities.Candidate
import entities.Company

class Register {

    List<Candidate> candidates = [

            new Candidate(
                    "Jean",
                    "jean@email.com",
                    "158.514.687-00",
                    19,
                    "RJ",
                    "24890-000",
                    "Sou participante do Acelera ZG",
                    ["Java", "JavaScript", "Groovy"]
            ),
            new Candidate(
                    "João",
                    "joão@email.com",
                    "987.654.321-00",
                    25,
                    "SP",
                    "54321-876",
                    "Sou programador backend",
                    ["Python", "C#", "Docker"]
            ),
            new Candidate(
                    "Ana",
                    "ana@email.com",
                    "111.222.333-44",
                    28,
                    "ES",
                    "98765-432",
                    "Sou programadora FullStack.",
                    ["Ruby", "React", "SQL"]
            ),
            new Candidate(
                    "Pedro",
                    "pedro@email.com",
                    "555.666.777-88",
                    32,
                    "RS",
                    "87654-321",
                    "Sou analista de sistemas.",
                    ["C++", "Angular", "AWS"]
            ),
            new Candidate(
                    "Marina",
                    "marina@email.com",
                    "999.888.777-66",
                    23,
                    "MT",
                    "65432-109",
                    "Sou desenvolvedora Front-End",
                    ["Javascript", "Angular", "Typescript"]
            )
    ]
    List<Company> companies = [
            new Company(
                    "ABC Tech",
                    "tech@abc.com",
                    "123.456.789/0001-01",
                    "Brasil",
                    "SP",
                    "12345-678",
                    "Empresa de tecnologia inovadora",
                    ["Java", "JavaScript", "AWS"]
            ),
            new Company(
                    "New Solutions",
                    "new@solutions.com",
                    "987.654.321/0001-02",
                    "Brasil",
                    "MG",
                    "98765-432",
                    "Novas soluções aqui!",
                    ["Python", "Django", "Machine Learning"]
            ),
            new Company(
                    "ZG Soft",
                    "zg@soft.com",
                    "456.789.123/0001-03",
                    "Brasil",
                    "GO",
                    "56789-012",
                    "Desenvolvimento de software em Goiânia",
                    ["C#", "ASP.NET", "SQL Server"]
            ),
            new Company(
                    "Tech Innovation",
                    "tech@innovation.com",
                    "789.123.456/0001-04",
                    "Brasil",
                    "RS",
                    "34567-890",
                    "Startup de tecnologia e soluções",
                    ["React", "Node.js", "MongoDB"]
            ),
            new Company(
                    "Math Systems",
                    "math@systems.com",
                    "234.567.890/0001-05",
                    "Brasil",
                    "RJ",
                    "23456-789",
                    "Soluções de calculos matemáticos é aqui.",
                    ["SQL", "Power BI", "Big Data"]
            )
    ]

    void registerCandidates(Candidate candidate){
        candidates.add(candidate)
    }

    void registerCompanies(Company company){
        companies.add(company)
    }

    void getCandidates(){
        candidates.each {candidate -> println("Nome: ${candidate.name}, Email: ${candidate.email}, CPF: ${candidate.cpf}, Idade: ${candidate.age}, Estado: ${candidate.state}, CEP: ${candidate.cep}, Descrição Pessoal: ${candidate.description}, Competências: ${candidate.skillsList}, ")}
    }

    void getCompanies(){
        companies.each {company -> println("Empresa: ${company.name}, Email Corporativo: ${company.email}, CNPJ: ${company.cnpj}, Pais: ${company.country}, Estado: ${company.state}, CEP: ${company.cep}, Descrição da Empresa: ${company.description}, Competências: ${company.skillsList}, ")}
    }
}

