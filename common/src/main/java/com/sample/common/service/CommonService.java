package com.sample.common.service;

import com.sample.common.entity.Sample;
import com.sample.common.entity.User;
import com.sample.common.repository.CommonMongoDBRepository;
import com.sample.common.repository.CommonMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommonService {

    @Autowired
    private CommonMongoDBRepository commonMongoDBRepository;

    @Autowired
    private CommonMysqlRepository commonMysqlRepository;

    public String sayWow() {
        return "WOW!!";
    }

    public List<Sample> findSampleAll() {
        return commonMongoDBRepository.findAll();
    }

    public void createSample(Sample sample) {
        commonMongoDBRepository.create(sample);
    }

    @Transactional(readOnly = true)
    public Iterable<User> findUserAll() {
        return commonMysqlRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findUserById(int id) {
        return commonMysqlRepository.findBy(id);
    }

    @Transactional
    public User saveUserAll(User user) {
        return commonMysqlRepository.save(user);
    }
}
