import org.jetbrains.annotations.NotNull;

import static javax.swing.JOptionPane.*;
import javax.swing.*;
import java.sql.*;


//package com.olekristian.oving4;

/**
 * Created by olekristianaune on 20.01.2016.
 */
public class Client {

    public static void main(String[] args) throws Exception {
        String brukernavn = DataLeser.lesTekst("Brukernavn: ");  // DataLeser, se nedenfor
        String passord = DataLeser.lesPassord();

        String databasedriver = "com.mysql.jdbc.Driver";
        Class.forName(databasedriver);  // laster inn driverklassen

        String databasenavn = "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + brukernavn + "?user=" + brukernavn + "&password=" + passord;
        Connection forbindelse = DriverManager.getConnection(databasenavn);

        Statement setning = forbindelse.createStatement();

        ResultSet res = setning.executeQuery("select * from " + brukernavn + ".person");
        while (res.next()) {
            int persNr = res.getInt("persnr");
            String fornavn = res.getString("fornavn");
            String etternavn = res.getString("etternavn");
            System.out.println(persNr + ": " + fornavn + " " + etternavn);
        }
        res.close();
        setning.close();
        forbindelse.close();
    }

}

class DataLeser {
    /**
     * Leser passord fra brukeren.
     * Teksten "trimmes" for den returneres til klienten.
     */
    @NotNull
    public static String lesPassord() {
        JLabel jPassword = new JLabel("Passord: "); // forenklet: http://www.asjava.com/swing/joptionpane-showinputdialog-with-password/
        JTextField password = new JPasswordField();
        Object[] obj = {jPassword, password};
        showConfirmDialog(null, obj, "Please input password for JOptionPane showConfirmDialog", OK_CANCEL_OPTION);
        return password.getText().trim();
    }

    @NotNull
    public static String lesTekst(String ledetekst) {
        String tekst = showInputDialog(ledetekst);
        while (tekst == null || tekst.trim().equals("")) {
            showMessageDialog(null, "Du maa oppgi data.");
            tekst = showInputDialog(ledetekst);
        }
        return tekst.trim();
    }
}