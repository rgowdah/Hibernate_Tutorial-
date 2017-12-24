package com.rgowdah.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{			
			//start transaction
			session.beginTransaction();
			//query students
			List<Student> theStudents=session.createQuery("from Student").getResultList();
			displayStudents(theStudents);
			//query students last name duck
			theStudents=session.createQuery("from Student s where s.lastName='duck'").getResultList();
			//display student
			System.out.println("The students with last name duck");
			displayStudents(theStudents);
			//query students with last name duck or first name abc
			theStudents=session.createQuery("from Student s where s.lastName='duck' OR s.firstName='abc'").getResultList();
			//display student
			System.out.println("The students with last name duck or first name abc");
			displayStudents(theStudents);
			//query students where email like '%asu.edu'
			theStudents=session.createQuery("from Student s where s.email LIKE '%asu.edu'").getResultList();
			//display student
			System.out.println("The students with email like '%asu.edu'");
			displayStudents(theStudents);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally{
			sessionFactory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student student : theStudents) {
			System.out.println(student);
		}
	}
}
