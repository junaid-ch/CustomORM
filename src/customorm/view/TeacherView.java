/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.view;

import customorm.controller.TeacherController;
import customorm.model.Student;
import customorm.model.Teacher;
import java.util.Scanner;

/**
 *
 * @author junaid.ahmad
 */
public class TeacherView implements BaseView{
    
    @Override
    public void menu(){
        int option = 0;
        Scanner scan = new Scanner(System.in);
        
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
        TeacherController tc = new TeacherController();
        tc.add();
    }
    
    @Override
    public void delete(){
        TeacherController tc = new TeacherController();
        tc.delete();
    }
    
    @Override
    public void update(){
        TeacherController tc = new TeacherController();
        tc.update();
    }
    
    @Override
    public void print(){
        TeacherController tc = new TeacherController();
        Teacher t = tc.print();
        
        if(t.getId() != 0){
            System.out.println("Teacher: ");
            System.out.println("ID: " + t.getId());
            System.out.println("Name: " + t.getName());
            System.out.println("Realted Students: ");
            if(t.getStudents().get(0).getId() != 0){
                for (Student student: t.getStudents()) {
                    System.out.println("ID: " + student.getId() 
                            + "\tName: " + student.getName() 
                            + "\tAddress: " + student.getAddress());
                }
            }else{
                System.out.println("No Student Found...");
            }
        }else{
            System.out.println("No Record Found...");
        }
    }
    
}
