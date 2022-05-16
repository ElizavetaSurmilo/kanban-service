package com.project.kanbanservice.conrollers;


import com.project.kanbanservice.dto.AckDto;
import com.project.kanbanservice.dto.DirectorDto;
import com.project.kanbanservice.entity.DirectorEntity;
import com.project.kanbanservice.exceptions.BadRequestException;
import com.project.kanbanservice.exceptions.NotFoundException;
import com.project.kanbanservice.factories.DirectorDtoFactory;
import com.project.kanbanservice.repositories.DirectorRepository;
import com.project.kanbanservice.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Transactional
@RestController
public class DirectorController {

    private final DirectorRepository directorRepository;
    private final DirectorDtoFactory directorDtoFactory;

    private final EmployeeRepository employeeRepository;

    public static final String FETCH_DIRECTORS = "/directors";
    public static final String GET_ALL_DIRECTORS = "/all/directors";
    public static final String GET_DIRECTOR_BY_ID = "/director/{director_id}";
    public static final String CREATE_DIRECTOR = "/director";
    public static final String EDIT_DIRECTOR = "/director/{director_id}";
    public static final String DELETE_DIRECTOR = "/director/{director_id}";


    @GetMapping(FETCH_DIRECTORS)
    public List<DirectorDto> fetchDirectors(
            @RequestParam(value = "director_username", required = false) Optional<String> optionalDirectorUsername
    ){
        optionalDirectorUsername = optionalDirectorUsername.filter(username -> !username.trim().isEmpty());

        Stream<DirectorEntity> directorStream = optionalDirectorUsername
                .map(directorRepository::streamAllByUsernameContainingIgnoreCase)
                .orElseGet(directorRepository::streamAllBy);

        return directorStream
                .map(directorDtoFactory::createDirectorDto)
                .collect(Collectors.toList());

    }

    @GetMapping(GET_ALL_DIRECTORS)
    public List<DirectorDto> getAllDirectors( ){

        Stream<DirectorEntity> directorStream = directorRepository.streamAllBy();

        return directorStream
                .map(directorDtoFactory::createDirectorDto)
                .collect(Collectors.toList());

    }

    @GetMapping(GET_DIRECTOR_BY_ID)
    public DirectorDto getDirectorById( @PathVariable("director_id") Long directorId){

        final DirectorEntity director = directorRepository
                .findById(directorId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Director with %s doesn't exist.", directorId
                                )
                        )
                );
        return directorDtoFactory.createDirectorDto(director);
    }

    @PostMapping(CREATE_DIRECTOR)
    public DirectorDto createProject(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String password
    )
    {

        if (username.trim().isEmpty()){
            throw new BadRequestException("Username can't be empty");
        }
        if (email.trim().isEmpty()){
            throw new BadRequestException("Email can't be empty");
        }
        if (password.trim().isEmpty()){
            throw new BadRequestException("Password can't be empty");
        }

        directorRepository
                .findByUsername(username)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Username %s already exists", username));
                });

        directorRepository
                .findByEmail(email)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Email %s already exists", email));
                });

        employeeRepository
                .findByUsername(username)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Username %s already exists", username));
                });

        employeeRepository
                .findByEmail(email)
                .ifPresent(director -> {
                    throw new BadRequestException(String.format("Email %s already exists", email));
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

    @PatchMapping(EDIT_DIRECTOR)
    public DirectorDto updateDirector(
            @PathVariable("director_id") Long directorId,
            @RequestParam(value = "director_username", required = false) Optional<String> optionalDirectorUsername,
            @RequestParam(value = "director_email", required = false) Optional<String> optionalDirectorEmail,
            @RequestParam(value = "director_password", required = false) Optional<String> optionalDirectorPassword
    )
    {

        if (!optionalDirectorUsername.isPresent() && !optionalDirectorEmail.isPresent() && !optionalDirectorPassword.isPresent()){
            throw new BadRequestException("The value cannot be empty");
        }

        final DirectorEntity director = directorRepository
                .findById(directorId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Director with %s doesn't exist.", directorId
                                )
                        )
                );

        if (optionalDirectorUsername.isPresent()){
            optionalDirectorUsername
                    .ifPresent(username -> {
                        directorRepository
                                .findByUsername(username)
                                .ifPresent(anotherDirector -> {
                                    throw new BadRequestException(String.format("Username %s already exists", username));
                                });
                    });

            optionalDirectorUsername
                    .ifPresent(username -> {
                        employeeRepository
                                .findByUsername(username)
                                .ifPresent(anotherEmployee -> {
                                    throw new BadRequestException(String.format("Username %s already exists", username));
                                });
                        director.setUsername(username);
                    });

        } else if (optionalDirectorEmail.isPresent()){
            optionalDirectorEmail
                    .ifPresent(email -> {
                        directorRepository
                                .findByEmail(email)
                                .ifPresent(anotherDirector -> {
                                    throw new BadRequestException(String.format("Email %s already exists", email));
                                });
                    });

            optionalDirectorEmail
                    .ifPresent(email -> {
                        employeeRepository
                                .findByEmail(email)
                                .ifPresent(anotherEmployee -> {
                                    throw new BadRequestException(String.format("Email %s already exists", email));
                                });
                        director.setEmail(email);
                    });

        } else if (optionalDirectorPassword.isPresent()){
            optionalDirectorPassword
                    .ifPresent(password -> {
                        if (Objects.equals(director.getPassword(), password)){
                            throw new BadRequestException(String.format("The new password matches the current one"));
                        }
                        director.setPassword(password);
                    });
        }

        final DirectorEntity saveDirector = directorRepository.saveAndFlush(director);

        return directorDtoFactory.createDirectorDto(saveDirector);
    }

    @DeleteMapping(DELETE_DIRECTOR)
    public AckDto deleteDirector(@PathVariable("director_id") Long directorId){
        DirectorEntity director = directorRepository
                .findById(directorId)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Director with %s doesn't exist.", directorId
                                )
                        )
                );

        directorRepository.deleteById(directorId);
        return AckDto.makeDefault(true);
    }

    // Генерация рандомной строки
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
