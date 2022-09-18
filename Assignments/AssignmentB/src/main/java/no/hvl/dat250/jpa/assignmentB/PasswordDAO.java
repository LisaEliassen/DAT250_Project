package no.hvl.dat250.jpa.assignmentB;

import java.util.ArrayList;
import java.util.List;

public class PasswordDAO {
    private List<Password> passwords;

    public PasswordDAO() {
        passwords = new ArrayList<>();
    }

    public List<Password> getAllPasswords() {
        return this.passwords;
    }

    public Password getPasswordByID(Long id) {
        for (Password password : getAllPasswords()) {
            if (password.getPassword().equals(id)) {
                return password;
            }
        }
        return null;
    }

    public void save(Password password) {
        getAllPasswords().add(password);
    }

    public void delete(Password password) {
        getAllPasswords().remove(password);
    }

    public void update(Password password, String[] params) {
        password.setPassword(params[0]);
    }
}
