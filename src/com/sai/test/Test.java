package com.sai.test;

import com.sai.api.Student;
import com.sai.dao.StudentDAOHelper;
import com.sai.dao.StudentDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");

        Student newStudent1 = new Student();
        newStudent1.setRollNO(20);
        newStudent1.setAddress("kphb");
        newStudent1.setName("Sandy");

        StudentDaoImpl studentDao = applicationContext.getBean("studentDao", StudentDaoImpl.class);


        StudentDAOHelper studentDAOHelper = applicationContext.getBean("studentDaoHelper", StudentDAOHelper.class);
//        studentDao.insert(newStudent1);
        studentDAOHelper.setUpStudentTable();

        List<Student> students = studentDao.findAllStudents();

//        for (Student s : students) {
//            System.out.println("Student{" +
//                    "roolNo=" + s.getRollNO() +
//                    ", name='" + s.getName() + '\'' +
//                    ", address='" + s.getAddress() + '\'' +
//                    '}');
//        }


//        Map<String, List<String>> groupBYAddress = studentDao.groupByStudentAddress();
//        System.out.println(groupBYAddress);


//        System.out.println(studentDao.findStudentByRollNo(1));
//
//        boolean isDeleted = studentDao.deleteRecordByRollNo(1);
//        if(isDeleted){
//            System.out.println("Row is deleted");
//        }





    }
}
