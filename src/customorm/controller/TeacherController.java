/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;


import customorm.dao.TeacherDAO;
import customorm.model.Course;
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
        List<Student> slist = new ArrayList<>();
        List<Course> clist = new ArrayList<>();
        
        System.out.print("Name: ");
        t.setName(scan.next());
        System.out.print("StudentID's(comma seperated): ");
        String[] sId = scan.next().split(",");
        System.out.print("CourseID's(comma seperated): ");
        String[] cId = scan.next().split(",");
        
        for (String str1 : sId) {
            Student s = new Student();
            s.setId(Integer.parseInt(str1));
            slist.add(s);
        }
        
        for (String str1 : cId) {
            Course c = new Course();
            c.setId(Integer.parseInt(str1));
            clist.add(c);
        }
        
        t.setStudents(slist);
        t.setCourses(clist);
        TeacherDAO dao = new TeacherDAO();
        dao.insert(t);

    }

    @Override
    public void delete() {
        
        TeacherDAO dao = new TeacherDAO();
        System.out.print("ID: ");
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
        System.out.print("ID: ");
        int id = scan.nextInt();
        Teacher s = (Teacher)dao.select(id);
        return s;
    }
}
