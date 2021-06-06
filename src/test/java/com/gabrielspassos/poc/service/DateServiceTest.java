package com.gabrielspassos.poc.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DateServiceTest {

    @Test
    public void shouldReturnDate() {
        String formattedDate = DateService.formatDate("31102015");

        assertNotNull(formattedDate);
        assertEquals("31/10/2015", formattedDate);
    }

}