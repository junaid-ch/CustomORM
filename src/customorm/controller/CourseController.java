/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;

import customorm.dao.BaseDAO;
import customorm.dao.DAOFactory;
import customorm.model.BaseModel;
import customorm.model.Course;
import customorm.model.ModelFactory;
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
    
    private Scanner scan;
    private BaseDAO dao;
    private DAOFactory dAOFactory;
    private final ModelFactory modelFactory;
    private BaseModel baseModel;

    public CourseController() {
        scan = new Scanner(System.in);
        dAOFactory = new DAOFactory();
        dao = dAOFactory.getDAO("courseDAO");
        modelFactory = new ModelFactory();
    }
        
    @Override
    public void add() {
        baseModel = modelFactory.getModel("courseModel");
        Course course = (Course)baseModel;
        List<Teacher> tlist = new ArrayList<>();
        List<Student> slist = new ArrayList<>();

        System.out.print("Name: ");
        course.setName(scan.next());
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
        
        course.setTeachers(tlist);
        course.setStudents(slist);
        
        dao.insert(course);

    }

    @Override
    public void delete() {        
        System.out.print("ID: ");
        dao.delete(scan.nextInt());
    }

    @Override
    public void update() {
        Course c = (Course)modelFactory.getModel("courseModel");
        
        System.out.print("ID: ");
        c.setId(scan.nextInt());
        System.out.print("Name: ");
        c.setName(scan.next());
        
        dao.update(c);
    }

    @Override
    public Course print() {
        System.out.print("ID: ");
        int id = scan.nextInt();
        Course c = (Course)dao.select(id);
        return c;
    }  
}
