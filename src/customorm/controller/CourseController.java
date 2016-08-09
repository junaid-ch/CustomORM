/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;

import customorm.dao.CourseDAO;
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
public class CourseController implements BaseController{
    
    Scanner scan = new Scanner(System.in);
    
    @Override
    public void add() {
        Course c = new Course();
        List<Teacher> tlist = new ArrayList<>();
        List<Student> slist = new ArrayList<>();

        System.out.print("Name: ");
        c.setName(scan.next());
        System.out.print("TeacherID's(comma seperated): ");
        String[] tId = scan.next().split(",");
        System.out.print("StudentID's(comma seperated): ");
        String[] sId = scan.next().split(",");
        
        
        for (String str1 : tId) {
            Teacher t1 = new Teacher();
            t1.setId(Integer.parseInt(str1));
            tlist.add(t1);
        }
        
        
        for (String str1 : sId) {
            Student t1 = new Student();
            t1.setId(Integer.parseInt(str1));
            slist.add(t1);
        }
        
        c.setTeachers(tlist);
        c.setStudents(slist);
        
        CourseDAO dao = new CourseDAO();
        dao.insert(c);

    }

    @Override
    public void delete() {
        
        CourseDAO dao = new CourseDAO();
        System.out.print("ID: ");
        dao.delete(scan.nextInt());
    }

    @Override
    public void update() {
        CourseDAO dao = new CourseDAO();
        Course c = new Course();
        
        System.out.print("ID: ");
        c.setId(scan.nextInt());
        System.out.print("Name: ");
        c.setName(scan.next());
        
        dao.update(c);
    }

    @Override
    public Course print() {
        CourseDAO dao = new CourseDAO();
        System.out.print("ID: ");
        int id = scan.nextInt();
        Course c = (Course)dao.select(id);
        return c;
    }
    
}
