package csv.academy.booksandauthors.dto;

import csv.academy.booksandauthors.model.BaseAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorResponseDTO extends BaseAudit {

    private Long id;
    private String name;

}
