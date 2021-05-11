package com.epam.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.exception.PasswordDoesntMatchException;
import com.epam.training.ticketservice.exception.UserNotFoundException;
import com.epam.training.ticketservice.presentation.cli.handler.AdminCommandHandler;
import com.epam.training.ticketservice.repository.UserRepository;
import com.epam.training.ticketservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminCommandHandlerTest {

    @InjectMocks
    private AdminCommandHandler underTest;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void signInPrivilegedShouldSignInPrivilegedUser() {
        // Given
        String username = "test";
        String password = "test";

        //When
        String actual = underTest.signInPrivileged(username,password);

        //Then
        Assertions.assertNull(actual);

    }

    @Test
    public void describeAccountShouldReturnNotSignedInWhenNotSignedIn(){
        //When
        when(userService.isUserLoggedIn()).thenReturn(false);
        String description = underTest.describeAccount();

        //Then
        Assertions.assertEquals("You are not signed in",description);
    }

    @Test
    public void describeAccountShouldReturnDescriptionOfAccountWhenSignedIn(){
        //Given
        UserProjection user = new UserProjection("admin", "admin", "ADMIN");

        //When
        when(userService.isUserLoggedIn()).thenReturn(true);
        when(userService.getLoggedInUser()).thenReturn(user);

        String actual = underTest.describeAccount();

        //Then
        Assertions.assertEquals("Signed in with privileged account '"
            + user.getUsername() + "'", actual);
    }

    @Test
    public void logOutShouldLogUserOut(){
        //When
        String actual = underTest.signOut();

        //Then
        Assertions.assertEquals("Signing out",actual);

    }


}
