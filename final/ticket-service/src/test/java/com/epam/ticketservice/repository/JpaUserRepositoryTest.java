package com.epam.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.repository.impl.JpaUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaUserRepositoryTest {

    @InjectMocks
    private JpaUserRepository underTest;

    @Mock
    private UserDao userDao;

    @Test
    public void testGetUserByUsernameShouldGetSingleUserByUserName() {
        //Given
        String username = "test";
        String password = "test";
        UserProjection userToTest = new UserProjection(username, password, "ADMIN");
        //When
        when(userDao.findByUsername(username)).thenReturn(Optional.of(userToTest));
        UserProjection actualUser = underTest.getUserByUsername(username);

        //Then
        Assertions.assertEquals(userToTest,actualUser);
    }
}
