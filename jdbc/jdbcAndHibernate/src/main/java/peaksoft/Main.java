package peaksoft;

import peaksoft.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {


        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
            userDaoHibernate.createUsersTable();
            




           //userDaoHibernate.dropUsersTable();
            userDaoHibernate.saveUser("Syimyk", "Syimy1k", (byte) 12);
            userDaoHibernate.saveUser("Jumgal", "Jumgal", (byte) 18);
//        userDaoHibernate.removeUserById(4);
        System.out.println(userDaoHibernate.getAllUsers());
    //       userDaoHibernate.cleanUsersTable();
        //System.out.println(userDaoHibernate.getAllUsers());

    }
}

