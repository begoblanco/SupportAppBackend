package dev.bego.supportapp.models;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RequestTest {

    private Request request;
    private Request request2;

    @BeforeEach
    void setUp() {
        request = new Request();
        request.setId(1L);
        request.setRequesterName("Miku Hatsune");
        request.setTopic("Topic 1");
        request.setDescription("Description 1");
        request.setRequestDate(LocalDateTime.of(2024, 7, 12, 10, 0));

        request2 = new Request();
    }

    @Test
    void testRequestHas5Attributes() {
        Field[] requestFields = request.getClass().getDeclaredFields();
        assertThat(requestFields.length, is(5));
    }

    @Test
    void testRequestGetters() {
        assertThat(request.getId(), is(1L));
        assertThat(request.getRequesterName(), is("Miku Hatsune"));
        assertThat(request.getTopic(), is("Topic 1"));
        assertThat(request.getDescription(), is("Description 1"));
        assertThat(request.getRequestDate(), is(LocalDateTime.of(2024, 7, 12, 10, 0)));
    }

    @Test
    void testRequestSetters() {
        request2.setId(2L);
        request2.setRequesterName("Bego Blanco");
        request2.setTopic("Topic 2");
        request2.setDescription("Description 2");
        request2.setRequestDate(LocalDateTime.of(2024, 2, 7, 12, 0));

        assertThat(request2.getId(), is(2L));
        assertThat(request2.getRequesterName(), is("Bego Blanco"));
        assertThat(request2.getTopic(), is("Topic 2"));
        assertThat(request2.getDescription(), is("Description 2"));
        assertThat(request2.getRequestDate(), is(LocalDateTime.of(2024, 2, 7, 12, 0)));
    }
}
