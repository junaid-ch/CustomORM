/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.view;

import customorm.controller.BaseController;
import customorm.controller.ControllerFactory;
import customorm.model.Course;
import customorm.model.Student;
import customorm.model.Teacher;


/**
 *
 * @author junaid.ahmad
 */

public class StudentView extends BaseView{

    private ControllerFactory controllerFactory;
    private BaseController baseController;
    
    public StudentView() {
        controllerFactory = new ControllerFactory();
        baseController = controllerFactory.getController("studentController");
    }
        
    @Override
    public void add(){
        baseController.add();
    }
    
    @Override
    public void delete(){
        baseController.delete();
    }
    
    @Override
    public void update(){
        baseController.update();
    }
    
    @Override
    public void print(){
        
        Student s = (Student)baseController.print();
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
