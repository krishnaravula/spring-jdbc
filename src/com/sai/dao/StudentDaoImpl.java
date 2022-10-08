package com.sai.dao;

import com.sai.api.Student;
import com.sai.rowmapper.StudentAdressResultsetExtractor;
import com.sai.rowmapper.StudentResultSetExtractor;
import com.sai.rowmapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {

   @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(Student student) {
        String sql = "INSERT INTO STUDENTSE VALUES(?,?,?)";

        Object[] arg= {student.getRollNO(),student.getName(),student.getAddress()};
        int noOfRowsInserted = jdbcTemplate.update(sql,arg);
        System.out.println("No of row inserted is "+ noOfRowsInserted);

    }

    @Override
    public void insert(List<Student> students) {

        String sql = "INSERT INTO STUDENTSE(ROLL_NO,STUDENT_NAME,STUDENT_ADDRESS) VALUES(?,?,?)";

        ArrayList<Object[]> sqlArgs = new ArrayList<>();

        for (Student tempStudent: students){
            Object[] studentData = {tempStudent.getRollNO(),tempStudent.getName(),tempStudent.getAddress()};
            sqlArgs.add(studentData);

        }
        jdbcTemplate.batchUpdate(sql,sqlArgs);

        System.out.println("Batch update completed");


    }

    @Override
    public boolean deleteRecordByRollNo(int rollNo) {
        String sql= "DELETE FROM STUDENTSE WHERE ROLL_NO = ?";
        int noOfRowDeleted = jdbcTemplate.update(sql,rollNo);
        return (noOfRowDeleted == 1);
    }

    @Override
    public List<Student> findAllStudents() {

        String selectSql = "SELECT * FROM STUDENTSE";
//        List<Student> studentList =  jdbcTemplate.query(selectSql,new StudentRowMapper());
        List<Student> studentList =  jdbcTemplate.query(selectSql,new StudentResultSetExtractor());
        return studentList;
    }

    @Override
    public Student findStudentByRollNo(int rollNo) {
        String selectSql = "SELECT ROLL_NO AS rollNo,STUDENT_NAME as name,STUDENT_ADDRESS as address FROM STUDENTSE WHERE ROLL_NO = ?";
        Student student= jdbcTemplate.queryForObject(selectSql,new BeanPropertyRowMapper<Student>(Student.class),rollNo);
        return student;

    }

    @Override
    public Map<String, List<String>> groupByStudentAddress() {
        String sql = "SELECT * FROM STUDENTSE";
        Map<String, List<String>> query = jdbcTemplate.query(sql, new StudentAdressResultsetExtractor());
        return query;
    }

    @Override
    public int updateStudent(Student student) {
        return 0;
    }

    @Override
    public int updateStudent(List<Student> students) {
       String sql = "UPDATE STUDENTSE SET STUDENT_ADDRESS = ? WHERE ROLL_NO = ?";
        int[] batchvalue = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, students.get(i).getAddress());
                ps.setInt(2, students.get(i).getRollNO());

                System.out.println("Inside set values");
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }
        });


        int updatedRowCount = 0;
        for (int i=0;i<batchvalue.length;i++){
            if (batchvalue[i] ==1){
                updatedRowCount++;
            }
        }
        return updatedRowCount;
    }


    public DataSource getDataSource(){


        String url = "jdbc:mysql://127.0.0.1:3306/rsk";
        String userName = "root";
        String password = "Acadia@123";

        DataSource dataSource = new DriverManagerDataSource(url,userName,password);
        return dataSource;
    }
}
