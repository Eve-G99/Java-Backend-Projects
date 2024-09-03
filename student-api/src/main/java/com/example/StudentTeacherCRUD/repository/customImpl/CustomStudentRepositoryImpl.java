package com.example.StudentTeacherCRUD.repository.customImpl;

import com.example.StudentTeacherCRUD.entity.Student;
import com.example.StudentTeacherCRUD.entity.Teacher;
import com.example.StudentTeacherCRUD.repository.custom.CustomStudentRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> findStudentsWithTeachersWhereStudentIdGreaterThanTen() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        Join<Student, Teacher> teacher = student.join("teachers");

        cq.select(student)
                .where(cb.greaterThan(student.get("id"), 10));
        TypedQuery<Student> query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentsByName(String name) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT b FROM Student b WHERE b.name = :name", Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @Override
    public List<Student> findStudentsByTeacherName(String teacherName) {
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT b FROM Student b JOIN StudentTeacher ba ON b.id = ba.studentId " +
                        "JOIN Teacher a ON ba.teacherId = a.id WHERE a.name = :teacherName", Student.class);
        query.setParameter("teacherName", teacherName);
        return query.getResultList();
    }

}
