package csv.academy.booksandauthors.mapper;

import csv.academy.booksandauthors.dto.BookRequestDTO;
import csv.academy.booksandauthors.dto.BookResponseDTO;
import csv.academy.booksandauthors.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book requestDtoToModel(BookRequestDTO bookDTO);

    BookResponseDTO modelToResponseDto(Book book);

}