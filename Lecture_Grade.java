package ict.machineproblem_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Lecture_Grade {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GradeCalculatorFrame());
    }
}

class GradeCalculatorFrame extends JFrame {
    private JTextField prelimExamLectureField, essayField, pvmField, javaBasicsField, introToJSField, lectureAbsencesField;
    private JTextField java1Field, java2Field, js1Field, js2Field, mp1Field, mp2Field, mp3Field, mp3DocuField, labAbsencesField;
    private JTextArea resultArea;

    public GradeCalculatorFrame() {
        setTitle("Grade Calculator");
        setSize(600, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        getContentPane().setBackground(new Color(230, 240, 255));

        JPanel inputPanel = new JPanel(new GridLayout(17, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        inputPanel.setBackground(new Color(230, 240, 255));
        
         JLabel lectureTitle = new JLabel("LECTURE GRADES", SwingConstants.CENTER);
        lectureTitle.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(lectureTitle);
        inputPanel.add(new JLabel());
         
        inputPanel.add(new JLabel("Lecture Prelim Exam Score:"));
        prelimExamLectureField = new JTextField();
        inputPanel.add(prelimExamLectureField);
        
        inputPanel.add(new JLabel("Essay Score:"));
        essayField = new JTextField();
        inputPanel.add(essayField);

        inputPanel.add(new JLabel("PVM Score:"));
        pvmField = new JTextField();
        inputPanel.add(pvmField);

        inputPanel.add(new JLabel("Java Basics Score:"));
        javaBasicsField = new JTextField();
        inputPanel.add(javaBasicsField);

        inputPanel.add(new JLabel("Intro to JS Score:"));
        introToJSField = new JTextField();
        inputPanel.add(introToJSField);

        inputPanel.add(new JLabel("Lecture Absences:"));
        lectureAbsencesField = new JTextField();
        inputPanel.add(lectureAbsencesField);
        
        
        
         JLabel labTitle = new JLabel("LABORATORY GRADES", SwingConstants.CENTER);
        labTitle.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(labTitle);
        inputPanel.add(new JLabel());
        
        inputPanel.add(new JLabel("Java 1 Score:"));
        java1Field = new JTextField();
        inputPanel.add(java1Field);

        inputPanel.add(new JLabel("Java 2 Score:"));
        java2Field = new JTextField();
        inputPanel.add(java2Field);

        inputPanel.add(new JLabel("JS 1 Score:"));
        js1Field = new JTextField();
        inputPanel.add(js1Field);

        inputPanel.add(new JLabel("JS 2 Score:"));
        js2Field = new JTextField();
        inputPanel.add(js2Field);

        inputPanel.add(new JLabel("MP1 Score:"));
        mp1Field = new JTextField();
        inputPanel.add(mp1Field);

        inputPanel.add(new JLabel("MP2 Score:"));
        mp2Field = new JTextField();
        inputPanel.add(mp2Field);

        inputPanel.add(new JLabel("MP3 Score:"));
        mp3Field = new JTextField();
        inputPanel.add(mp3Field);

        inputPanel.add(new JLabel("MP3 (Docu) Score:"));
        mp3DocuField = new JTextField();
        inputPanel.add(mp3DocuField);

        inputPanel.add(new JLabel("Lab Absences:"));
        labAbsencesField = new JTextField();
        inputPanel.add(labAbsencesField);

        JButton calculateButton = new JButton("Calculate Grade");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(230, 240, 255));
        buttonPanel.add(calculateButton);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.BOLD, 14));
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        resultPanel.setBackground(new Color(230, 240, 255));
        resultPanel.add(resultArea);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(230, 240, 255));
        centerPanel.add(inputPanel, BorderLayout.CENTER);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
        
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });
        
        setVisible(true);
    }

    private void calculateGrades() {
        double prelimExamLecture = Double.parseDouble(prelimExamLectureField.getText());
        double essay = Double.parseDouble(essayField.getText());
        double pvm = Double.parseDouble(pvmField.getText());
        double javaBasics = Double.parseDouble(javaBasicsField.getText());
        double introToJS = Double.parseDouble(introToJSField.getText());
        int lectureAbsences = Integer.parseInt(lectureAbsencesField.getText());
        
        double prelimQuizzes = (essay + pvm + javaBasics + introToJS) / 4;
        double lectureAttendance = Math.max(0, 100 - (lectureAbsences * 10));
        double prelimClassStandingLecture = (0.6 * prelimQuizzes) + (0.4 * lectureAttendance);
        double lecturePrelimGrade = (0.6 * prelimExamLecture) + (0.4 * prelimClassStandingLecture);

        double java1 = Double.parseDouble(java1Field.getText());
        double java2 = Double.parseDouble(java2Field.getText());
        double js1 = Double.parseDouble(js1Field.getText());
        double js2 = Double.parseDouble(js2Field.getText());
        double prelimExamLab = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);

        double mp1 = Double.parseDouble(mp1Field.getText());
        double mp2 = Double.parseDouble(mp2Field.getText());
        double mp3 = Double.parseDouble(mp3Field.getText());
        double mp3Docu = Double.parseDouble(mp3DocuField.getText());
        int labAbsences = Integer.parseInt(labAbsencesField.getText());
        
        double labWork = (mp1 + mp2 + mp3 + mp3Docu) / 4;
        double labAttendance = Math.max(0, 100 - (labAbsences * 10));
        double prelimClassStandingLab = (0.6 * labWork) + (0.4 * labAttendance);
        double labPrelimGrade = (0.6 * prelimExamLab) + (0.4 * prelimClassStandingLab);

        resultArea.setText(String.format("Lecture Prelim Grade: %.2f\nLaboratory Prelim Grade: %.2f", lecturePrelimGrade, labPrelimGrade));
    }
}