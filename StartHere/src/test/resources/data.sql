DELETE
FROM exercises;

DELETE
FROM userworkouts;

DELETE
FROM users;

INSERT INTO users(userid, username, password)
VALUES (1, 'adminn', 'password'),
    (2, 'fred', '123');

INSERT INTO  userworkouts(workoutid, workoutname, date, workoutlength, user)
VALUES (12 , '01/20/19', '', '1:20:00', 1),
       (13 , '10/21/19', '', '1:35:00', 2);

INSERT INTO exercises
VALUES (100, 'abs', '15lbs', '3x15', '2 min', 'abdominal', 1 , 12),
       (101, 'chest', '20lbs', '4x15', '3 min', 'abdominal', 2 , 13);







alter sequence hibernate_sequence restart with 20;