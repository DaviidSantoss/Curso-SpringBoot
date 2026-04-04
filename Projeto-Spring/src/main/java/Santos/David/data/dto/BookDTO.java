package Santos.David.data.dto;

import Santos.David.model.Book;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonPropertyOrder({"id","Autor","Titulo","Preço","Data"})
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    private long id;

    @JsonProperty("Autor")
    private String author;

    @JsonProperty("Titulo")
    private String title;

    @JsonProperty("Data")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date launch_date;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#00.00")
    @JsonProperty("Preço")
    private Double price;

    public BookDTO() {
    }

    public BookDTO(String author, String title, Date launch_date, Double price) {
        this.author = author;
        this.title = title;
        this.launch_date = launch_date;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLaunch_date() {
        return launch_date;
    }

    public void setLaunch_date(Date launch_date) {
        this.launch_date = launch_date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BookDTO book = (BookDTO) o;
        return id == book.id && Objects.equals(author, book.author) && Objects.equals(title, book.title) && Objects.equals(launch_date, book.launch_date) && Objects.equals(price, book.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, launch_date, price);
    }
}
