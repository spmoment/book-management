package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import com.bookmanagement.repository.BooksRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultBookService implements BookService{

    private BooksRepository booksRepository;
    private BooksConverter booksConverter;
    private static final Logger log = (Logger) LoggerFactory.getLogger(DefaultBookService.class);


    @Override
    public BooksDto saveBook(BooksDto booksDto) throws ValidationException {
        validateBooksDto(booksDto);
        log.info("method: saveBook");
        Books savedBooks = booksRepository.save(booksConverter.fromBooksDtoToBooks(booksDto));
        return booksConverter.fromBooksToBooksDto(savedBooks);
    }

    private void validateBooksDto(BooksDto booksDto) throws ValidationException {
        log.info("method: validateBooksDto, before if (isNull(booksDto))");
        if (isNull(booksDto)) {
            throw new ValidationException("Object user is null");
        }
    }

    @Override
    public BooksDto updateAuthor(BooksDto updateBook) {
        log.info("method: updateAuthor");
        Books updateBooks = booksRepository.save(booksConverter.fromBooksDtoToBooks(updateBook));
        return booksConverter.fromBooksToBooksDto(updateBooks);
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
        log.info("method: findBookById");
        Books books = booksRepository.findBookById(id);
        if(books != null)
            return booksConverter.fromBooksToBooksDto(books);
        return null;
    }
}
