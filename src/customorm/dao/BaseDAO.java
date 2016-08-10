/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customorm.dao;

import customorm.model.BaseModel;

/**
 *
 * @author junaid.ahmad
 */
public interface BaseDAO {
    public void insert(BaseModel obj);
    public void delete(int id);
    public void update(BaseModel obj);
    public BaseModel select(int id);
}
