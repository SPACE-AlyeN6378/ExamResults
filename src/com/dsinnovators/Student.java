package com.dsinnovators;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Student {
    // HELPER FUNCTIONS ----------------------------------------------------------------
    private static boolean theoryOrPrac(String is_theory, String is_practical) {
        if (is_theory.equals("F") && is_practical.equals("T")) {
            return true;  // Practical
        }
        else if (is_theory.equals("T") && is_practical.equals("F")) {
            return false; // Theory
        }
        else {
            throw new RuntimeException("Invalid input");
        }
    }
    // ---------------------------------------------------------------------------------

    // Attributes
    private int rollNumber;
    private String name;
    private ArrayList<Subject> subjects = new ArrayList<>();
    private int totalMarksObtained = 0;
    private boolean allPassed = true;

    // Constructor
    public Student(int rollIn, String nameIn, Map<Integer, String> mapOfSubjects, Map<Integer, Integer> subject_paper, List<String[]> paper_info, List<String[]> marks_input) {

        rollNumber = rollIn;
        name = nameIn;

        ArrayList<Integer> subjectKey = new ArrayList<>(mapOfSubjects.keySet());
        Collections.sort(subjectKey);

        for (int key: subjectKey) {
            Subject subject = new Subject(key, mapOfSubjects.get(key), subject_paper, paper_info);

            marks_input.stream()
                    .filter(arr -> Integer.parseInt(arr[0]) == rollNumber && subject_paper.get(Integer.parseInt(arr[1])) == key)
                    .forEach(arr -> {
                        // System.out.println(arr[1]+":"+arr[4]+":"+arr[2]+":"+arr[3]);
                        subject.setMarks(Integer.parseInt(arr[1]), Integer.parseInt(arr[4]), theoryOrPrac(arr[2], arr[3]));
                    });

            allPassed = allPassed && subject.getPassed();
            totalMarksObtained += subject.getSumMarksObtained();
            subjects.add(subject);
        }
    }

    public String result_getCSVrow() {
        String row = rollNumber +":"+ name +":"+ totalMarksObtained +":"+ (allPassed ? "T" : "F")+"\n";
        return row;
    }

    public String markSheet_getCSVrows() {
        StringBuilder rows = new StringBuilder();
        for (Subject sub: subjects) {
            String row = rollNumber +":"+ sub.getSubjectCode() +":"+ sub.getSumMarksObtained() +":"+ (sub.getPassed() ? "T" : "F");
            rows.append(row).append("\n");
        }
        return rows.toString();
    }
}
