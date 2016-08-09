/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.view;

import customorm.controller.CourseController;
import customorm.model.Course;
import customorm.model.Student;
import customorm.model.Teacher;
import java.util.Scanner;

/**
 *
 * @author junaid.ahmad
 */
public class CourseView implements BaseView{
    
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
        CourseController cc = new CourseController();
        cc.add();
    }
    
    @Override
    public void delete(){
        CourseController cc = new CourseController();
        cc.delete();
    }
    
    @Override
    public void update(){
        CourseController cc = new CourseController();
        cc.update();
    }
    
    @Override
    public void print(){
        
        CourseController cc = new CourseController();
        Course c = cc.print();
        if(c.getId() != 0){
            System.out.println("Course: ");
            System.out.println("ID: " + c.getId() 
                    + "\tName: " + c.getName());
            
            System.out.println("Realted Teachers: ");
            if(c.getTeachers().get(0).getId() != 0){
                for (Teacher teacher : c.getTeachers()) {
                    System.out.println("ID: " + teacher.getId() 
                            + "\tName: " + teacher.getName());
                }
            }else{
                System.out.println("No Teacher Found...");
            } 
            
            System.out.println("Realted Students: ");
            if(c.getStudents().get(0).getId() != 0){
                for (Student student : c.getStudents()) {
                    System.out.println("ID: " + student.getId() 
                            + "\tName: " + student.getName()
                            + "\tAddress: " + student.getAddress());
                }
            }else{
                System.out.println("No student Found...");
            }    
            
        }else{
            System.out.println("No Record Found...");
        }
   }
}
