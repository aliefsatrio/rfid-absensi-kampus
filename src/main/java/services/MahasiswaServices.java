package services;

import com.rfid.absensikampus.gui.DashboardAdmin;
import dao.GenericDAO;
import objects.Mahasiswa;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.conversions.Bson;

/**
 *
 * @author mnish
 */
public class MahasiswaServices {

    // Inisialisasi GenericDAO khusus untuk entitas Karyawan
    // Menggunakan koleksi "karyawan" dan referensi Class Karyawan [3]
    private final GenericDAO<Mahasiswa> DAO;
    private Mahasiswa MahasiswaBaru;

    public MahasiswaServices() {
        this.DAO = new GenericDAO<>("mahasiswa", Mahasiswa.class);
    }

    /**
     * 1.CREATE: Fungsi untuk menyimpan data karyawan baru ke MongoDB [2], [3]
     *
     * @param MahasiswaBaru
     */
    public void MahasiswaServices(Mahasiswa MahasiswaBaru) {
        DAO.save(MahasiswaBaru); // Memanggil insertOne melalui GenericDAO [3]
    }

    public void tambahMahasiswa(String uid,
                            String nim,
                            String namaLengkap,
                            String mataKuliah,
                            String username,
                            String password) {

    Mahasiswa mahasiswaBaru =
            new Mahasiswa(
                    uid,
                    nim,
                    namaLengkap,
                    mataKuliah,
                    username,
                    password
            );

    DAO.save(mahasiswaBaru);
    }

    /**
     * 2. READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     */
    public void tampilkanDaftarMahasiswa() {
        List<Mahasiswa> daftar = DAO.findAll();
        System.out.println("--- Daftar Karyawan Bank ---");
        for (Mahasiswa k : daftar) {
            System.out.println(k.toString()); // Menggunakan format toString di sumber [7]
        }
    }

    /**
     * 2.READ (All): Fungsi untuk mengambil semua data karyawan [5], [6]
     *
     * @param panelTarget
     * @param key
     */
    public void tampilMahasiswa(JPanel panelTarget, String key) {
        //1. 
        // Menampilkan data berdasarkan request
        // key "null/kosong" = get all data
        // key "filled" = get specific data

        List<Mahasiswa> daftarMahasiswa;
        if (key.isEmpty()) {
            //Mengambil data dari database menggunakan GenericDAO
            daftarMahasiswa = DAO.findAll();
        } else {
            //Mengambil data dari database menggunakan GenericDAO
            //berdasarkan kata kunci yang diketik
            daftarMahasiswa = cariMahasiswa(key);
        }
        // 2. Membersihkan panel target utama sebelum memuat data baru
        panelTarget.removeAll();

        // Mengubah layout panel target menjadi BorderLayout
        panelTarget.setLayout(new BorderLayout());
        // Mengatur warna background utama menjadi biru
        panelTarget.setBackground(new Color(68, 114, 196));

        // Membuat panel grid khusus untuk menampung kotak/card
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        gridPanel.setOpaque(false); // Transparan agar warna biru panelTarget terlihat
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Memberi jarak dari tepi layar

        // 3. Iterasi data dan menambahkannya ke panel grid
        try {
            for (Mahasiswa k: daftarMahasiswa) {
                // Membuat panel 'Card' (box orange) untuk 1 karyawan
                // Layout 4 baris 1 kolom agar kolor berisi Nama,ID, Departemen, panel control 
                JPanel cardPanel = new JPanel(new GridLayout(4, 1, 0, 0));
                cardPanel.setBackground(new Color(237, 125, 49)); // Warna background orange

                // Memberikan garis tepi tipis membulat (rounded) dan padding/jarak ke dalam
                cardPanel.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.MAGENTA, 1, true),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));

                // Membuat Label Nama & Set warna teks jadi Putih
                JLabel lblNama = new JLabel("Nama: " + k.getNamaLengkap());
                lblNama.setForeground(Color.WHITE);

                // Membuat Label ID Karyawan & Set warna teks jadi Putih
                JLabel lblidMh = new JLabel("ID Mahasiswa: " + k.getUid());
                lblidMh.setForeground(Color.WHITE);

                // Membuat Label Departemen & Set warna teks jadi Putih
                JLabel lblNim = new JLabel("NIM: " + k.getNim());
                lblNim.setForeground(Color.WHITE);

                // Membuat Label Departemen & Set warna teks jadi Putih
                JLabel lblMk = new JLabel("MK: " + k.getMataKuliah());
                lblMk.setForeground(Color.WHITE);
                
