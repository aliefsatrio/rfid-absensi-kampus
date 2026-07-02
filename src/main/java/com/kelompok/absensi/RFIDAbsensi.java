package com.kelompok.absensi;

import javax.swing.SwingUtilities;
import com.rfid.absensikampus.gui.LoginPage;

public class RFIDAbsensi {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            LoginPage login = new LoginPage();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });

    }
}