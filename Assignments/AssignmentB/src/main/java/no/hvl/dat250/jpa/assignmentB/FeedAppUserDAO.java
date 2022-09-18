package no.hvl.dat250.jpa.assignmentB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FeedAppUserDAO {
    private List<FeedAppUser> users;

    public FeedAppUserDAO() {
        users = new ArrayList<>();
    }

    public List<FeedAppUser> getAllUsers() {
        return this.users;
    }

    public FeedAppUser getUserByID(Long id) {
        for (FeedAppUser user : getAllUsers()) {
            if (user.getUserID().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void save(FeedAppUser user) {
        getAllUsers().add(user);
    }

    public void delete(FeedAppUser user) {
        getAllUsers().remove(user);
    }

    public void update(FeedAppUser user, String[] params) {
        user.setUsername(params[0]);
        user.setFirstName(params[1]);
        user.setLastName(params[2]);
        user.getPassword().setPassword(params[3]);
    }
}
