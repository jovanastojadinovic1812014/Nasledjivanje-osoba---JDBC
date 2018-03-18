/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import db.DBBroker;
import domen.Student;
import domen.Zaposleni;

/**
 *
 * @author Jovana
 */
public class Main {

    public static void main(String[] args) throws Exception {

        DBBroker broker = new DBBroker();
        
        Zaposleni z = new Zaposleni("181", "1401995727828", "Jovana", "Stojadinovic");
        Student s = new Student("1/14", "1", "Jovana", "Stojadinovic");
        broker.uspostaviKonekciju();
        //broker.ubaciStudenta(s);
        broker.potvrdiTransakciju();
        broker.vratiSveZaposlene();
        broker.potvrdiTransakciju();
        broker.raskiniKonekciju();

    }
}
