package com.rgowdah.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		//create session factory
		SessionFactory sessionFactory=new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Student.class).buildSessionFactory();
		//create session
		Session session=sessionFactory.getCurrentSession();
		try{
			int studentId=1;
			//delete student object
			//start transaction
			session.beginTransaction();
			//delete student based on student id:primary key
			System.out.println("Deleting the student with student id: "+studentId);
			Student student=session.get(Student.class, studentId);
			session.delete(student);
			//commit transaction
			session.getTransaction().commit();
			//delete students			
			System.out.println("update email for all students");
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Student where id=2").executeUpdate();
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}finally{
			sessionFactory.close();
		}
	}
}
