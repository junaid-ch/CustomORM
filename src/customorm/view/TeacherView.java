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
public class TeacherView extends BaseView{
    
    private ControllerFactory controllerFactory;
    private BaseController baseController;
    
    public TeacherView() {
        controllerFactory = new ControllerFactory();
        baseController = controllerFactory.getController("teacherController");
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
        
        Teacher t = (Teacher)baseController.print();
        
        if(t.getId() != 0){
            System.out.println("Teacher: ");
             System.out.println("ID: " + t.getId() 
                            + "\tName: " + t.getName());
             
            System.out.println("Realted Students: ");
            if(t.getStudents().get(0).getId() != 0){    //students exist or not
                for (Student student: t.getStudents()) {
                    System.out.println("ID: " + student.getId() 
                            + "\tName: " + student.getName() 
                            + "\tAddress: " + student.getAddress());
                }
            }else{
                System.out.println("No Student Found...");
            }
            
            System.out.println("Realted Courses: ");
            if(t.getCourses().get(0).getId() != 0){    //courses exist or not
                for (Course course: t.getCourses()) {
                    System.out.println("ID: " + course.getId() 
                            + "\tName: " + course.getName());
                }
            }else{
                System.out.println("No course Found...");
            }
        }else{
            System.out.println("No Record Found...");
        }
    }
    
}
