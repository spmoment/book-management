package com.bookmanagement.service;

import com.bookmanagement.dto.BooksDto;
import com.bookmanagement.entity.Books;
import com.bookmanagement.repository.BooksRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultBookService implements BookService{

    private BooksRepository booksRepository;
    private BooksConverter booksConverter;

    private void validateBooksDto(BooksDto booksDto) throws ValidationException {
        if (isNull(booksDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(booksDto.getId()) ) {
            throw new ValidationException("Id is empty");
        }
    }

    @Override
    public BooksDto saveBook(BooksDto booksDto) throws ValidationException {
        validateBooksDto(booksDto);
        Books savedBooks = booksRepository.save(booksConverter.fromBooksDtoToBooks(booksDto));
        return booksConverter.fromBooksToBooksDto(savedBooks);
    }

    @Override
    public BooksDto updateAuthor(BooksDto updateBook) {
        Books updateBooks = booksRepository.save(booksConverter.fromBooksDtoToBooks(updateBook));
        return booksConverter.fromBooksToBooksDto(updateBooks);
    }

    @Override
    public void deleteBook(Integer id) {
        booksRepository.deleteById(id);
    }

    @Override
    public List<BooksDto> findAllBooks() {
        return booksRepository.findAll()
                .stream()
                .map(booksConverter::fromBooksToBooksDto)
                .collect(Collectors.toList());
    }

    @Override
    public BooksDto findBookById(Integer id) {
        Books books = booksRepository.findBookById(id);
        if(books != null)
            return booksConverter.fromBooksToBooksDto(books);
        return null;
    }
}
