package com.sample.common.repository;

import com.sample.common.entity.User;
import java.util.Optional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommonMysqlRepository extends CommonMysqlAbstractRepository{

    public int getUserTotalSize() {
        String countQ = "SELECT count (u.id) FROM User u";
        Query countQuery = getSession().createQuery(countQ);
        Long countResults = (Long) countQuery.uniqueResult();

        return Optional.ofNullable(countResults).orElse(0l).intValue();
    }

    public List<User> findAll() {
        Criteria criteria = getSession().createCriteria(User.class);
        return (List<User>) criteria.list();
    }

    public List<User> findAll(int page) {
        // 페이징 사이트를 Set
        int pageSize = 15;
        Criteria criteria = getSession().createCriteria(User.class);
        criteria.setFirstResult(page*pageSize);
        criteria.setMaxResults(pageSize);
        return  criteria.list();
    };


    public User finUserById(int id) {
        Criteria  criteria = getSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("id", id));

        return (User) criteria.uniqueResult();
    }

    public void saveUser(User user) {
        getSession().persist(user);
    }

    public void updateUser(User user) {
        getSession().update(user);
    }

    public void deleteUser(User user) {
        getSession().delete(user);
    }

}
