/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;

import customorm.dao.StudentDAO;
import customorm.model.Student;
import java.util.Scanner;

/**
 *
 * @author junaid.ahmad
 */
public class StudentController implements BaseController{

    Scanner scan = new Scanner(System.in);
    
    @Override
    public void add() {
        Student t = new Student();
        
        System.out.println("Name: ");
        t.setName(scan.next());
        System.out.println("Address: ");
        t.setAddress(scan.next());
        
        StudentDAO dao = new StudentDAO();
        dao.insert(t);

    }

    @Override
    public void delete() {
        
        StudentDAO dao = new StudentDAO();
        System.out.println("ID: ");
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
        System.out.println("ID: ");
        int id = scan.nextInt();
        Student s = (Student)dao.select(id);
        return s;
    }
    
}
