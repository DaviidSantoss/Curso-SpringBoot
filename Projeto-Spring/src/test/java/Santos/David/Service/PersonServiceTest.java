package Santos.David.Service;

import Santos.David.Exception.RequiredObjectIsNullException;
import Santos.David.Service.repository.PersonRepository;
import Santos.David.data.dto.PersonDTO;
import Santos.David.mapper.ObjectMapper;
import Santos.David.model.Person;
import Santos.David.unitests.mapper.mocks.MockPerson;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        input = new MockPerson();
    }

    @Test
    void findById() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(entity));

        try (MockedStatic<ObjectMapper> mapper = mockStatic(ObjectMapper.class)) {

            mapper.when(() ->
                            ObjectMapper.parseObject(any(Person.class), eq(PersonDTO.class)))
                    .thenReturn(dto);

            var result = service.findById(1L);

            assertNotNull(result);
            assertEquals(1L, result.getId());
            assertEquals("First Name Test1", result.getFirstName());
        }
    }

    @Test
    void create() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(repository.save(any(Person.class)))
                .thenReturn(entity);

        try (MockedStatic<ObjectMapper> mapper = mockStatic(ObjectMapper.class)) {

            mapper.when(() ->
                            ObjectMapper.parseObject(any(PersonDTO.class), eq(Person.class)))
                    .thenReturn(entity);

            mapper.when(() ->
                            ObjectMapper.parseObject(any(Person.class), eq(PersonDTO.class)))
                    .thenReturn(dto);

            var result = service.create(dto);

            assertNotNull(result);
            assertEquals(1L, result.getId());
        }
    }

    @Test
    void update() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);
        dto.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(entity));

        when(repository.save(any(Person.class)))
                .thenReturn(entity);

        try (MockedStatic<ObjectMapper> mapper = mockStatic(ObjectMapper.class)) {

            mapper.when(() ->
                            ObjectMapper.parseObject(any(Person.class), eq(PersonDTO.class)))
                    .thenReturn(dto);

            var result = service.update(dto);

            assertNotNull(result);
            assertEquals(1L, result.getId());
        }
    }

    @Test
    void delete() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(entity));

        service.delete(1L);

        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).delete(entity);
    }

    @Test
    @Disabled("Reason: Still Under Devoelopment")
    void findAll() {

        List<Person> entities = input.mockEntityList();

        when(repository.findAll())
                .thenReturn(entities);

        List<PersonDTO> people = new ArrayList<>();

        try (MockedStatic<ObjectMapper> mapper = mockStatic(ObjectMapper.class)) {

            mapper.when(() ->
                            ObjectMapper.parseObject(any(Person.class), eq(PersonDTO.class)))
                    .thenAnswer(invocation -> {

                        Person source = invocation.getArgument(0);

                        PersonDTO dto = new PersonDTO();

                        dto.setId(source.getId());
                        dto.setFirstName(source.getFirstName());
                        dto.setLastName(source.getLastName());
                        dto.setAddress(source.getAddress());
                        dto.setGender(source.getGender());

                        return dto;
                    });
////
////            Pageable pageable;
////            var result = service.findAll(pageable);
//
//            assertNotNull(result);
//            assertEquals(14, result.size());
        }
    }

    @Test
    void testCreateWithNullPerson() {

        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> service.create(null)
        );

        assertTrue(exception.getMessage()
                .contains("It is not allowed to persist a null object"));
    }

    @Test
    void testUpdateWithNullPerson() {

        Exception exception = assertThrows(
                RequiredObjectIsNullException.class,
                () -> service.update(null)
        );

        assertTrue(exception.getMessage()
                .contains("It is not allowed to persist a null object"));
    }
}