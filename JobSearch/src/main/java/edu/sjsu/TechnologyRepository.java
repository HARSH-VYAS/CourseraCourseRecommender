package edu.sjsu;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TechnologyRepository extends MongoRepository<Technology, String> {

}