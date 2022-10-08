package com.sai.dao;

import com.sai.api.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("studentDaoHelper")
public class StudentDAOHelper {

    @Autowired
    private StudentDao studentDaoImpl;

    public void setUpStudentTable(){

        Student student1 = new Student();
        student1.setRollNO(33);
        student1.setName("sai");
        student1.setAddress("Nizamabad");


        Student student2 = new Student();
        student2.setRollNO(333);
        student2.setName("saiK");
        student2.setAddress("Texas");

        Student student3 = new Student();
        student3.setRollNO(111);
        student3.setName("Sandy");
        student3.setAddress("Nizamabad");

        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        studentDaoImpl.updateStudent(studentList);

    }


}
