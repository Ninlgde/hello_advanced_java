package com.ninlgde.db.mybatis.mapper;

import com.ninlgde.db.mybatis.entity.Student;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * StudentMapper Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>Mar 9, 2020</pre>
 */
public class StudentMapperTest {

    private StudentMapper mapper;

    @Before
    public void before() throws Exception {
        mapper = new StudentMapper();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(Student student)
     */
    @Test
    public void testAdd() throws Exception {
        mapper.add(new Student(4, "1mcc", 2000.0));
        mapper.add(new Student(5, "1mcc2", 20000.0));
        mapper.add(new Student(6, "1mcc3", 200000.0));
        mapper.add(new Student(7, "2mcc", 3000.0));
        mapper.add(new Student(8, "2mcc2", 30000.0));
        mapper.add(new Student(9, "2mcc3", 300000.0));
    }

    /**
     * Method: findById(int id)
     */
    @Test
    public void testFindById() throws Exception {
        Student student = mapper.findById(1);
        System.out.println(student.getName());
    }

    /**
     * Method: findAll()
     */
    @Test
    public void testFindAll() throws Exception {
        List<Student> students = mapper.findAll();
        System.out.println(students.size());
    }

    /**
     * Method: delete(int id)
     */
    @Test
    public void testDelete() throws Exception {
        mapper.delete(1);
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = mapper.findById(2);
        student.setName(student.getName() + "fixed");
        student.setSal(student.getSal() * 2);
        mapper.update(student);
    }

    @Test
    public void testPagination() throws Exception {
        List<Student> students = mapper.pagination(0, 3);
        for (Student student : students) {
            System.out.println(student.getId());
        }
    }

    @Test
    public void testFindByCondition() throws Exception {
        List<Student> students = mapper.findByCondition(null, 90000D);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testUpdateByConditions() throws Exception {
        mapper.updateByConditions(2,"haha",500D);
    }

    @Test
    public void testDeleteByConditions() throws Exception {
        mapper.deleteByConditions(2,3,4);
    }

    @Test
    public void testInsertByConditions() throws Exception {
        mapper.insertByConditions(new Student(55, null, null));//name和sal为空
        mapper.insertByConditions(new Student(66, "haxi", null));//sal为空
        mapper.insertByConditions(new Student(77, null, 3999d));//name为空
    }
} 
