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

    /**
     *
     * MongoDB 등록
     *
     * @param sample Sample 객체
     */
    public void saveSample(Sample sample) {
        commonMongoDBRepository.create(sample);
    }

    /**
     *
     * MongoDB 리스트 조회
     *
     * @return List<Sample>
     */
    public List<Sample> findSampleAll() {
        return commonMongoDBRepository.findAll();
    }

    /**
     *
     * MongoDB 리스트 조회
     *
     * @return List<Sample>
     */
    public Sample findSampleById(String id) {
        return commonMongoDBRepository.findById(id);
    }

    /**
     *
     * MongoDB 업데이트
     *
     * @param sample
     */
    public void updateSample(Sample sample) {
        commonMongoDBRepository.update(sample);
    }

    /**
     *
     * MongoDB 삭제
     *
     * @param id
     */
    public void deleteSample(String id) {
        Sample sample = commonMongoDBRepository.findById(id);
        commonMongoDBRepository.delete(sample);
    }

    /**
     *
     * MySql 전체 조회
     *
     * @param page 페이지
     * @return List<User>
     */
    public List<User> findUserAll(int page) {
        return commonMysqlRepository.findAll(page);
    }

    /**
     *
     * MySql 조회
     *
     * @param id ID
     * @return User 객체
     */
    public User findUserById(int id) {
        return commonMysqlRepository.finUserById(id);
    }

    /**
     *
     * MySql 저장
     *
     * @param user User 객체
     */
    public void saveUsers(User user) {
        commonMysqlRepository.saveUser(user);
    }

    /**
     *
     * MySql 업데이트
     *
     * @param user User 객체
     */
    public void updateUser(User user) {
        commonMysqlRepository.updateUser(user);
    }

    /**
     *
     * MySql 삭제
     *
     * @param id ID
     */
    public void deleteUser(int id) {
        User user = commonMysqlRepository.finUserById(id);

        commonMysqlRepository.deleteUser(user);
    }
}
