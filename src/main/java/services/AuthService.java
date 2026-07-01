package services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.rfid.absensikampus.MongoManager;
import com.rfid.absensikampus.gui.DashboardAdmin;
import com.rfid.absensikampus.gui.LoginPage;
import org.bson.Document;

import javax.swing.JOptionPane;

public class AuthService {

    private final MongoDatabase database;
    private final MongoCollection<Document> adminCollection;

    public AuthService() {
        database = MongoManager.getDatabase();

        // Sesuaikan dengan nama collection di MongoDB Compass kamu
        // Dari screenshot kamu: collection = "admin"
        adminCollection = database.getCollection("admin");
    }

    public void login(String username, String password, LoginPage loginPage) {
        try {
            username = username.trim();
            password = password.trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        loginPage,
                        "Username dan password wajib diisi!"
                );
                return;
            }

            Document query = new Document("username", username)
                    .append("password", password);

            Document user = adminCollection.find(query).first();

            System.out.println("Database: " + database.getName());
            System.out.println("Collection: admin");
            System.out.println("Username input: " + username);
            System.out.println("Password input: " + password);
            System.out.println("User ditemukan: " + user);

            if (user != null) {
                JOptionPane.showMessageDialog(
                        loginPage,
                        "Login Berhasil!"
                );

                DashboardAdmin dashboard = new DashboardAdmin();
                dashboard.setLocationRelativeTo(null);
                dashboard.setVisible(true);

                loginPage.dispose();

            } else {
                JOptionPane.showMessageDialog(
                        loginPage,
                        "Username atau Password Salah!",
                        "Login Gagal",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    loginPage,
                    "Terjadi kesalahan saat login: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    public void registerAdmin(String username, String password) {
        try {
            Document data = new Document("username", username.trim())
                    .append("password", password.trim());

            adminCollection.insertOne(data);

            JOptionPane.showMessageDialog(
                    null,
                    "Admin berhasil ditambahkan!"
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Gagal menambahkan admin: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
    }

    void registerUser(String yusuf_Maulana, String admin, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}