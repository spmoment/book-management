package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.repository.AuthorsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.ValidationException;

import static com.bookmanagement.prototype.AuthorsPrototype.prAuhorDto;
import static com.bookmanagement.prototype.AuthorsPrototype.prAuthor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DefaultAuthorServiceTest {

    private AuthorsRepository authorsRepository;
    private AuthorsConverter authorsConverter;
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorsRepository = mock(AuthorsRepository.class);
        authorsConverter = new AuthorsConverter();
        authorService = new DefaultAuthorService(authorsRepository, authorsConverter);
    }

    @Test
    void saveAuthor() throws ValidationException {
        when(authorsRepository.save(any())).thenReturn(prAuthor());
        AuthorsDto createdAuthor = authorService.saveAuthor(prAuhorDto());
        assertThat(createdAuthor).isNotNull();
        assertThat(createdAuthor.getFirstName()).isEqualTo(prAuhorDto().getFirstName());
    }

    @Test
    void saveAuthorThrowsValidationExceptionWhenFirstNameIsNull() {
        AuthorsDto authorsDto = prAuhorDto();
        authorsDto.setFirstName("");
        assertThrows(NullPointerException.class,
                () -> authorService.saveAuthor(authorsDto),
                "FirstName is empty");
    }

    @Test
    void deleteAuthor() {
        AuthorsDto a = prAuhorDto();
        authorService.deleteAuthor(a.getId());
        verify(authorsRepository).deleteById(any());
    }

    @Test
    void findAllAuthors() {
        authorService.findAllAuthors();
        verify(authorsRepository).findAll();

    }

    @Test
    void findAuthorByLastName() {
        when(authorsRepository.findAuthorByLastName("TestLastName")).thenReturn(prAuthor());
        AuthorsDto fAuthor = authorService.findAuthorByLastName("TestLastName");
        assertThat(fAuthor).isNotNull();
        assertThat(fAuthor.getLastName()).isEqualTo("TestLastName");
    }
}
