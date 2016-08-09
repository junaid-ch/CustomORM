/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.view;

import customorm.controller.StudentController;
import customorm.model.Course;
import customorm.model.Student;
import customorm.model.Teacher;
import java.util.Scanner;


/**
 *
 * @author junaid.ahmad
 */

public class StudentView implements BaseView{
    
    Scanner scan = null;
    
    @Override
    public void menu(){
        int option = 0;
        scan = new Scanner(System.in);
        
        System.out.println("1. add");
        System.out.println("2. delete");
        System.out.println("3. update");
        System.out.println("4. view");
        
        option = scan.nextInt();
        
        switch (option) {
            case 1:
                add();
                break;
            case 2:
                delete();
                break;
            case 3:
                update();
                break;
            case 4:
                print();
                break;
            default:
                break;
        }
        
    }
    
    @Override
    public void add(){
        StudentController sc = new StudentController();
        sc.add();
    }
    
    @Override
    public void delete(){
        StudentController sc = new StudentController();
        sc.delete();
    }
    
    @Override
    public void update(){
        StudentController sc = new StudentController();
        sc.update();
    }
    
    @Override
    public void print(){
        
        StudentController sc = new StudentController();
        Student s = sc.print();
        if(s.getId() != 0){
            System.out.println("Student: ");
            System.out.println("ID: " + s.getId() 
                    + "\tName: " + s.getName()
                    + "\tAddress: " + s.getAddress());
            System.out.println("Realted Teachers: ");
            if(s.getTeachers().get(0).getId() != 0){
                for (Teacher teacher : s.getTeachers()) {
                    System.out.println("ID: " + teacher.getId() 
                            + "\tName: " + teacher.getName());
                }
            }else{
                System.out.println("No Teacher Found...");
            }
            System.out.println("Realted Courses: ");
            if(s.getCourses().get(0).getId() != 0){
                for (Course course : s.getCourses()) {
                    System.out.println("ID: " + course.getId() 
                            + "\tName: " + course.getName());
                }
            }else{
                System.out.println("No Course Found...");
            }      
        }else{
            System.out.println("No Record Found...");
        }
   }
    
}
