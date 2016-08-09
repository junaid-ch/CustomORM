/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;

import customorm.dao.StudentDAO;
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
public class StudentController implements BaseController{

    Scanner scan = new Scanner(System.in);
    
    @Override
    public void add() {
        Student student = new Student();
        List<Teacher> tlist = new ArrayList<>();
        List<Course> clist = new ArrayList<>();
        
        System.out.print("Name: ");
        student.setName(scan.next());
        System.out.print("Address: ");
        student.setAddress(scan.next());
        System.out.print("TeacherID's(comma seperated): ");
        String[] tId = scan.next().split(",");
        System.out.print("CourseID's(comma seperated): ");
        String[] cId = scan.next().split(",");
        
        for (String str1 : tId) {
            Teacher t1 = new Teacher();
            t1.setId(Integer.parseInt(str1));
            tlist.add(t1);
        }
        
        for (String str1 : cId) {
            Course c = new Course();
            c.setId(Integer.parseInt(str1));
            clist.add(c);
        }
        
        student.setTeachers(tlist);
        student.setCourses(clist);
        StudentDAO dao = new StudentDAO();
        dao.insert(student);

    }

    @Override
    public void delete() {
        
        StudentDAO dao = new StudentDAO();
        System.out.print("ID: ");
        dao.delete(scan.nextInt());
    }

    @Override
    public void update() {
        StudentDAO dao = new StudentDAO();
        Student t = new Student();
        
        System.out.print("ID: ");
        t.setId(scan.nextInt());
        System.out.print("Name: ");
        t.setName(scan.next());
        System.out.print("Address: ");
        t.setAddress(scan.next());
        
        dao.update(t);
    }

    @Override
    public Student print() {
        StudentDAO dao = new StudentDAO();
        System.out.print("ID: ");
        int id = scan.nextInt();
        Student s = (Student)dao.select(id);
        return s;
    }
    
}
