# Learning Assignment - Exam Results

The purpose of this assignment is to learn how to do object-oriented programming, practice coding conventions and data structures.

This Java program processes the results of an examination. There are three categories of files:
* Student information
* Subject and paper information
* Marks obtained

Each student gives exams for three subjects—Math, Physics and Chemistry. Math has one paper, while Physics and Chemistry have two papers each. In each paper, there are two parts—theoretical and practical. You have to tabulate each mark and produce the results in alphabetical order of students.

## Criteria For Passing
The student has to get at at least 50% of the total marks of a portion to pass in that portion (both theoretical and practical)
1. The student has to pass in both portions (theoretical and practical) to pass the paper
2. If the subject has more than one paper, the student has to pass in all the papers
3. The student has to pass in all 3 subjects to pass the exam

## File Format
Please note that all the CSV files are colon-separated instead of comma.

### Student Information

_Filename:_ student_info.csv

*Format:*
```
ROLL:NAME
```

*Example:*
```
32:Syed Abdullah Khan
25:Mehedi Hasan
```

### Paper Information
_Filename:_ paper_info.csv
