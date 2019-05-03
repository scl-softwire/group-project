package org.softwire.training.slideshowbob.services;

import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService extends DatabaseService {

    private final PasswordEncoder encoder;

    @Autowired
    public UsersService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public List<AdminUser> getAlladmins() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM admins")
                        .mapToBean(AdminUser.class)
                        .list());
    }


    public void createUser(AdminUser user) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO admins (username, password) " +
                        "VALUES (:username, :password)")
                        .bind("username", user.getUsername())
                        .bind("password", encoder.encode(user.getPassword()))
                        .execute()
        );
    }

    public Optional<String> getEncodedPassword(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT password FROM admins WHERE username = :username")
                        .bind("username", username)
                        .mapTo(String.class)
                        .findFirst()
        );
    }

    public AdminUser getUser(int i) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM admins WHERE id = :id")
                        .bind("id", i)
                        .mapToBean(AdminUser.class)
                        .findFirst().get()
        );
    }


    public AdminUser loadUserByUsername(String username) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM admins WHERE username = :username")
                        .bind("username", username)
                        .mapToBean(AdminUser.class)
                        .findFirst().get()
        );
    }
}
