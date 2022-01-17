package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService userService = new UserServiceImpl(new UserDaoHibernateImpl());
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("человек1", "чел1", (byte) 78);
        userService.saveUser("человек2", "чел2", (byte) 74);
        userService.saveUser("человек3", "чел3", (byte) 59);
        userService.saveUser("человек4", "чел4", (byte) 74);
        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}