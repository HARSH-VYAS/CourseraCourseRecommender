package edu.sjsu;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by HARMIT on 5/11/2015.
 */
public interface QuoraRepository extends MongoRepository<Quora, String>
{

}

