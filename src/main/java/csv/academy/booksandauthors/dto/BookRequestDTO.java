package csv.academy.booksandauthors.dto;

import lombok.Data;

@Data
public class BookRequestDTO {

    private Long id;
    private String title;
    private String description;

}
