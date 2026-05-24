package Santos.David.integrationtest.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","PrimeiroNome","lastName","birthDate","gender","address",})
public class PersonDTO implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;


    @JsonProperty("PrimeiroNome")
    private String firstName;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;


    private String sensitiveData;


    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;


    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;


    private String address;


    private String gender;

    private Boolean enabled;


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
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(sensitiveData, personDTO.sensitiveData) && Objects.equals(phoneNumber, personDTO.phoneNumber) && Objects.equals(birthDate, personDTO.birthDate) && Objects.equals(address, personDTO.address) && Objects.equals(gender, personDTO.gender) && Objects.equals(enabled, personDTO.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, sensitiveData, phoneNumber, birthDate, address, gender, enabled);
    }
}