                // Membuat panel kontrol 1 baris 2 kolom, berisi tombol edit dan hapus
                JPanel controlPanel = new JPanel(new GridLayout(1, 2, 20, 15));
                controlPanel.setBackground(new Color(237, 125, 49));

                JButton tombolEdit = new JButton("Edit");
                tombolEdit.setBackground(Color.ORANGE);
                tombolEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tombolEdit.addActionListener((ActionEvent e) -> {
                    DashboardAdmin.UID.setText(k.getUid());
                    DashboardAdmin.NIM.setText(k.getNim());
                    DashboardAdmin.NAMALKP.setText(k.getNamaLengkap());
                    DashboardAdmin.MK.setSelectedItem(k.getMataKuliah());
                    DashboardAdmin.update.setEnabled(true);
                    DashboardAdmin.Save.setEnabled(false); 
                });
                JButton tombolDelete = new JButton("Delete");
                tombolDelete.setBackground(Color.RED);
                tombolDelete.setForeground(Color.WHITE);
                tombolDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
                tombolDelete.addActionListener((ActionEvent e) -> {
                    Object[] options = {"Ya, Hapus", "Batal"};
                    int choice = JOptionPane.showOptionDialog(
                            null, // Parent component
                            "Apakah Anda ingin menyimpan data "+k.getNamaLengkap()+"?", // Message
                            "Konfirmasi Pengelolaan", // Title
                            JOptionPane.YES_NO_OPTION, // Option type
                            JOptionPane.QUESTION_MESSAGE, // Message type
                            null, // Custom icon (null uses default)
                            options, // The array of custom button text
                            options[0] // Default button focused
                    );

                    switch (choice) {
                        case JOptionPane.YES_OPTION -> hapusMahasiswa(k.getUid());
                        case JOptionPane.NO_OPTION -> System.out.println("User memilih: Batal");
                        default -> {
                        }
                    }
                });

                controlPanel.add(tombolEdit);
                controlPanel.add(tombolDelete);

                // Memasukkan label ke dalam cardPanel (box orange)
                cardPanel.add(lblNama);
                cardPanel.add(lblNim);
                cardPanel.add(lblMk);
                cardPanel.add(controlPanel);

                // Memasukkan cardPanel utuh ke dalam gridPanel
                gridPanel.add(cardPanel);
            }

            // Memasukkan gridPanel ke bagian ATAS (NORTH) dari panel target.
            panelTarget.add(gridPanel, BorderLayout.NORTH);

            // 4. Me-refresh panel agar perubahan muncul di GUI
            panelTarget.revalidate();
            panelTarget.repaint();
        } catch (Exception e) {
        }
    }

    /**
     * 3.READ (One): Mencari satu karyawan spesifik berdasarkan UID RFID [5],
     * [6] Sangat krusial untuk alur Tap Kartu pada Pertemuan 14 [8].
     *
     * @param key
     * @return
     */
    public List<Mahasiswa> cariMahasiswa(String key) {
        List<Bson> filters = new ArrayList<>();
        // Get all fields from the Karyawan class
        for (Field field : Mahasiswa.class.getDeclaredFields()) {
            // Skip the uidRfid field and non-string fields if necessary
            if (field.getName().equals("uidRfid")) {
                continue;
            }
            filters.add(Filters.regex(field.getName(), key, "i"));
        }
        // Search and return Karyawan objects directly
        List<Mahasiswa> results = DAO.findMany(Filters.or(filters));
        return results;
    }

    /**
     * 4.UPDATE: Memperbarui data karyawan menggunakan filter Bson [5], [6]
     *
     * @param newK
     */
    public void updateMahasiswa(Mahasiswa newK) {
        Bson filter = Filters.eq("uid", newK.getUid());
        Mahasiswa k = DAO.findOne(filter);
        if (k != null) {
            DAO.update(filter, newK);
            DashboardAdmin.showData("");
            JOptionPane.showMessageDialog(null, "Data berhasil diperbarui!");
        }
    }

    /**
     * 5.DELETE: Menghapus data karyawan dari database [5], [6]
     *
     * @param idK
     */
    public void hapusMahasiswa(String idK) {
        Bson filter = Filters.eq("uid", idK);
        DAO.delete(filter);
        DashboardAdmin.showData("");
        JOptionPane.showMessageDialog(null, "Data mahasiswa berhasil dihapus.");
    }

    public void tambahMahasiswa(Mahasiswa K) {
        DAO.save(K);
    }
    
    public Mahasiswa findByUid(String uid){

        return DAO.findOne(
            Filters.eq("uid", uid)
        );
    }
    
    public Mahasiswa findByUsername(String username){

        return DAO.findOne(
                Filters.eq("username", username)
        );
    }
}