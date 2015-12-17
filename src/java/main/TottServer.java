/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import object.Database;

/**
 *
 * @author rekah4
 */
public class TottServer {

    /**
     * @param args the command line arguments
     * Po zmianie danych logowania do bazy danych, nie commitować tego pliku!!!
     */
    public static void main(String[] args) {
        Database db = new Database("localhost", "3306", "totalizator", "totalizator", "totalizator");
        
    }
    
}
