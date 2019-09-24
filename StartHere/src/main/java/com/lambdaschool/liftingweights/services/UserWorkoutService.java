package com.lambdaschool.liftingweights.services;

import com.lambdaschool.liftingweights.models.Exercise;
import com.lambdaschool.liftingweights.models.UserWorkout;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserWorkoutService {

    List<UserWorkout> findAll(Pageable pageable);

    UserWorkout findWorkoutByName(String name);

    UserWorkout findById(long id);

    void delete(long workoutid);

    UserWorkout saveWorkout(UserWorkout workout);

    UserWorkout saveExerciseToWorkout(long workoutid, Exercise exercise);

    UserWorkout update(UserWorkout customer, long id);

}