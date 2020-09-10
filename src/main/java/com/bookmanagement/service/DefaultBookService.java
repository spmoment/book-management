package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.repository.BooksRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultBookService implements BookService {

    private static final Logger log = Logger.getLogger("DefaultBookService.class");
    private BooksRepository booksRepository;
    private BooksConverter booksConverter;

    public DefaultBookService(BooksRepository booksRepository, BooksConverter booksConverter) {
        this.booksRepository = booksRepository;
        this.booksConverter = booksConverter;
    }

    @Override
    public BooksDto saveBook(BooksDto booksDto) throws ValidationException {
        validateBooksDto(booksDto);
        log.info("method: saveBook" + booksDto);
        Books savedBooks = booksRepository.save(booksConverter.fromBooksDtoToBooks(booksDto));
        return booksConverter.fromBooksToBooksDto(savedBooks);
    }

    private void validateBooksDto(BooksDto booksDto) throws ValidationException {
        log.info("method: validateBooksDto, before if (isNull(booksDto))" + booksDto);
        if (isNull(booksDto)) {
            throw new ValidationException("Object book is null");
        }
    }

    @Override
    public BooksDto updateAuthor(BooksDto updateBook) throws NotFoundException {
        log.info("method: updateBook" + updateBook);
        Books book = booksRepository.findById(updateBook.getId()).orElseThrow(() -> new NotFoundException("Book not found with id=" + updateBook.getId()));
        book.setTitle(updateBook.getTitle());
        book.setYearPublishing(updateBook.getYearPublishing());
        book.setAnnotation(updateBook.getAnnotation());
        return updateBook;
    }

    @Override
    public void deleteBook(Integer id) {
        booksRepository.deleteById(id);
    }

    @Override
    public List<BooksDto> findAllBooks() {
        log.info("method: deleteBook");
        return booksRepository.findAll()
                .stream()
                .map(booksConverter::fromBooksToBooksDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooksDto findBookById(Integer id) {
        log.info("method: findBookById" + id);
        Books books = booksRepository.findBookById(id);
        if(books != null)
            return booksConverter.fromBooksToBooksDto(books);
        return null;
    }
}
