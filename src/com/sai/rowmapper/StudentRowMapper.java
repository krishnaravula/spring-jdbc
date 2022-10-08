package com.sai.rowmapper;

import com.sai.api.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        Student newStudent = new Student();
        newStudent.setRollNO(rs.getInt("ROLL_NO"));
        newStudent.setName(rs.getString("STUDENT_NAME"));
        newStudent.setAddress(rs.getString("STUDENT_ADDRESS"));

        System.out.println("Map row");
        return newStudent;
    }
}
