/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;


import customorm.dao.StudentDAO;
import customorm.dao.TeacherDAO;
import customorm.model.Student;
import customorm.model.Teacher;
import java.util.Scanner;

/**
 *
 * @author junaid.ahmad
 */
public class TeacherController implements BaseController{
    
    Scanner scan = new Scanner(System.in);
    
    @Override
    public void add() {
        Teacher t = new Teacher();
        
        System.out.println("Name: ");
        t.setName(scan.next());
        
        
        TeacherDAO dao = new TeacherDAO();
        dao.insert(t);

    }

    @Override
    public void delete() {
        
        TeacherDAO dao = new TeacherDAO();
        System.out.println("ID: ");
        dao.delete(scan.nextInt());
    }

    @Override
    public void update() {
        TeacherDAO dao = new TeacherDAO();
        Teacher t = new Teacher();
        
        System.out.print("ID: ");
        t.setId(scan.nextInt());
        System.out.print("Name: ");
        t.setName(scan.next());
        
        dao.update(t);
    }

    @Override
    public Teacher print() {
        TeacherDAO dao = new TeacherDAO();
        System.out.println("ID: ");
        int id = scan.nextInt();
        Teacher s = (Teacher)dao.select(id);
        return s;
    }
}
