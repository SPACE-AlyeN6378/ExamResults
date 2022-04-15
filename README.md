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

__Filename:__ student_info.csv

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
__Filename:__ paper_info.csv

*Format:*
```
PAPER_CODE:NAME:PAPER_NUMBER:HAS_THEORY:HAS_PRACTICAL:THEORY_MARKS:PRACTICAL_MARKS
```

If the subject does not have papers, PAPER_NUMBER will be 0. THEORY_MARKS and PRACTICAL_MARKS are total marks in those portions.

*Example:*
```
101:Math:0:T:F:100:0
201:Physics I:1:T:T:60:40
202:Physics II:2:T:T:60:40
301:Chemistry I:1:T:T:70:30
302:Chemistry II:2:T:T:70:30
```

### Subject-Paper Mapping
__Filename:__ subject_paper.csv

There is s one-to-many mapping between subject and papers.

*Format:*
```
SUBJECT_CODE:PAPER_CODE
```

*Example:*
```
1000:101
2000:201
2000:202
3000:301
3000:302
```

### Subject Names
__Filename__: subject_name.csv

There is a one-to-one mapping between subject code and name.

*Format:*
```
SUBJECT_CODE:NAME
```

*Example:*
```
1000:Mathematics
2000:Physics
3000:Chemistry
```

### Marks Obtained

__Filename:__ marks.csv

*Format:*
```
ROLL:SUBJECT_CODE:IS_THEORY:IS_PRACTICAL:MARKS
```

*Example:*
```
3:101:T:F:95
3:201:T:T:50
3:201:F:T:30
```

## Output Files

After processing the input and applying the business rules, you will need to produce two files to describe the results of the students.

### Results File

**Filename:** result.csv

_Format:_
```
ROLL:STUDENT_NAME:TOTAL_MARKS:HAS_PASSED
```

The `HAS_PASSED` field is a boolean, 'T' if the student passed, 'F' if he/she has not passed.

_Example:_
```
3:Nargis Akhter:400:T
4:Sadiq Khan:200:F
```

### Mark Sheet

**Filename:** mark_sheet.csv

_Format:_
```
ROLL:SUBJECT_CODE:TOTAL_MARKS_IN_SUBJECT:HAS_PASSED
```

The `HAS_PASSED` field is a boolean, 'T' if the student passed, 'F' if he/she has not passed.

_Example:_
```
3:1000:80:T
3:2000:160:T
3:3000:160:T
4:1000:40:F
4:2000:80:F
4:2000:80:F
```

## Rules of this Assignment
* No external libraries are allowed. Only Java Core Library is used. 
* Use classes and objects, that will help hold the data
* Make the program write the results on a new CSV File
* It is a command line program
* Does not accept any input from the user
* Printing some status/information can be done using `System.out.println` while processing

## Feedback from the Supervisor
The supervisor told me that my code has improved a lot! But there are a few problems.
1. **Technical** - when you open a stream from a file, you need to close it. You should use the "try resource" control structure to properly close it, automatically. The way you closed now is buggy.
2. **Major** - Your Student and Subject constructors are doing way too much work. These two objects should not need to know the structure of the input files and all. Revise the Object Oriented Design Principles. One of the principles you violated severely is SRP.
