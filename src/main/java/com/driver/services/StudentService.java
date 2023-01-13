package com.driver.services;

import com.driver.models.Card;
import com.driver.models.CardStatus;
import com.driver.models.Student;
import com.driver.repositories.CardRepository;
import com.driver.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {


    @Autowired
    CardService cardService4;

    @Autowired
    StudentRepository studentRepository4;

    @Autowired
    CardRepository cardRepository;

    public Student getDetailsByEmail(String email){

        Student student = studentRepository4.findByEmailId(email);

        return student;
    }

    public Student getDetailsById(int id){
        Student student = studentRepository4.findById(id).get();

        return student;
    }

    public void createStudent(Student student){
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        student.setCard(card);
        card.setStudent(student);
        cardRepository.save(card);
    }

    public void updateStudent(Student student){

       int id =studentRepository4.updateStudentDetails(student);
    }

    public void deleteStudent(int id){
        //Delete student and deactivate corresponding card
        Student student = studentRepository4.findById(id).get();
        Card card= student.getCard();
        cardRepository.delete(card);
//        studentRepository4.delete(student);
    }
}
