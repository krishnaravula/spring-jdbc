package com.sai.dao;

import com.sai.api.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {

    void insert(Student student);

    void insert(List<Student> students);

    boolean deleteRecordByRollNo(int rollNo);

    List<Student> findAllStudents();

    Student findStudentByRollNo(int rollno);

    Map<String,List<String>> groupByStudentAddress();

    int updateStudent(Student student);

    int updateStudent(List<Student> students);



}
