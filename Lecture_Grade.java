package ict.machineproblem_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JavaApplication1 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculatorFrame());
    }
}

class GradeCalculatorFrame extends JFrame {
    private JTextField prelimExamLectureField, essayField, pvmField, javaBasicsField, introToJSField, lectureAbsencesField;
    private JTextField java1Field, java2Field, js1Field, js2Field, mp1Field, mp2Field, mp3Field, mp3DocuField, labAbsencesField;
    private JTextArea resultArea, formulaArea;

    public GradeCalculatorFrame() {
        // Set up the frame
        setTitle("Grade Calculator");
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Disable resizing
        setLayout(new BorderLayout());

        // Set a modern look and feel
        UIManager.put("Panel.background", new Color(45, 45, 45));
        UIManager.put("TextArea.background", new Color(60, 63, 65));
        UIManager.put("TextArea.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(70, 130, 180)); // Steel Blue
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(17, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        inputPanel.setBackground(new Color(45, 45, 45));

        JLabel lectureTitle = new JLabel("LECTURE", SwingConstants.CENTER);
        lectureTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lectureTitle.setForeground(Color.WHITE);
        inputPanel.add(lectureTitle);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Prelim Exam:"));
        prelimExamLectureField = new JTextField(4);
        inputPanel.add(prelimExamLectureField);

        inputPanel.add(new JLabel("Essay Score:"));
        essayField = new JTextField(4);
        inputPanel.add(essayField);

        inputPanel.add(new JLabel("PVM Score:"));
        pvmField = new JTextField(4);
        inputPanel.add(pvmField);

        inputPanel.add(new JLabel("Java Basics Score:"));
        javaBasicsField = new JTextField(4);
        inputPanel.add(javaBasicsField);

        inputPanel.add(new JLabel("Intro to JS Score:"));
        introToJSField = new JTextField(4);
        inputPanel.add(introToJSField);

        inputPanel.add(new JLabel("Lecture Absences:"));
        lectureAbsencesField = new JTextField(4);
        inputPanel.add(lectureAbsencesField);

        JLabel labTitle = new JLabel("LABORATORY", SwingConstants.CENTER);
        labTitle.setFont(new Font("Arial", Font.BOLD, 14));
        labTitle.setForeground(Color.WHITE);
        inputPanel.add(labTitle);
        inputPanel.add(new JLabel());

        inputPanel.add(new JLabel("Java 1 Score:"));
        java1Field = new JTextField(4);
        inputPanel.add(java1Field);

        inputPanel.add(new JLabel("Java 2 Score:"));
        java2Field = new JTextField(4);
        inputPanel.add(java2Field);

        inputPanel.add(new JLabel("JS 1 Score:"));
        js1Field = new JTextField(4);
        inputPanel.add(js1Field);

        inputPanel.add(new JLabel("JS 2 Score:"));
        js2Field = new JTextField(4);
        inputPanel.add(js2Field);

        inputPanel.add(new JLabel("MP1 Score:"));
        mp1Field = new JTextField(4);
        inputPanel.add(mp1Field);

        inputPanel.add(new JLabel("MP2 Score:"));
        mp2Field = new JTextField(4);
        inputPanel.add(mp2Field);

        inputPanel.add(new JLabel("MP3 Score:"));
        mp3Field = new JTextField(4);
        inputPanel.add(mp3Field);

        inputPanel.add(new JLabel("MP3 (Docu) Score:"));
        mp3DocuField = new JTextField(4);
        inputPanel.add(mp3DocuField);

        inputPanel.add(new JLabel("Lab Absences:"));
        labAbsencesField = new JTextField(4);
        inputPanel.add(labAbsencesField);

        JButton calculateButton = new JButton("Calculate Grade");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(45, 45, 45));
        buttonPanel.add(calculateButton);

        // Result area
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(60, 63, 65));
        resultArea.setForeground(Color.WHITE);
        resultArea.setFont(new Font("Arial", Font.BOLD, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.WHITE, 2), "Results", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), Color.WHITE));

        // Formula area
        formulaArea = new JTextArea(10, 20);
        formulaArea.setEditable(false);
        formulaArea.setBackground(new Color(60, 63, 65));
        formulaArea.setForeground(Color.WHITE);
        formulaArea.setFont(new Font("Arial", Font.PLAIN, 12));
        formulaArea.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.WHITE, 2), "Formulas", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), Color.WHITE));

        // Set the result area to fill the remaining space
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        formulaArea.setLineWrap(true);
        formulaArea.setWrapStyleWord(true);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBackground(new Color(45, 45, 45));
        resultPanel.add(resultArea, BorderLayout.CENTER);
        resultPanel.add(formulaArea, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(45, 45, 45));
        centerPanel.add(inputPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH); // Add result area to the bottom

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        setVisible(true);
    }

    private void calculateGrades() {
        try {
            double prelimExamLecture = Double.parseDouble(prelimExamLectureField.getText());
            double essay = Double.parseDouble(essayField.getText());
            double pvm = Double.parseDouble(pvmField.getText());
            double javaBasics = Double.parseDouble(javaBasicsField.getText());
            double introToJS = Double.parseDouble(introToJSField.getText());
            int lectureAbsences = Integer.parseInt(lectureAbsencesField.getText());

            // Validate inputs
            if (!validateInput(prelimExamLecture, 0, 100) || 
                !validateInput(essay, 0, 100) || 
                !validateInput(pvm, 0, 100) || 
                !validateInput(javaBasics, 0, 100) || 
                !validateInput(introToJS, 0, 100) || 
                !validateInput(lectureAbsences, 0, 10)) {
                resultArea.setText("Please enter valid values for lecture inputs.");
                formulaArea.setText("");
                return;
            }

            double prelimQuizzes = (essay + pvm + javaBasics + introToJS) / 4;
            double lectureAttendance = Math.max(0, 100 - (lectureAbsences * 10));
            double prelimClassStandingLecture = (0.6 * prelimQuizzes) + (0.4 * lectureAttendance);
            double lecturePrelimGrade = (0.6 * prelimExamLecture) + (0.4 * prelimClassStandingLecture);

            double java1 = Double.parseDouble(java1Field.getText());
            double java2 = Double.parseDouble(java2Field.getText());
            double js1 = Double.parseDouble(js1Field.getText());
            double js2 = Double.parseDouble(js2Field.getText());

            // Validate lab inputs
            if (!validateInput(java1, 0, 100) || 
                !validateInput(java2, 0, 100) || 
                !validateInput(js1, 0, 100) || 
                !validateInput(js2, 0, 100)) {
                resultArea.setText("Please enter valid values for lab inputs.");
                formulaArea.setText("");
                return;
            }

            double prelimExamLab = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);

            double mp1 = Double.parseDouble(mp1Field.getText());
            double mp2 = Double.parseDouble(mp2Field.getText());
            double mp3 = Double.parseDouble(mp3Field.getText());
            double mp3Docu = Double.parseDouble(mp3DocuField.getText());
            int labAbsences = Integer.parseInt(labAbsencesField.getText());

            // Validate MP inputs
            if (!validateInput(mp1, 0, 100) || 
                !validateInput(mp2, 0, 100) || 
                !validateInput(mp3, 0, 100) || 
                !validateInput(mp3Docu, 0, 100) || 
                !validateInput(labAbsences, 0, 10)) {
                resultArea.setText("Please enter valid values for MP inputs.");
                formulaArea.setText("");
                return;
            }

            double labWork = (mp1 + mp2 + mp3 + mp3Docu) / 4;
            double labAttendance = Math.max(0, 100 - (labAbsences * 10));
            double prelimClassStandingLab = (0.6 * labWork) + (0.4 * labAttendance);
            double labPrelimGrade = (0.6 * prelimExamLab) + (0.4 * prelimClassStandingLab);

            // Prepare results
            String feedbackLecture = getFeedback(lecturePrelimGrade);
            String feedbackLab = getFeedback(labPrelimGrade);

            resultArea.setText(String.format("Lecture Prelim Grade: %.2f - %s\nLaboratory Prelim Grade: %.2f - %s",
                    lecturePrelimGrade, feedbackLecture, labPrelimGrade, feedbackLab));

            // Display formulas
            formulaArea.setText(String.format(
                "Lecture Prelim Grade = (0.6 * Prelim Exam) + (0.4 * Prelim Class Standing)\n" +
                "Prelim Class Standing = (0.6 * Prelim Quizzes) + (0.4 * Attendance)\n" +
                "Prelim Quizzes = (Essay + PVM + Java Basics + Intro to JS) / 4\n" +
                "Attendance = 100 - (10 * Lecture Absences)\n\n" +
                "Lab Prelim Grade = (0.6 * Prelim Exam Lab) + (0.4 * Prelim Class Standing Lab)\n" +
                "Prelim Exam Lab = (0.2 * Java 1) + (0.3 * Java 2) + (0.2 * JS 1) + (0.3 * JS 2)\n" +
                "Prelim Class Standing Lab = (0.6 * Lab Work) + (0.4 * Attendance)\n" +
                "Lab Work = (MP1 + MP2 + MP3 + MP3 (Docu)) / 4\n" +
                "Attendance = 100 - (10 * Lab Absences)"));
        } catch (NumberFormatException e) {
            resultArea.setText("Please enter valid numeric values.");
            formulaArea.setText("");
        }
    }

    private boolean validateInput(double value, double min, double max) {
        return value >= min && value <= max;
    }

    private String getFeedback(double grade) {
        if (grade >= 90) {
            return "Excellent!";
        } else if (grade >= 80) {
            return "Good Job!";
        } else if (grade >= 75) {
            return "You Passed!";
        } else {
            return "You need to improve.";
        }
    }
}
