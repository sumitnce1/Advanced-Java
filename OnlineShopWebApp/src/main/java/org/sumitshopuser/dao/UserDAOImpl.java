package org.sumitshopuser.dao;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.sumitshop.hibernate.entity.Users;

public class UserDAOImpl implements UserDAO {
	private Session session;

	public UserDAOImpl(Session session) {
		this.session = session;
	}

	public UserDAOImpl() {
	}

	public void setSession(Session session) {
		this.session = session;
	}

    @Override
    public boolean authenticate(String userName, String password) {
    	Query<Users> query = session.createQuery("SELECT u FROM Users u WHERE u.userName = :userName AND u.password = :password");
        query.setParameter("userName", userName);
        query.setParameter("password", password);
        Users user =query.uniqueResult();
        return user != null;
    }

    @Override
    public boolean registerUser(String userName, String password, String name, String email, long phone, String city) {
        Users users = new Users();
        users.setUserName(userName);
        users.setPassword(password);
        users.setName(name);
        users.setEmail(email);
        users.setPhone(phone);
        users.setCity(city);

        session.save(users);
        return users.getUserName() != null;
    }

    @Override
    public boolean changePassword(String userName, String password) {
        int rowsUpdated = session.createQuery("UPDATE User user SET user.password = :password WHERE user.userName =:userName")
                .setParameter("password", password)
                .setParameter("userName", userName)
                .executeUpdate();

        return rowsUpdated > 0;
    }
}