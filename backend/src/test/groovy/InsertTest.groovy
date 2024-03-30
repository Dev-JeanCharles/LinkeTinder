//import entities.Candidate
//import org.junit.jupiter.api.Test
//
//class InsertTest {
//
//    @Test
//    void insertNewCandidatesTest () {
//    // Arrange
//        Candidate candidates = new Candidate()
//        List<Candidate> candidatesList = candidates.getCandidatesList()
//
//        String name = "Joe";
//        String email = "joe@gmail.com";
//        String state = "SP";
//        String cep = "24890-000";
//        String cpf = "15851468-00";
//        int age = 25;
//        String description = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."
//
//        // Act
//        Candidate newCandidate = new Candidate(name, email, state, cep, cpf, age, description)
//        newCandidate.getSkillsList().add(Skills.JavaScript)
//        candidates.getCandidatesList().add(newCandidate)
//
//    //Assert
//        assertEquals(newCandidate, candidatesList.get(candidatesList.size() -1))
//    }
//
//    @Test
//    void insertNewCompanyTest () {
//        // Arrange
//        Company company = new Company()
//        List<Company> companyList = company.getCompanyList()
//
//        String name = "Tech Solutions";
//        String email = "techsolutions@gmail.com";
//        String state = "SP";
//        String cep = "09402-320";
//        String cnpj = "59.438.437/0001-54";
//        String country = "Brasil";
//        String companyDescription = "Neque porro quisquam est qui dolorem ipsum quia."
//
//        // Act
//        Company newCompany = new Company(name, email, state, cep, cnpj, country, companyDescription)
//        newCompany.getSkillsList().add(Skills.JavaScript)
//        company.getCompanyList().add(newCompany)
//
//        //Assert
//        assertEquals(newCompany, companyList.get(companyList.size() -1))
//    }
//
//}
