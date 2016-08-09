/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author junaid.ahmad
 */

public class Student {
    
   private int id;
   private String name;
   private String address;
   private List<Teacher> teachers = new ArrayList();
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the teachers
     */
    public List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * @param teachers the teachers to set
     */
    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
    
     @Override
    public boolean equals(Object obj) {
        
        if((obj != null) && (obj instanceof Student) 
                && (this.id == ((Student)obj).getId()) 
                && (this.name.equalsIgnoreCase(((Student)obj).getName()))
                && (this.address.equalsIgnoreCase(((Student)obj).getAddress()))){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.address);
        return hash;
    }
}
