package com.sample.common.service;

import com.sample.common.entity.Sample;
import com.sample.common.entity.User;
import com.sample.common.repository.CommonMongoDBRepository;
import com.sample.common.repository.CommonMysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public Iterable<User> findUserAll(int page) {
        return commonMysqlRepository.findAll(page);
    }

    public User findUserById(int id) {
        return commonMysqlRepository.finUserById(id);
    }

    public void saveUsers(User user) {
        commonMysqlRepository.saveUser(user);
    }

    public void updateUser(User user) {
        commonMysqlRepository.updateUser(user);
    }

    public void deleteUser(int id) {
        User user = commonMysqlRepository.finUserById(id);

        commonMysqlRepository.deleteUser(user);
    }
}
