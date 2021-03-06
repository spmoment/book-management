package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.exception.ValidationException;
import com.bookmanagement.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultBookService implements BookService {

    private BooksRepository booksRepository;
    private BooksConverter booksConverter;
    private static final Logger log = Logger.getLogger("DefaultBookService.class");

    public DefaultBookService(BooksRepository booksRepository, BooksConverter booksConverter) {
        this.booksRepository = booksRepository;
        this.booksConverter = booksConverter;
    }

    @Override
    public BooksDto saveBook(BooksDto booksDto) throws ValidationException {
        validateBooksDto(booksDto);
        log.info("method: saveBook " + booksDto);
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
    public BooksDto updateBook(BooksDto updateDto) throws NotFoundException {
        log.info("method: updateBook" + updateDto);
        Books book = booksRepository.findById(updateDto.getId()).orElseThrow(() -> new NotFoundException("Book not found with id=" + updateDto.getId()));
        book.setTitle(updateDto.getTitle());
        book.setYearPublishing(updateDto.getYearPublishing());
        book.setAnnotation(updateDto.getAnnotation());
        book.setPrice(updateDto.getPrice());
        booksRepository.save(book);
        return updateDto;
    }

    @Override
    public void deleteBook(Integer id) {
        log.info("method: deleteBook" + id);
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
