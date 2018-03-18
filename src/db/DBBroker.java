/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Student;
import domen.Zaposleni;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jovana
 */
public class DBBroker {

    Connection connection;

    public void ucitajDriver() throws Exception {
        try {
            Class.forName(SettingsLoader.getInstance().getValue("driver"));
        } catch (ClassNotFoundException | IOException ex) {
            throw new Exception("Driver nije uspesno ucitan! " + ex.getMessage());
        }
    }

    public void uspostaviKonekciju() throws Exception {

        try {
            String url = SettingsLoader.getInstance().getValue("url");
            String user = SettingsLoader.getInstance().getValue("user");
            String password = SettingsLoader.getInstance().getValue("password");
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            throw new Exception("Nije uspostavljena konekcija sa bazom! " + ex.getMessage());
        }
    }

    public void raskiniKonekciju() throws Exception {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new Exception("Konekcija nije uspesno raskinuta! " + ex.getMessage());
        }
    }

    public void potvrdiTransakciju() throws Exception {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new Exception("Transakcija potvrdjena! " + ex.getMessage());
        }
    }

    public void ponistiTransakciju() throws Exception {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new Exception("Transakcija ponistena! " + ex.getMessage());
        }
    }

    public void ubaciStudenta(Student s) throws Exception {
        String upit = "INSERT INTO osoba(jmbg,ime,prezime,brojIndeksa,zanimanje) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, s.getJmbg());
            ps.setString(2, s.getIme());
            ps.setString(3, s.getPrezime());
            ps.setString(4, s.getBrojIndeksa());
            ps.setString(5, "student");

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception("Student nije uspesno unet! " + ex.getMessage());
        }
    }

    public void ubaciZaposlenog(Zaposleni z) throws Exception {
        String upit = "INSERT INTO osoba(jmbg,ime,prezime,brojRadneKnjizice,zanimanje) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, z.getJmbg());
            ps.setString(2, z.getIme());
            ps.setString(3, z.getPrezime());
            ps.setString(4, z.getBrojRadneKnjizice());
            ps.setString(5, "zaposleni");

            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            throw new Exception("Zaposleni nije uspesno unet! " + ex.getMessage());
        }
    }

    public void izbrisiStudenta(String brojIndeksa) throws Exception {
        String upit = "DELETE FROM osoba WHERE brojIndeksa=?";

        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, brojIndeksa);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new Exception("Student nije uspesno obrisan! " + ex.getMessage());
        }

    }

    public void izbrisiZaposlenog(String brojRadneKnjizice) throws Exception {
        String upit = "DELETE FROM osoba WHERE brojRadneKnjizice=?";

        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, brojRadneKnjizice);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            throw new Exception("Zaposleni nije uspesno obrisan! " + ex.getMessage());
        }
    }

    public List<Student> vratiSveStudente() throws Exception {
        List<Student> studenti = new LinkedList<>();

        String upit = "SELECT * FROM osoba WHERE brojRadneKnjizice IS NULL";

        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setBrojIndeksa(rs.getString("brojIndeksa"));
                s.setIme(rs.getString("ime"));
                s.setPrezime(rs.getString("prezime"));
                s.setJmbg(rs.getString("jmbg"));
                studenti.add(s);
            }

            if (studenti.isEmpty()) {
                System.out.println("Ne postoje studenti u bazi!");
            } else {
                System.out.println(studenti);
            }

            return studenti;

        } catch (SQLException ex) {
            throw new Exception("Studenti nisu uspesno izlistani! " + ex.getMessage());
        }

    }

    public List<Zaposleni> vratiSveZaposlene() throws Exception {
        List<Zaposleni> zaposleni = new LinkedList<>();

        String upit = "SELECT * FROM osoba WHERE brojIndeksa IS NULL";

        try {
            PreparedStatement ps = connection.prepareStatement(upit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Zaposleni z = new Zaposleni();
                z.setBrojRadneKnjizice(rs.getString("brojRadneKnjizice"));
                z.setIme(rs.getString("ime"));
                z.setPrezime(rs.getString("prezime"));
                z.setJmbg(rs.getString("jmbg"));
                zaposleni.add(z);
            }

            if (zaposleni.isEmpty()) {
                System.out.println("Ne postoje studenti u bazi!");
            } else {
                System.out.println(zaposleni);
            }

            return zaposleni;

        } catch (SQLException ex) {
            throw new Exception("Zaposleni nisu uspesno izlistani! " + ex.getMessage());
        }
    }
}
