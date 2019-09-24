package com.lambdaschool.liftingweights.services;

import com.lambdaschool.liftingweights.exceptions.ResourceNotFoundException;
import com.lambdaschool.liftingweights.models.Exercise;
import com.lambdaschool.liftingweights.models.User;
import com.lambdaschool.liftingweights.models.UserWorkout;
import com.lambdaschool.liftingweights.repository.ExerciseRepository;
import com.lambdaschool.liftingweights.repository.UserRepository;
import com.lambdaschool.liftingweights.repository.UserWorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userWorkoutService")
public class UserWorkoutServiceImpl implements UserWorkoutService {
    @Autowired
    UserWorkoutRepository userworkoutrepos;

    @Autowired
    ExerciseRepository exerciserepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<UserWorkout> findAllWorkouts(Pageable pageable) {
        List<UserWorkout> myWorkouts = new ArrayList<>();
        userworkoutrepos.findAll(pageable).iterator().forEachRemaining(myWorkouts::add);
        return myWorkouts;
    }

    @Override
    public UserWorkout findWorkoutByName(String name) {

        return null;
    }

    @Override
    public void delete(long workoutid) {
        if (userworkoutrepos.findById(workoutid).isPresent()) {
            userworkoutrepos.deleteById(workoutid);
        } else {
            throw new ResourceNotFoundException("Workout id " + workoutid + " not found.");
        }

    }

    @Transactional
    @Override
    public UserWorkout saveWorkout(UserWorkout workout, String username) {
        UserWorkout uw = new UserWorkout();

        User user = userrepos.findByUsername(username);
        uw.setUserid(user);
        uw.setWorkoutname(workout.getWorkoutname());
        uw.setWorkoutlength(workout.getWorkoutlength());


        return  userworkoutrepos.save(uw);
    }

    @Override
    public UserWorkout saveExerciseToWorkout(long workoutid, Exercise exercise) {
        UserWorkout uw = userworkoutrepos.findById(workoutid)
                .orElseThrow(() -> new ResourceNotFoundException("Workout id" + workoutid + " not found!"));
        Exercise ex = new Exercise(exercise.getExercisename(), exercise.getWeightlifted(), exercise.getReps(), exercise.getRestperiod(), exercise.getExerciseregion());

        uw.getExercises().add(ex);
        return userworkoutrepos.save(uw);
    }


    @Override
    public UserWorkout findById(long workoutid) throws ResourceNotFoundException {
        return userworkoutrepos.findById(workoutid)
                .orElseThrow(() -> new ResourceNotFoundException("Workout id" + workoutid + "not Found!"));
    }


    @Override
    public UserWorkout update(UserWorkout customer, long id) {
        return null;
    }


}
