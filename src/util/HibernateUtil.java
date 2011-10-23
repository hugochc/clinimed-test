package util;

import model.Paciente;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory factory;

	static {	
		Configuration cfg = new Configuration()
		.setProperty ("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
		.setProperty ("hibernate.connection.driver_class", "org.h2.Driver")
		.setProperty ("hibernate.connection.url", "jdbc:h2:mem:clinimed")
		.setProperty ("hibernate.connection.username", "sa")
		.setProperty ("hibernate.connection.password", "")
		.setProperty ("hibernate.connection.pool_size", "1")
		.setProperty ("hibernate.connection.autocommit", "true")
		.setProperty ("hibernate.cache.provider_class", "org.hibernate.cache.HashtableCacheProvider")
		.setProperty ("hibernate.hbm2ddl.auto", "create-drop")
		.setProperty ("hibernate.show_sql", "true")
		.setProperty ("hibernate.format_sql", "true")
		.addAnnotatedClass (Paciente.class);
		
		factory = cfg.buildSessionFactory();		
	}

	public static Session getSession() {  
		return factory.openSession();  
	}

}
