package com.sample.common.repository;

import com.sample.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommonMysqlRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        return entityManager.createQuery("SELECT a FROM user a").getResultList();
    }

    public User findBy(int id) {
        return entityManager.find(User.class, id);
    }

    public User save(User user) {
        return entityManager.merge(user);
    }
}
