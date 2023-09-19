/*code by Arnav Kumar
          BSC CS , MITWPU , KOTHRUD
		  TASK 2:  STUDENT GRADE CALCULATOR
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    private JTextField[] subjectFields;
    private JLabel[] subjectGradeLabels; // New array for subject grade labels
    private JButton calculateButton;
    private JLabel totalMarksLabel;
    private JLabel averagePercentageLabel;
    private JLabel gradeLabel;
    private JLabel gpaLabel;
    private JLabel cgpaLabel;

    public StudentGradeCalculator() {
        setTitle("Student Grade Calculator");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 3, 10, 10));

        JLabel[] subjectLabels = new JLabel[6];
        subjectFields = new JTextField[6];
        subjectGradeLabels = new JLabel[6]; // Initialize the array

        for (int i = 0; i < 6; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + " Marks:");
            subjectFields[i] = new JTextField();
            subjectGradeLabels[i] = new JLabel(); // Initialize each subject's grade label
            add(subjectLabels[i]);
            add(subjectFields[i]);
            add(subjectGradeLabels[i]); // Add the grade label
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");
        gpaLabel = new JLabel("GPA: ");
        cgpaLabel = new JLabel("CGPA: ");

        add(calculateButton);
        add(new JLabel());

        add(totalMarksLabel);
        add(averagePercentageLabel);
        add(gradeLabel);
        add(gpaLabel);
        add(cgpaLabel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            int totalMarks = 0;
            int numSubjects = subjectFields.length;
            double[] subjectGPA = new double[numSubjects];

            for (int i = 0; i < numSubjects; i++) {
                String marksStr = subjectFields[i].getText();
                int marks = Integer.parseInt(marksStr);
                totalMarks += marks;
                subjectGPA[i] = calculateGPA(marks);

                double subjectAveragePercentage = (double) marks / 100 * 100;
                String subjectGrade = calculateSubjectGrade(subjectAveragePercentage); // Calculate subject grade
                subjectGradeLabels[i].setText("Grade: " + subjectGrade); // Set the grade label
            }

            double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;

            String grade;
            if (averagePercentage >= 90) {
                grade = "A";
            } else if (averagePercentage >= 80) {
                grade = "B";
            } else if (averagePercentage >= 70) {
                grade = "C"; 
            } else if (averagePercentage >= 60) {
                grade = "D";
            } else if (averagePercentage >= 50) {
                grade = "E";
			} else {
                grade = "F";
            }

            double gpa = calculateGPA((int) averagePercentage); // Corrected line
            double cgpa = calculateCGPA(subjectGPA);

            totalMarksLabel.setText("Total Marks: " + totalMarks);
            averagePercentageLabel.setText("Average Percentage: " + String.format("%.2f", averagePercentage) + "%");
            gradeLabel.setText("Grade: " + grade);
            gpaLabel.setText("GPA: " + String.format("%.2f", gpa));
            cgpaLabel.setText("CGPA: " + String.format("%.2f", cgpa));
        }
    }

    private String calculateSubjectGrade(double averagePercentage) {
    if (averagePercentage >= 90) {
        return "A";
    } else if (averagePercentage >= 80) {
        return "B";
    } else if (averagePercentage >= 70) {
        return "C";
    } else if (averagePercentage >= 60) {
        return "D";
    } else if (averagePercentage >= 50) {
        return "E";
    } else {
        return "F";
    }
}

    private double calculateGPA(int marks) {
        if (marks >= 95) {
            return 10.0;
        } else if (marks >= 90) {
            return 9.0;
        } else if (marks >= 85) {
            return 8.0;
        } else if (marks >= 80) {
            return 7.0;
		} else if (marks >= 75) {
            return 6.0;
        } else if (marks >= 70) {
		    return 5.0; 
		} else if (marks >= 65) {
		    return 4.0; 
		} else if (marks >= 60) {
		    return 3.0; 
		} else if (marks >= 55) {
		    return 2.0; 
		} else if (marks >= 50) {
		    return 1.0; 
		}
		else {
            return 0.0;
        }
    }

    private double calculateCGPA(double[] gpas) {
        double sumGPA = 0.0;
        for (double gpa : gpas) {
            sumGPA += gpa;
        }
        return sumGPA / gpas.length;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator calculator = new StudentGradeCalculator();
            calculator.setVisible(true);
        });
    }
}
