package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.*;

@Repository
public class StudentRepository {
    HashMap<String, Student> studentHashMap = new HashMap<>();
    HashMap<String, Teacher> teacherHashMap = new HashMap<>();
    Map<Teacher, List<Student>> pairHashMap = new HashMap<>();

    void addStudent(String name, Student student) {
        studentHashMap.put(name, student);
    }

    void addTeacher(String name, Teacher teacher) {
        teacherHashMap.put(name, teacher);
    }

    void pairStudentTeacher(String studentName, String teacherName) {
        if (studentHashMap.containsKey(studentName) && teacherHashMap.containsKey(teacherName)) {
            Student student = studentHashMap.get(studentName);
            Teacher teacher = teacherHashMap.get(teacherName);

            if (!pairHashMap.containsKey(teacher)) {
                List<Student> studentList = new ArrayList<>();
                studentList.add(student);
                teacher.setNumberOfStudents(teacher.getNumberOfStudents() + 1);
                pairHashMap.put(teacher, studentList);
            } else {
                List<Student> studentList = pairHashMap.get(teacher);
                for (Student student1 : studentList) {
                    if(student1.getName().equalsIgnoreCase(studentName))
                        return;
                }

                studentList.add(student);
                teacher.setNumberOfStudents(teacher.getNumberOfStudents() + 1);
                pairHashMap.put(teacher, studentList);

            }
        }
    }

    Student getStudentByName(String name) {
        return studentHashMap.get(name);
    }

    Teacher getTeacherByName(String name) {
        return teacherHashMap.get(name);
    }

    List<String> getStudentsByTeacherName(String teacherName) {
        Teacher teacher = teacherHashMap.get(teacherName);
        List<String> studentList = new ArrayList<>();
        if (!pairHashMap.containsKey(teacher)) {
            return studentList;
        } else {
            List<Student> originalList = pairHashMap.get(teacher);
            for(Student student : originalList)
                studentList.add(student.getName());
            return studentList;
        }
    }

    List<String> getAllStudents() {
        List<String> studentList = new ArrayList<>();
        for (Student student : studentHashMap.values()) {
            studentList.add(student.getName());
        }
        return studentList;
    }

    void deleteTeacherByName(String teacherName) {
        Teacher teacher = teacherHashMap.get(teacherName);
        List<Student> studentList = pairHashMap.get(teacher);
        for (Student student : studentList) {
            studentHashMap.remove(student.getName());
        }
        pairHashMap.remove(teacher);
        teacherHashMap.remove(teacherName);
    }

    void deleteAllTeachers() {
        teacherHashMap = new HashMap<>();
        pairHashMap = new HashMap<>();
    }

}
