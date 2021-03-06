package com.lambdaschool.liftingweights.services;

import com.lambdaschool.liftingweights.StartHereApplication;
import com.lambdaschool.liftingweights.exceptions.ResourceNotFoundException;
import com.lambdaschool.liftingweights.models.Exercise;
import com.lambdaschool.liftingweights.models.User;
import com.lambdaschool.liftingweights.models.UserWorkout;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartHereApplication.class)
public class UserWorkoutServiceImplTest {

    @Autowired
    private UserWorkoutServiceImpl userworkoutservice;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ExerciseServiceImpl exerciseService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findAllWorkouts() {
        assertEquals(4, userworkoutservice.findAll().size());
    }

    @Test
    public void deleteFound() {
        userworkoutservice.delete(13);
        assertEquals(3, userworkoutservice.findAll().size());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteNotFound() {
        userworkoutservice.delete(100);
        assertEquals(3, userworkoutservice.findAll().size());
    }

    @Test
    public void saveWorkout() {
        User newUser = new User();
        newUser.setUsername("TestUser");
        newUser.setPassword("TestPassword");
        userService.save(newUser);

        UserWorkout newWorkout = new UserWorkout(newUser, "09/25/19", "1:25:00", "1");
        userworkoutservice.saveWorkout(newWorkout, newUser.getUsername());
        assertEquals("09/25/19", userworkoutservice.saveWorkout(newWorkout, newUser.getUsername()).getWorkoutname());;
    }

    @Test
    public void saveExerciseToWorkout() {
        User newUser = new User();
        newUser.setUsername("TestUser11");
        newUser.setPassword("TestPassword1");
        userService.save(newUser);

        UserWorkout newWorkout = new UserWorkout(newUser, "09/26/19", "1:11:11", "09");
        userworkoutservice.saveWorkout(newWorkout, newUser.getUsername());

        Exercise newExercise = new Exercise("PUMPED", "2000 lbs", "3 x 15", "3 min", "GAINS", newWorkout);
        exerciseService.save(newExercise);
        assertEquals("PUMPED", exerciseService.save(newExercise).getExercisename());
    }

    @Test
    public void findById() {
        assertEquals("01/20/19", userworkoutservice.findById(12).getWorkoutname());
    }

    @Test
    public void update() {
        User newUser = new User();
        newUser.setUsername("TestUser1");
        newUser.setPassword("TestPassword1");
        userService.save(newUser);

        UserWorkout newWorkout = new UserWorkout(newUser, "09/26/19", "1:11:11", "09");
        userworkoutservice.saveWorkout(newWorkout, newUser.getUsername());

        newWorkout.setWorkoutname("SUPER PUMPED");
        userworkoutservice.saveWorkout(newWorkout, newUser.getUsername());
        assertEquals("SUPER PUMPED", userworkoutservice.saveWorkout(newWorkout, newUser.getUsername()).getWorkoutname());
    }

    @Test
    public void findAll() {
        assertEquals(5, userworkoutservice.findAll().size());
    }
}