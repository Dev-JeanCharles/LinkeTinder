package org.jean.linketinder.Manager

import org.jean.linketinder.Entities.Candidate
import org.jean.linketinder.Entities.Company
import org.jean.linketinder.Entities.Skill

class Register {

    public List<Candidate> candidates = [
            new Candidate(
                    "Joao",
                    "joao@email.com",
                    "123.456.789-00",
                    30,
                    "SP",
                    "12345-678",
                    "Me formei em Engenharia de software pela Estácio.",
                    ["Java", "JavaScript", "Git"] as List<Skill>
            ),
            new Candidate(
                    "Priscila",
                    "priscila@email.com",
                    "987.654.321-00",
                    25,
                    "RJ",
                    "54321-876",
                    "Sou formada em enfermagem e busco uma transição de carreira para a área de tecnologia.",
                    ["Python", "C#", "Docker"] as List<Skill>
            ),
            new Candidate(
                    "Ana",
                    "ana@email.com",
                    "111.222.333-44",
                    28,
                    "MG",
                    "98765-432",
                    "Atualmente estudo Análise e Desenvolvimento de Sistemas pelo IFMG e busco uma oportunidade de estágio.",
                    ["Ruby", "React", "SQL"] as List<Skill>
            ),
            new Candidate(
                    "Carlos",
                    "carlos@email.com",
                    "555.666.777-88",
                    32,
                    "RS",
                    "87654-321",
                    "Sou formado em Ciência da Computação pelo IFRS e concluí uma pós em Ciber Segurança pela mesma instituição.",
                    ["C++", "Angular", "AWS"] as List<Skill>
            ),
            new Candidate(
                    "Marina",
                    "marina@email.com",
                    "999.888.777-66",
                    23,
                    "BA",
                    "65432-109",
                    "Tenho interesse em atuar como desenvolvedora mobile.",
                    ["Swift", "iOS Development", "Firebase"] as List<Skill>
            )
    ]

    List<Company> companies = [
            new Company(
                    "ABC Tech",
                    "contato@abc.com",
                    "123.456.789/0001-01",
                    "Brasil",
                    "SP",
                    "12345-678",
                    "Empresa de tecnologia inovadora",
                    ["Java", "JavaScript", "AWS"] as List<Skill>
            ),
            new Company(
                    "XYZ Solutions",
                    "contato@xyz.com",
                    "987.654.321/0001-02",
                    "EUA",
                    "CA",
                    "98765-432",
                    "Consultoria em soluções empresariais",
                    ["Python", "Django", "Machine Learning"] as List<Skill>
            ),
            new Company(
                    "Global Soft",
                    "contato@globalsoft.com",
                    "456.789.123/0001-03",
                    "Índia",
                    "MH",
                    "56789-012",
                    "Desenvolvimento de software personalizado",
                    ["C#", "ASP.NET", "SQL Server"] as List<Skill>
            ),
            new Company(
                    "Tech Innovators",
                    "contato@techinnovators.com",
                    "789.123.456/0001-04",
                    "Alemanha",
                    "BW",
                    "34567-890",
                    "Startup focada em inovações tecnológicas",
                    ["React", "Node.js", "MongoDB"] as List<Skill>
            ),
            new Company(
                    "Data Systems",
                    "contato@datasystems.com",
                    "234.567.890/0001-05",
                    "Canadá",
                    "ON",
                    "23456-789",
                    "Soluções de gerenciamento de dados",
                    ["SQL", "Power BI", "Big Data"] as List<Skill>
            )
    ]

    void registerCandidate(Candidate candidate){
        candidates.add(candidate)
    }

    void registerCompany(Company company){
        companies.add(company)
    }

//    void getCandidates(){
//
//        candidates.each {candidate -> println("Nome: ${candidate.name}, Email: ${candidate.email}, CPF: ${candidate.cpf}, Idade: ${candidate.age}, Estado: ${candidate.state}, CEP: ${candidate.cep}, Descrição Pessoal: ${candidate.description}, Competências: ${candidate.skills} ")}
//    }
//
//    void getCompany(){
//
//        companies.each {company -> println("Empresa: ${company.name}, Email Corporativo: ${company.email}, CNPJ: ${company.cnpj}, Pais: ${company.country}, Estado: ${company.state}, CEP: ${company.cep}, Descrição da Empresa: ${company.description}, Competências: ${company.skillsList}")}
//    }
}

