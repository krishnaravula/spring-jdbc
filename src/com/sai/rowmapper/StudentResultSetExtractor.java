package com.sai.rowmapper;

import com.sai.api.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentResultSetExtractor implements ResultSetExtractor<List<Student>> {

    @Override
    public List<Student> extractData(ResultSet rs) throws SQLException, DataAccessException {

        List<Student> studentList = new ArrayList<Student>();
        while (rs.next()) {
            Student newStudent = new Student();
            newStudent.setRollNO(rs.getInt("ROLL_NO"));
            newStudent.setName(rs.getString("STUDENT_NAME"));
            newStudent.setAddress(rs.getString("STUDENT_ADDRESS"));
            studentList.add(newStudent);
        }

        return studentList;
    }
}
