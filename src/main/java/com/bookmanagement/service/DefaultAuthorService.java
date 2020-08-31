package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorsDto;
import com.bookmanagement.entity.Authors;
import com.bookmanagement.repository.AuthorsRepository;
import org.springframework.stereotype.Service;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class DefaultAuthorService implements AuthorService {


    private AuthorsRepository authorsRepository;
    private AuthorsConverter authorsConverter;

    private void validateAuthorsDto(AuthorsDto authorsDto) throws ValidationException {
        if (isNull(authorsDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(authorsDto.getId()) ) {
            throw new ValidationException("Id is empty");
        }
    }

    @Override
    public AuthorsDto saveAuthor(AuthorsDto authorsDto) throws ValidationException {
        validateAuthorsDto(authorsDto);
        Authors savedAuthors = authorsRepository.save(authorsConverter.fromAuthorsDtoToAuthors(authorsDto));
        return authorsConverter.fromAuthorsToAuthorsDto(savedAuthors);
    }

    @Override
    public AuthorsDto updateAuthor(AuthorsDto updateAuthor) {
        Authors updateAuthors = authorsRepository.save(authorsConverter.fromAuthorsDtoToAuthors(updateAuthor));
        return authorsConverter.fromAuthorsToAuthorsDto(updateAuthors);
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorsRepository.deleteById(id);
    }

    @Override
    public List<AuthorsDto> findAllAuthors() {
        return authorsRepository.findAll()
                .stream()
                .map(authorsConverter::fromAuthorsToAuthorsDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorsDto findAuthorBySurname(String surname) {
        Authors authors = authorsRepository.findAuthorBySurname(surname);
        if(authors != null)
            return authorsConverter.fromAuthorsToAuthorsDto(authors);
        return null;
    }
}
