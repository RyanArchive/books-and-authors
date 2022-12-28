package csv.academy.booksandauthors.dto;

import csv.academy.booksandauthors.model.BaseAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookResponseDTO extends BaseAudit {

    private Long id;
    private String title;
    private String description;
    private AuthorResponseDTO author;

}
