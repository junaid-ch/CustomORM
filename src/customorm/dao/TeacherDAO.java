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
        try {
            if(conn == null){
                conn = dBConfig.configureDB();
            }
            conn.setAutoCommit(false);
            // the mysql insert statement
            String query = " insert into teacher (name)"
                    + " values (?)";
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, s.getName());

            
            // execute the preparedstatement
            preparedStmt.execute();
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
            String query = " select * from teacher where id = ?";
            
            // create the mysql insert preparedstatement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, id);
            
            
            // execute the preparedstatement
            ResultSet rs = preparedStmt.executeQuery();
            conn.commit();
            if(rs.next()){
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
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
