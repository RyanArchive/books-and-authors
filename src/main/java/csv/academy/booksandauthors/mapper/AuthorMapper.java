package csv.academy.booksandauthors.mapper;

import csv.academy.booksandauthors.dto.AuthorRequestDTO;
import csv.academy.booksandauthors.dto.AuthorResponseDTO;
import csv.academy.booksandauthors.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    Author requestDtoToModel(AuthorRequestDTO authorDTO);

    AuthorResponseDTO modelToResponseDto(Author author);

}