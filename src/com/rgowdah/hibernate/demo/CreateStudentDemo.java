package com.rgowdah.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class CreateStudentDemo {
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
			System.out.println("Creating a new student object");
			Student student=new Student("Raghu","veer","raghuvee@asu.edu",theDateOfBirth);
			//start transaction
			session.beginTransaction();
			//save student object
			System.out.println("Saving the student");
			session.save(student);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception exc) {
            exc.printStackTrace();
        }finally{
			sessionFactory.close();
		}
	}
}
