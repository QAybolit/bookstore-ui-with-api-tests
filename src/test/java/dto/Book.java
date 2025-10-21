package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    String isbn;
    String title;
    String subTitle;
    String author;
    String publisher;
    String description;
}
