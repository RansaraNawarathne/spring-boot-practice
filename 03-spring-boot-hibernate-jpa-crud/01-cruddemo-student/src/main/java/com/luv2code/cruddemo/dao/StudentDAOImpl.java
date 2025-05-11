package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{
    // define field for entity manager
    EntityManager entityManager;

    // inject entity manager using construction injection
    @Autowired
    public StudentDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student find(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // Return query result
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //  Create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName = :theLastName", Student.class);

        // Set query parameters
        theQuery.setParameter("theLastName", theLastName);

        // Return query results
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student) {
        entityManager.merge(student);
    }
}
