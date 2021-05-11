package com.epam.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.exception.PasswordDoesntMatchException;
import com.epam.training.ticketservice.exception.UserNotFoundException;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testLoginShouldLogInTheUser() throws UserNotFoundException, PasswordDoesntMatchException {
        // Given
        String username = "test";
        String password = "test";
        UserProjection user = new UserProjection(username, password, "ADMIN");

        // When
        when(userRepository.getUserByUsername(username)).thenReturn(user);
        underTest.login(username, password);

        // Then
        Assertions.assertEquals(user, underTest.getLoggedInUser());
        verifyNoMoreInteractions(userRepository);
    }
}
