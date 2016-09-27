package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbUtil {
	static Logger logger = LoggerFactory.getLogger(DbUtil.class);
	private static final SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration().
				configure("lib/hibernate.cfg.xml").
				buildSessionFactory();
	}

	public static Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	public static void destory() {
		getSession().close();
	}
}
