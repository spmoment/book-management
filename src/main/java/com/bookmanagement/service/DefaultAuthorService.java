package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;
import com.bookmanagement.repository.AuthorsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultAuthorService implements AuthorService {


    private AuthorsRepository authorsRepository;
    private AuthorsConverter authorsConverter;
    private static final Logger log = (Logger) LoggerFactory.getLogger(DefaultAuthorService.class);

    @Override
    public AuthorsDto saveAuthor(AuthorsDto authorsDto) throws ValidationException {
        validateAuthorsDto(authorsDto);
        log.info("method: saveAuthor");
        Authors savedAuthors = authorsRepository.save(authorsConverter.fromAuthorsDtoToAuthors(authorsDto));
        return authorsConverter.fromAuthorsToAuthorsDto(savedAuthors);
    }

    private void validateAuthorsDto(AuthorsDto authorsDto) throws ValidationException {
        log.info("method: validateAuthorsDto, before if (isNull(authorsDto))");
        if (isNull(authorsDto)) {
            throw new ValidationException("Object user is null");
        }
        log.info("method: validateAuthorsDto, before if (isNull(authorsDto.getLastName()))");
        if (isNull(authorsDto.getLastName())) {
            throw new ValidationException("Lastname is empty");
        }
    }

    @Override
    public AuthorsDto updateAuthor(AuthorsDto updateAuthor) {
        log.info("method: updateAuthor");
        Authors updateAuthors = authorsRepository.save(authorsConverter.fromAuthorsDtoToAuthors(updateAuthor));
        return authorsConverter.fromAuthorsToAuthorsDto(updateAuthors);
    }

    @Override
    public void deleteAuthor(Integer id) {
        log.info("method: deleteAuthor");
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
    public AuthorsDto findAuthorBySurname(String surname) {
        log.info("method: findAuthorBySurname");
        Authors authors = authorsRepository.findAuthorBySurname(surname);
        if(authors != null)
            return authorsConverter.fromAuthorsToAuthorsDto(authors);
        return null;
    }
}
