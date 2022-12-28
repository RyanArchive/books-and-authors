package csv.academy.booksandauthors.repository;

import csv.academy.booksandauthors.model.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    List<Book> findBookByAuthorId(Long authorId);

    Optional<Book> findBookByAuthorIdAndId(Long authorId, Long bookId);

}
