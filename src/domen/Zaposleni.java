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
public class Zaposleni extends Osoba {

    private String brojRadneKnjizice;

    public Zaposleni() {
    }

    public Zaposleni(String brojRadneKnjizice, String jmbg, String ime, String prezime) {
        super(jmbg, ime, prezime);
        this.brojRadneKnjizice = brojRadneKnjizice;
    }

    public String getBrojRadneKnjizice() {
        return brojRadneKnjizice;
    }

    public void setBrojRadneKnjizice(String brojRadneKnjizice) {
        this.brojRadneKnjizice = brojRadneKnjizice;
    }

    @Override
    public String toString() {
        String s = super.toString();
        s += "Zaposleni{" + "brojRadneKnjizice=" + brojRadneKnjizice + '}';
        return s;
    }

}
