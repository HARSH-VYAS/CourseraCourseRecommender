package edu.sjsu;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course,String> {

    //public void save(int id, String shortName ,String name);

}
