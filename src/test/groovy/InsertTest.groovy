import entities.Candidates
import enums.Skills
import org.junit.Test

import static org.junit.Assert.assertEquals

class InsertTest {

    @Test
    void insertNewCandidatesTest () {
    // Arrange
        Candidates candidates = new Candidates()
        List<Candidates> candidatesList = candidates.getCandidatesList()

        String name = "Joe";
        String email = "joe@gmail.com";
        String state = "SP";
        String cep = "24890-000";
        String cpf = "15851468-00";
        int age = 25;
        String personalDescription = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit..."

        // Act
        Candidates newCandidate = new Candidates(name, email, state, cep, cpf, age, personalDescription)
        newCandidate.getSkillsList().add(Skills.JavaScript)
        candidates.getCandidatesList().add(newCandidate)

    //Assert
        assertEquals(newCandidate, candidatesList.get(candidatesList.size() -1))
    }
}
