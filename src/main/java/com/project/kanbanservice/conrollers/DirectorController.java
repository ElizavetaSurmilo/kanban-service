package com.project.kanbanservice.conrollers;


import com.project.kanbanservice.dto.DirectorDto;
import com.project.kanbanservice.entity.DirectorEntity;
import com.project.kanbanservice.exceptions.BadRequestException;
import com.project.kanbanservice.factories.DirectorDtoFactory;
import com.project.kanbanservice.repositories.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Transactional
@RestController
public class DirectorController {

    private final DirectorRepository directorRepository;
    private final DirectorDtoFactory directorDtoFactory;

    public static final String CREATE_DIRECTOR = "/director";

    @PostMapping(CREATE_DIRECTOR)
    public DirectorDto createOrUpdateProject(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    )
    {
        directorRepository
                .findByUsername(username)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Username \"%s\" already exists", username));
                });

        directorRepository
                .findByEmail(email)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Email \"%s\" already exists", email));
                });

        String connectionId = GeneratingRandomConnectionId();

        while(directorRepository.findByConnectionId(connectionId).isPresent()){
            connectionId = GeneratingRandomConnectionId();
        }

        int countEmployees = 0;

        DirectorEntity director = directorRepository.saveAndFlush(
                DirectorEntity.builder()
                        .username(username)
                        .email(email)
                        .password(password)
                        .connectionId(connectionId)
                        .countEmployees(countEmployees)
                        .build()
        );
        return directorDtoFactory.createDirectorDto(director);
    }

    private String GeneratingRandomConnectionId() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
