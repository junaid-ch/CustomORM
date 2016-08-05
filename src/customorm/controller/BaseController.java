/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.controller;

import customorm.model.Student;

/**
 *
 * @author junaid.ahmad
 */
public interface BaseController {

   
    public void add();
    public void delete();
    public void update();
    public Object print();
    
}
