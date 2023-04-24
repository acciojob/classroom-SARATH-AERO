package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    void addStudent(Student student) {
        String name = student.getName();
        studentRepository.addStudent(name, student);
    }

    void addTeacher(Teacher teacher) {
        String name = teacher.getName();
        studentRepository.addTeacher(name, teacher);
    }

    void addStudentTeacherPair(String studentName, String teacherName) {
        Student student = studentRepository.studentHashMap.get(studentName);
        Teacher teacher = studentRepository.teacherHashMap.get(teacherName);
        studentRepository.pairStudentTeacher(studentName,teacherName);
    }

    Student getStudentByName(String name) {
        return studentRepository.getStudentByName(name);
    }

    Teacher getTeacherByName(String name) {
        return studentRepository.getTeacherByName(name);
    }

    List<String> getStudentsByTeacherName(String teacherName) {
        return studentRepository.getStudentsByTeacherName(teacherName);
    }

    List<String> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    void deleteTeacherByName(String name) {
        studentRepository.deleteTeacherByName(name);
    }

    void deleteAllTeachers() {
        studentRepository.deleteAllTeachers();
    }
}
