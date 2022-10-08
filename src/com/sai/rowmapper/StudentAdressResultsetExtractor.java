package com.sai.rowmapper;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAdressResultsetExtractor implements ResultSetExtractor<Map<String, List<String>>> {
    @Override
    public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {

        Map<String, List<String>> studentTable = new HashMap<>();

        while (rs.next()){
            String studentName = rs.getString("STUDENT_NAME");
            String studentAddress = rs.getString("STUDENT_ADDRESS");
            List<String> studentNameList = studentTable.get(studentAddress);

            if(studentNameList == null){
                ArrayList<String> newStudentList = new ArrayList<>();
                newStudentList.add(studentName);

                studentTable.put(studentAddress,newStudentList);
            }else {
                studentNameList.add(studentName);
            }
        }



        return studentTable;
    }
}
