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
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("StudentID's(comma seperated): ");
        String[] str = scan.next().split(",");
        List<Student> slist = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            Student s = new Student();
            s.setId(Integer.parseInt(str[i]));
            slist.add(s);
        }
        
        t.setStudents(slist);
              
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
