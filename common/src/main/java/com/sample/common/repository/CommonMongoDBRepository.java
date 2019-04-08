package com.sample.common.repository;

import com.sample.common.entity.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonMongoDBRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void create(Sample sample) {
        mongoTemplate.insert(sample);
    }

    public List<Sample> findAll() {
        return (List<Sample>) mongoTemplate.findAll(Sample.class);
    }

    public Sample findById(String id) {
        return mongoTemplate.findById(id, Sample.class);
    }

    public void update(Sample sample) {
        mongoTemplate.save(sample);
    }

    public void delete(Sample sample) {
        mongoTemplate.remove(sample);
    }


}
