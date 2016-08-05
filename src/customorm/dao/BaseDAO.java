/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.dao;

import customorm.model.Student;

/**
 *
 * @author junaid.ahmad
 */
public interface BaseDAO {
    public void insert(Object obj);
    public void delete(int id);
    public void update(Object obj);
    public Object select(int id);
}
