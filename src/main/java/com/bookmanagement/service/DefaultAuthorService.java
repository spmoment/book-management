package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;
import com.bookmanagement.exception.NotFoundException;
import com.bookmanagement.repository.AuthorsRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultAuthorService implements AuthorService {

    private static final Logger log = Logger.getLogger("DefaultAuthorService.class");
    private final AuthorsRepository authorsRepository;
    private final AuthorsConverter authorsConverter;

    public DefaultAuthorService(AuthorsRepository authorsRepository, AuthorsConverter authorsConverter) {
        this.authorsRepository = authorsRepository;
        this.authorsConverter = authorsConverter;
    }

    @Override
    public AuthorsDto saveAuthor(AuthorsDto authorsDto) throws ValidationException {
        validateAuthorsDto(authorsDto);
        log.info("method: saveAuthor" + authorsDto);
        Authors savedAuthors = authorsRepository.save(authorsConverter.fromAuthorsDtoToAuthors(authorsDto));
        return authorsConverter.fromAuthorsToAuthorsDto(savedAuthors);
    }

    private void validateAuthorsDto(AuthorsDto authorsDto) throws ValidationException {
        log.info("method: validateAuthorsDto, before if (isNull(authorsDto))" + authorsDto);
        if (isNull(authorsDto)) {
            throw new ValidationException("Object author is null");
        }
        log.info("method: validateAuthorsDto, before if (isNull(authorsDto.getLastName()))" + authorsDto.getLastName());
        if (isNull(authorsDto.getLastName())) {
            throw new ValidationException("Lastname is empty");
        }
    }

    @Override
    public AuthorsDto updateAuthor(AuthorsDto updateDto) throws ValidationException, NotFoundException {
        log.info("method: updateAuthor" + updateDto);
        Authors author = authorsRepository.findById(updateDto.getId()).orElseThrow(() -> new NotFoundException("Author not found with id=" + updateDto.getId()));
        author.setFirstName(updateDto.getFirstName());
        author.setLastName(updateDto.getLastName());
        author.setYearOfBirth(updateDto.getYearOfBirth());
        return updateDto;
    }

    @Override
    public void deleteAuthor(Integer id) {
        log.info("method: deleteAuthor" + id);
        authorsRepository.deleteById(id);
    }

    @Override
    public List<AuthorsDto> findAllAuthors() {
        log.info("method: findAllAuthors");
        return authorsRepository.findAll()
                .stream()
                .map(authorsConverter::fromAuthorsToAuthorsDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorsDto findAuthorByLastName(String lastName) {
        log.info("method: findAuthorByLastName" + lastName);
        Authors authors = authorsRepository.findAuthorByLastName(lastName);
        return Optional.of(authorsConverter.fromAuthorsToAuthorsDto(authors)).orElse(null);
    }
}
