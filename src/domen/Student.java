/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author Jovana
 */
public class Student extends Osoba {

    private String brojIndeksa;

    public Student() {
    }

    public Student(String brojIndeksa, String jmbg, String ime, String prezime) {
        super(jmbg, ime, prezime);
        this.brojIndeksa = brojIndeksa;
    }

    public String getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(String brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "Student{" + "brojIndeksa=" + brojIndeksa + '}';
        return s;
    }

}
