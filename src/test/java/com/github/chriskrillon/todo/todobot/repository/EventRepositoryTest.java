package com.github.chriskrillon.todo.todobot.repository;

import com.github.chriskrillon.todo.todobot.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest
@TestPropertySource(locations = "classpath:application.properties")
public class EventRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @DynamicPropertySource
    static void configureProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Autowired
    private EventRepository eventRepository;

    @Test
    void testSaveAndFindEvent() {
        Event event = new Event();
        event.setUserId("user123");
        event.setText("Test Event");
        event.setLocalDateTime(LocalDateTime.now());

        Event savedEvent = eventRepository.save(event);
        
        Optional<Event> retrievedEvent = eventRepository.findById(savedEvent.getId());

        assertThat(retrievedEvent).isPresent();
        assertThat(retrievedEvent.get().getText()).isEqualTo("Test Event");
    }
}