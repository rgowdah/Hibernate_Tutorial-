package com.rgowdah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			int studentId=1;
			//update student object
			//start transaction
			session.beginTransaction();
			//retrieve student based on student id:primary key
			System.out.println("Getting the student with student id: "+studentId);
			Student student=session.get(Student.class, studentId);
			System.out.println("updating the student");
			student.setFirstName("Scooby");
			//commit transaction
			session.getTransaction().commit();
			//update email for all students			
			System.out.println("update email for all students");
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student set email='dummy@gmail.com'").executeUpdate();
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally{
			sessionFactory.close();
		}
	}
}
