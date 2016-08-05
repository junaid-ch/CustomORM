/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm;


import customorm.view.StudentView;
import customorm.view.TeacherView;
import java.util.Scanner;

/**
 *
 * @author junaid.ahmad
 */
public class CustomORM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner scan = new Scanner(System.in);
        int option = 0;
        
        System.out.println("1. Teacher");
        System.out.println("2. Student");
        
        option = scan.nextInt();
        
        switch(option){
            case 1:
                TeacherView tv = new TeacherView();
                tv.menu();
                break;
            case 2:
                StudentView sv = new StudentView();
                sv.menu();
                break;
            default:
                break;
        }
        
        
    }
    
}
