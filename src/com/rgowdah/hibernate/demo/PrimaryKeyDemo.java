package com.rgowdah.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class PrimaryKeyDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			//create student object
			String theDateOfBirthStr = "31/12/1998";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
			System.out.println("Creating a 3 new student objects");
			Student student=new Student("hij","klm","klm@asu.edu",theDateOfBirth);
			Student student1=new Student("abc","efg","efg@asu.edu",theDateOfBirth);
			Student student2=new Student("nop","qrs","qrs@asu.edu",theDateOfBirth);			
			//start transaction
			session.beginTransaction();
			//save student object
			System.out.println("Saving the student");
			session.save(student);
			session.save(student1);
			session.save(student2);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			sessionFactory.close();
		}
	}
}
