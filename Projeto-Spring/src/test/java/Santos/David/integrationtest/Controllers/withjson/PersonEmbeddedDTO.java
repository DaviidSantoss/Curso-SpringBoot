package Santos.David.integrationtest.Controllers.withjson;

import Santos.David.integrationtest.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PersonEmbeddedDTO {

    private static final long serialVersionUID = 1L;

    @JsonProperty("books")
    private List<PersonDTO> persons;

    public PersonEmbeddedDTO() {}

    public List<PersonDTO> getPeople() {
        return getPeople();
    }

    public void setPersons(List<PersonDTO> persons) {
        this.persons = persons;
    }
}
