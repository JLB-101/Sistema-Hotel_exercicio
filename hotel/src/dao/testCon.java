/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author lb
 */
public class testCon {
    public static void main(String[] args) throws Exception {
        Dao d = new  Dao();
        System.out.println(d.getConnection());
    }
    
}
