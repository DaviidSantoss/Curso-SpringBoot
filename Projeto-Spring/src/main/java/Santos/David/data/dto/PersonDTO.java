package Santos.David.data.dto;


import com.fasterxml.jackson.annotation.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Relation(collectionRelation = "people")
@JsonPropertyOrder({"id","PrimeiroNome","lastName","birthDate","gender","address",})
public class PersonDTO extends RepresentationModel<PersonDTO>  implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    /* Utilizado para mudar o nome do campo na hora
    * de retornar o Json */
    @JsonProperty("PrimeiroNome")
    private String firstName;

    /* Se o campo for nullo ele será ignorado e não será exibido. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    private String sensitiveData;

    /* Se o campo estiver vazio ele será ignorado e não será exibido. */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;

    /*Utilizado para formatar a resposta.  */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;

    private String address;

    private Boolean enabled;

//    /*Utilizado para "ignorar" o campo na hora de retornar
//    * o Json.  */
//    @JsonIgnore
    private String gender;

    public PersonDTO() {
    }

    public PersonDTO(Long id, String firstName, String lastName, String address, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSensitiveData() {
        return sensitiveData;
    }

    public void setSensitiveData(String sensitiveData) {
        this.sensitiveData = sensitiveData;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(sensitiveData, personDTO.sensitiveData) && Objects.equals(phoneNumber, personDTO.phoneNumber) && Objects.equals(birthDate, personDTO.birthDate) && Objects.equals(address, personDTO.address) && Objects.equals(enabled, personDTO.enabled) && Objects.equals(gender, personDTO.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, sensitiveData, phoneNumber, birthDate, address, enabled, gender);
    }
}