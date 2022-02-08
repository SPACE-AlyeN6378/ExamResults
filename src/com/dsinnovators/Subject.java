package com.dsinnovators;

import java.lang.reflect.Array;
import java.util.*;

public class Subject {

    // HELPER FUNCTIONS ------------------------------------------------------
    private static boolean charToBool(char ch) throws RuntimeException {
        if (ch == 'T') { return true; }
        else if (ch == 'F') { return false; }
        else {
            throw new RuntimeException("Invalid character: T and F only!");
        }
    }

    private static boolean isItPassMark(int marksObtained, int totalMarks) {
        // System.out.println(marksObtained+"/"+totalMarks);
        if (marksObtained > totalMarks) {
            throw new ArithmeticException("Marks obtained exceed full marks");
        }
        int minPassingMark = totalMarks / 2;
        return marksObtained >= minPassingMark;
    }
    // -----------------------------------------------------------------------

    // Attributes
    private int subjectCode = 0;
    private String name = "Unknown";
    private boolean[] hasTheoryPractical = {false, false}; // First - theory, Second - practical
    private ArrayList<Integer[]> papersVStotalMarks = new ArrayList<>(); // Total marks: Paper -> Theory, Practical
    private ArrayList<Integer[]> papersVSmarksObtained = new ArrayList<>(); // Marks Obtained: Paper -> Theory, Practical
    private int sumMarksObtained = 0;
    private boolean passed = true;

    // Constructor
    public Subject(int subCodeIn, String nameIn, Map<Integer, Integer> subject_paper, List<String[]> paper_info) {
        // 1. Assign the attributes
        subjectCode = subCodeIn;
        name = nameIn;
        paper_info.stream()
                .filter(arr -> subCodeIn == subject_paper.get(Integer.parseInt(arr[0])))
                .forEach(arr -> {
                        hasTheoryPractical[0] = charToBool(arr[3].toCharArray()[0]);
                        hasTheoryPractical[1] = charToBool(arr[4].toCharArray()[0]);
                        papersVStotalMarks.add(new Integer[]{Integer.parseInt(arr[0]),
                                Integer.parseInt(arr[5]), Integer.parseInt(arr[6])});
                        papersVSmarksObtained.add(new Integer[]{Integer.parseInt(arr[0]), 0, 0});
                });
    }

    // Sets some marks obtained
    public void setMarks(int paperCode, int marksInput, boolean isPractical) {
        for (int i=0; i<papersVSmarksObtained.size(); i++) {
            if (papersVSmarksObtained.get(i)[0] == paperCode) {
                if (!isPractical) {
                    if (hasTheoryPractical[0]) {
                        papersVSmarksObtained.get(i)[1] = marksInput;
                        passed = passed && isItPassMark(marksInput, papersVStotalMarks.get(i)[1]);
                    }
                } else {
                    if (hasTheoryPractical[1]) {
                        papersVSmarksObtained.get(i)[2] = marksInput;
                        passed = passed && isItPassMark(marksInput, papersVStotalMarks.get(i)[2]);
                    }
                }
                sumMarksObtained += marksInput;
            }
        }
    }

    public void print() {
        System.out.println("Subject Code: "+subjectCode);
        System.out.println("Name: "+name);
        papersVStotalMarks.forEach(arr -> System.out.println(Arrays.toString(arr)));
        papersVSmarksObtained.forEach(arr -> System.out.println(Arrays.toString(arr)));
        System.out.println(passed ? "Passed!" : "Failed!");
    }

    public int getSubjectCode() { return subjectCode; }
    public int getSumMarksObtained() { return sumMarksObtained; }
    public boolean getPassed() { return passed; }
}
