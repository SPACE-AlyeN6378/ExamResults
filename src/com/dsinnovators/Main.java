package com.dsinnovators;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {



    public static List<String[]> getTable(String filename) throws IOException {
        Stream<String> row = Files.lines(Paths.get(filename));
        List<String[]> table = row.map(str -> str.split(":")).toList();
        row.close();
        return table;
    }

    // This getMap method returns an int to int hashmap
    // Parameters: file name, and boolean to check whether the elements need to be added in reverse or not
    public static Map<Integer, Integer> getMap(String filename, boolean reverse) throws IOException {
        Stream<String> pair = Files.lines(Paths.get(filename));
        Map<Integer, Integer> map = new HashMap<>();

        if (reverse) {
            map = pair.map(str -> str.split(":"))
                    .collect(Collectors.toMap(x -> Integer.parseInt(x[1]), x -> Integer.parseInt(x[0])));
        }
        else {
            map = pair.map(str -> str.split(":"))
                    .collect(Collectors.toMap(x -> Integer.parseInt(x[0]), x -> Integer.parseInt(x[1])));
        }
        pair.close();
        return map;
    }

    // This getMap method returns an int to String hashmap
    // Parameters: file name, and boolean to check whether the elements need to be added in reverse or not
    public static Map<Integer, String> getMap2(String filename, boolean reverse) throws IOException {
        Stream<String> pair = Files.lines(Paths.get(filename));
        Map<Integer, String> map = new HashMap<>();

        if (reverse) {
            map = pair.map(str -> str.split(":"))
                    .collect(Collectors.toMap(x -> Integer.parseInt(x[1]), x -> x[0]));
        }
        else {
            map = pair.map(str -> str.split(":"))
                    .collect(Collectors.toMap(x -> Integer.parseInt(x[0]), x -> x[1]));
        }

        pair.close();
        return map;
    }

    public static void main(String[] args) throws IOException {

        // Get all the data from external files
        Map<Integer, String> studentsRolls = getMap2("student_info.csv", false);
        List<String[]> paperInfo = getTable("paper_info.csv");
        Map<Integer, Integer> subjectPaper = getMap("subject_paper.csv", true);
        Map<Integer, String> subjectName = getMap2("subject_name.csv", false);
        List<String[]> allMarks = getTable("marks.csv");

        List<Student> students = new ArrayList<>();
        Stream<String> studentInfo = Files.lines(Paths.get("student_info.csv"));
        students = studentInfo.map(x -> x.split(":"))
                .map(x -> new Student(Integer.parseInt(x[0]), x[1], subjectName, subjectPaper, paperInfo, allMarks))
                .toList();
        studentInfo.close();


        FileWriter writer = new FileWriter("output_files/results.csv");
        students.forEach(st -> {
            try {
                writer.write(st.result_getCSVrow());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println("File written successfully!");
        writer.close();

        FileWriter writer1 = new FileWriter("output_files/mark_sheet.csv");
        students.forEach(st -> {
            try {
                writer1.write(st.markSheet_getCSVrows());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("File written successfully!");
        writer1.close();


//        Student me = new Student(3, "Aly Mooltazeem", subjectName, subjectPaper, paperInfo, allMarks);
//        System.out.println(me.result_getCSVrow());
    }
}
