package services;

public class UserInjector {

    public static void main(String[] args) {

        AuthService userService = new AuthService();

        userService.registerUser(
                "Yusuf Maulana",
                "admin",
                "123"
        );

        System.out.println("User berhasil ditambahkan!");
    }
}