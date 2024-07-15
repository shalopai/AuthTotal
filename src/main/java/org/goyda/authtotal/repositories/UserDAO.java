package org.goyda.authtotal.repositories;

import org.goyda.authtotal.models.User;

public class UserDAO extends BaseDAO<User> {
    public UserDAO() {
        super(User.class);
    }
}
