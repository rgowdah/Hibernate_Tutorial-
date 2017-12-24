package com.rgowdah.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rgowdah.hibernate.entity.Student;

public class ReadStudentDemo {
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
			Student student=new Student("Daffy","duck","daffy@gmail.com",theDateOfBirth);
			//start transaction
			session.beginTransaction();
			//save student object
			System.out.println("Saving the student");
			session.save(student);
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Saved student, Generated ID is :"+student.getId());
			session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			System.out.println("Getting student using ID"+student.getId());
			Student student2=session.get(Student.class, student.getId());
			System.out.println(student2);
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
