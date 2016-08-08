/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.dao;

import customorm.DBConfig;
import customorm.model.Student;
import customorm.model.Teacher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junaid.ahmad
 */
public class TeacherDAO implements BaseDAO{
    DBConfig dBConfig;
    Connection conn = null;
    public TeacherDAO(){
        dBConfig = DBConfig.getInstance();
        //conn = dBConfig.configureDB();
    }
    
    @Override
    public void insert(Object obj) {
        PreparedStatement preparedStmt = null;
        Teacher s = (Teacher)obj;
        StringBuilder query1 = new StringBuilder();
        
        try {
            if(conn == null){
                conn = dBConfig.configureDB();
            }
            conn.setAutoCommit(false);
            // the mysql insert statement
            String query = " insert into teacher (name)"
                    + " values (?)";
            query1.append("select * from student where id in (");
            String query2 = " insert into teacher_student "
                    + "(TEACHER_ID, STUDENT_ID) values (?, ?)";
        
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, s.getName());          
            // execute the preparedstatement
            preparedStmt.execute();
            
            ResultSet r = preparedStmt.getGeneratedKeys();
            r.next();
            
            if(s.getStudents().get(0).getId() != 0){
                //check whether students exist or not
                for(int i = 0; i < s.getStudents().size(); i++){
                    query1.append(s.getStudents().get(i).getId()).append(",");
                }
                query1.deleteCharAt(query1.length() -1);
                query1.append(")");
                // create the mysql select preparedstatement
                preparedStmt = conn.prepareStatement(query1.toString());
                // execute the preparedstatement
                ResultSet rs = preparedStmt.executeQuery();

                //Adding data relation in teacher_student table
                preparedStmt = conn.prepareStatement(query2);
                while (rs.next()) {
                    //id of teacher adding in teacher_student
                    preparedStmt.setInt(1, r.getInt(1));
                    //id of student adding in teacher_table
                    preparedStmt.setInt(2, rs.getInt(1)); 
                    preparedStmt.execute();
                }
            }
            
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null) {
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
        }finally{
            try{
                if(preparedStmt != null){
                    preparedStmt.close();
                }
                if(conn!=null){
                   conn.close();
                   dBConfig.closeConnection();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }

    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStmt = null;
        try {
            if(conn == null){
                conn = dBConfig.configureDB();
            }
            conn.setAutoCommit(false);
            // the mysql insert statement
            String query = " delete from teacher where id = ?";
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            
            
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null) {
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
        }finally{
            try{
                if(preparedStmt != null){
                    preparedStmt.close();
                }
                if(conn!=null){
                   conn.close();
                   dBConfig.closeConnection();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }

    }

    @Override
    public void update(Object obj) {
        PreparedStatement preparedStmt = null;
        Teacher t = (Teacher)obj;
        try {
            if(conn == null){
                conn = dBConfig.configureDB();
            }
            conn.setAutoCommit(false);
            // the mysql insert statement
            String query = " update teacher set name = ? where id = ?";
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, t.getName());
            preparedStmt.setInt(2, t.getId());
            
            
            // execute the preparedstatement
            preparedStmt.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null) {
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
        }finally{
            try{
                if(preparedStmt != null){
                    preparedStmt.close();
                }
                    
                if(conn!=null){
                   conn.close();
                   dBConfig.closeConnection();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
    }

    @Override
    public Object select(int id) {
        Teacher teacher = new Teacher();
        PreparedStatement preparedStmt = null;
        try {
            if(conn == null){
                conn = dBConfig.configureDB();
            }
            conn.setAutoCommit(false);
            // the mysql insert statement
            String query = " select t.*, s.* from teacher t "
                    + "left join teacher_student ts on t.id = ts.teacher_id "
                    + "left join student s on ts.student_id = s.id "
                    + "where t.id = ?";
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            conn.commit();
            if(rs.next()){
                teacher.setId(rs.getInt(1));
                teacher.setName(rs.getString(2));
         
                
                List<Student> sList = new ArrayList<>();
                do{
                    Student s = new Student();                   
                    s.setId(rs.getInt(3));
                    s.setName(rs.getString(4));
                    s.setAddress(rs.getString(5));
                    sList.add(s);
                }while(rs.next());
                teacher.setStudents(sList);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeacherDAO.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null) {
            try {
                System.err.print("Transaction is being rolled back");
                conn.rollback();
            } catch(SQLException excep) {
                excep.printStackTrace();
            }
        }
        }finally{
            try{
                if(preparedStmt != null){
                    preparedStmt.close();
                }
                if(conn!=null){
                   conn.close();
                   dBConfig.closeConnection();
                }
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }

        
        return teacher;
    }
}
