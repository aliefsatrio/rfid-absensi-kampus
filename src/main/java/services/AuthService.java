package services;

import com.mongodb.client.model.Filters;
import dao.GenericDAO;
import java.time.LocalDateTime;
import objects.Mahasiswa;
import objects.User;
import org.bson.conversions.Bson;

public class AuthService {

    private final GenericDAO<User> adminDAO =
            new GenericDAO<>("Admin", User.class);

    private final GenericDAO<Mahasiswa> mahasiswaDAO =
            new GenericDAO<>("mahasiswa", Mahasiswa.class);

    /**
     * LOGIN ADMIN
     */
    public boolean loginAdmin(String username, String password) {

        User admin = adminDAO.findOne(
                Filters.and(
                        Filters.eq("username", username),
                        Filters.eq("password", password)
                )
        );

        if (admin != null) {

            admin.setLastLogin(LocalDateTime.now());

            Bson filter = Filters.eq("username", username);

            adminDAO.update(filter, admin);

            return true;
        }

        return false;
    }

    /**
     * LOGIN MAHASISWA
     */
    public Mahasiswa loginDosen(String username,
                                    String password) {

        return mahasiswaDAO.findOne(
                Filters.and(
                        Filters.eq("username", username),
                        Filters.eq("password", password)
                )
        );

    }

    /**
     * REGISTER ADMIN
     */
    public void registerAdmin(String fullname,
                              String username,
                              String password) {

        User admin = new User(
                fullname,
                username,
                password,
                null
        );

        adminDAO.save(admin);
    }

    /**
     * REGISTER MAHASISWA
     */
    public void registerDosen(Mahasiswa mahasiswa) {

        mahasiswaDAO.save(mahasiswa);
    }

}