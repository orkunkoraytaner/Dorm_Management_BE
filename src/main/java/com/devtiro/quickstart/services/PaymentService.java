package com.devtiro.quickstart.services;

import com.devtiro.quickstart.entity.Payment;
import com.devtiro.quickstart.entity.Student;
import com.devtiro.quickstart.repository.PaymentRepository;
import com.devtiro.quickstart.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {


    private PaymentRepository paymentRepository;
    private StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, StudentRepository studentRepository)
    {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    public List<Payment> getAllPaymentForStudent(Long studentId)
    {
        return paymentRepository.findByStudentId(studentId);
    }

    public Optional<Payment> createPayment(Long studentId, Payment payment)
    {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isPresent())
        {
            payment.setStudent(studentOptional.get());
            return Optional.of(paymentRepository.save(payment));
        }
        return Optional.empty();
    }


    public void deletePayment(Long paymentId)
    {
        paymentRepository.deleteById(paymentId);
    }





}
