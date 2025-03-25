package ict.machineproblem_2;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JavaApplication2 {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(GradeCalculatorFrame::new);
    }
}

class GradeCalculatorFrame extends JFrame {
    private JTextField prelimExamLectureField, essayField, pvmField, javaBasicsField, introToJSField;
    private JTextField java1Field, java2Field, js1Field, js2Field, mp1Field, mp2Field, mp3Field, mp3DocuField;
    private JTextArea resultArea, formulaArea;
    private JPanel lectureAbsencesPanel, labAbsencesPanel;

    public GradeCalculatorFrame() {
        setupFrame();
        JPanel mainPanel = createMainPanel();
        JPanel resultPanel = createResultPanel();

        add(mainPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Grade Calculator");
        setSize(700, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(new BorderLayout());

        // Set a modern look and feel
        UIManager.put("Panel.background", new Color(45, 45, 45));
        UIManager.put("TextArea.background", new Color(60, 63, 65));
        UIManager.put("TextArea.foreground", Color.WHITE);
        UIManager.put("Button.background", new Color(70, 130, 180)); // Steel Blue
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(45, 45, 45));

        JPanel lecturePanel = createLecturePanel();
        JPanel labPanel = createLabPanel();
        mainPanel.add(lecturePanel);
        mainPanel.add(labPanel);

        return mainPanel;
    }

    private JPanel createResultPanel() {
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        resultPanel.setBackground(new Color(45, 45, 45));

        resultArea = createResultArea();
        formulaArea = createFormulaArea();
        JButton calculateButton = new JButton("Calculate Grade");
        calculateButton.addActionListener(e -> calculateGrades());

        resultPanel.add(resultArea, BorderLayout.CENTER);
        resultPanel.add(Box.createRigidArea(new Dimension(0, 10)), BorderLayout.NORTH);
        resultPanel.add(formulaArea, BorderLayout.NORTH);
        resultPanel.add(calculateButton, BorderLayout.SOUTH);

        return resultPanel;
    }

    private JTextArea createResultArea() {
        JTextArea area = new JTextArea(5, 30);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.BOLD, 14));
        area.setBackground(new Color(60, 63, 65));
        area.setForeground(Color.WHITE);
        area.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.WHITE, 2), "Results", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), Color.WHITE));
        return area;
    }

    private JTextArea createFormulaArea() {
        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 12));
        area.setBackground(new Color(60, 63, 65));
        area.setForeground(Color.WHITE);
        area.setBorder(BorderFactory.createTitledBorder(
                new LineBorder(Color.WHITE, 2), "Formulas", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), Color.WHITE));
        return area;
    }

    private JPanel createLecturePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Lecture Grades", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), Color.WHITE));
        panel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = createGridBagConstraints();

        addField(panel, gbc, "Prelim Exam Score:", prelimExamLectureField = new JTextField());
        addField(panel, gbc, "Essay Score:", essayField = new JTextField());
        addField(panel, gbc, "PVM Score (max 60):", pvmField = new JTextField());
        addField(panel, gbc, "Java Basics Score (max 40):", javaBasicsField = new JTextField());
        addField(panel, gbc, "Intro to JS Score (max 40):", introToJSField = new JTextField());

        lectureAbsencesPanel = createAbsencesPanel("Lecture Absences");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across both columns
        panel.add(lectureAbsencesPanel, gbc);

        return panel;
    }

    private JPanel createLabPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), "Lab Grades", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 16), Color.WHITE));
        panel.setBackground(new Color(45, 45, 45));
        GridBagConstraints gbc = createGridBagConstraints();

        addField(panel, gbc, "Java 1 Score:", java1Field = new JTextField());
        addField(panel, gbc, "Java 2 Score:", java2Field = new JTextField());
        addField(panel, gbc, "JS 1 Score:", js1Field = new JTextField());
        addField(panel, gbc, "JS 2 Score:", js2Field = new JTextField());
        addField(panel, gbc, "MP 1 Score:", mp1Field = new JTextField());
        addField(panel, gbc, "MP 2 Score:", mp2Field = new JTextField());
        addField(panel, gbc, "MP 3 Score:", mp3Field = new JTextField());
        addField(panel, gbc, "MP 3 (Docu) Score:", mp3DocuField = new JTextField());

        labAbsencesPanel = createAbsencesPanel("Lab Absences");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span across both columns
        panel.add(labAbsencesPanel, gbc);

        return panel;
    }

    private GridBagConstraints createGridBagConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        return gbc;
    }

    private JPanel createAbsencesPanel(String title) {
        JPanel absencesPanel = new JPanel(new GridLayout(0, 2));
        absencesPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.WHITE, 2), title, TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.WHITE));
        absencesPanel.setBackground(new Color(45, 45, 45));

        String[] absenceDates = {"January 23, 2025", "January 30, 2025", "February 06, 2025", "February 13, 2025", "February 20, 2025"};
        for (String date : absenceDates) {
            JCheckBox absenceCheckBox = new JCheckBox("Absence on ");
            absenceCheckBox.setPreferredSize(new Dimension(150, 30)); // Set preferred size for checkbox
            absencesPanel.add(absenceCheckBox);
            JLabel dateLabel = new JLabel(date);
            dateLabel.setForeground(Color.WHITE); // Set label color to white
            absencesPanel.add(dateLabel);
        }

        return absencesPanel;
    }

    private void addField(JPanel panel, GridBagConstraints gbc, String label, JTextField textField) {
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(new JLabel(label, SwingConstants.RIGHT), gbc);
        gbc.gridx = 1;
        textField.setColumns(10);
        textField.setPreferredSize(new Dimension(150, 30)); // Set preferred size for text fields
        panel.add(textField, gbc);
    }

private void calculateGrades() {
    try {
        // Lecture Inputs
        double prelimExamLecture = Double.parseDouble(prelimExamLectureField.getText());
        double essay = Double.parseDouble(essayField.getText());
        double pvm = Double.parseDouble(pvmField.getText());
        double javaBasics = Double.parseDouble(javaBasicsField.getText());
        double introToJS = Double.parseDouble(introToJSField.getText());

        // Check if any value exceeds the maximum allowed limit
        if (prelimExamLecture > 100 || essay > 100) {
            showCustomMessageDialog("Prelim Exam Score and Essay Score cannot exceed 100!", "Input Error");
            return;
        }
        if (pvm > 60) {
            showCustomMessageDialog("PVM Score cannot exceed 60!", "Input Error");
            return;
        }
        if (javaBasics > 40) {
            showCustomMessageDialog("Java Basics Score cannot exceed 40!", "Input Error");
            return;
        }
        if (introToJS > 40) {
            showCustomMessageDialog("Intro to JS Score cannot exceed 40!", "Input Error");
            return;
        }

        // Calculate prelim quizzes
        double prelimQuizzes = (essay + pvm + javaBasics + introToJS) / 4;

        // Lecture attendance
        int lectureAbsences = getAbsencesCount(lectureAbsencesPanel);
        if (lectureAbsences >= 4) {
            resultArea.setText("Lecture Prelim Grade: FAIL - Instant fail due to excessive absences.");
            formulaArea.setText("");
            return;
        }
        double attendancePenalty = lectureAbsences * 10;
        double attendance = Math.max(100 - attendancePenalty, 0);
        double lecturePrelimClassStanding = (prelimQuizzes * 0.6) + (attendance * 0.4);
        double lecturePrelimGrade = (prelimExamLecture * 0.6) + (lecturePrelimClassStanding * 0.4);

        // Feedback for lecture
        String lectureFeedback = String.format("Lecture Prelim Grade: %.2f - %s", lecturePrelimGrade, getFeedback(lecturePrelimGrade));

        // Lab Inputs
        double java1 = Double.parseDouble(java1Field.getText());
        double java2 = Double.parseDouble(java2Field.getText());
        double js1 = Double.parseDouble(js1Field.getText());
        double js2 = Double.parseDouble(js2Field.getText());

        // Check if lab scores exceed 100
        if (java1 > 100 || java2 > 100 || js1 > 100 || js2 > 100) {
            showCustomMessageDialog("Java 1, Java 2, JS 1, and JS 2 scores cannot exceed 100!", "Input Error");
            return;
        }

        // Weighted lab prelim exam score
        double prelimExamLab = (0.2 * java1) + (0.3 * java2) + (0.2 * js1) + (0.3 * js2);

        double mp1 = Double.parseDouble(mp1Field.getText());
        double mp2 = Double.parseDouble(mp2Field.getText());
        double mp3 = Double.parseDouble(mp3Field.getText());
        double mp3Docu = Double.parseDouble(mp3DocuField.getText());
        int labAbsences = getAbsencesCount(labAbsencesPanel);

        // Check if MP scores exceed 100
        if (mp1 > 100 || mp2 > 100 || mp3 > 100 || mp3Docu > 100) {
            showCustomMessageDialog("MP 1, MP 2, MP 3, and MP 3 (Docu) scores cannot exceed 100!", "Input Error");
            return;
        }

        // Check for lab absences
        String labFeedback = "";
        if (labAbsences >= 4) {
            labFeedback = "Failed due to the amount of absences in laboratory.";
        } else {
            // Lab Work Calculation
            double labWork = (mp1 + mp2 + mp3 + mp3Docu) / 4; // Average of MP1, MP2, MP3, and MP3 Docu
            double labAttendance = Math.max(0, 100 - (labAbsences * 10)); // Attendance calculation
            double labPrelimClassStanding = (labWork * 0.6) + (labAttendance * 0.4); // Class standing calculation
            double labPrelimGrade = (0.6 * prelimExamLab) + (0.4 * labPrelimClassStanding); // Final lab grade calculation
            labFeedback = String.format("Laboratory Prelim Grade: %.2f - %s", labPrelimGrade, getFeedback(labPrelimGrade));
        }

        // Display Results
        resultArea.setText(lectureFeedback + "\n" + labFeedback);

        // Set formulas in the formula area
        formulaArea.setText(
            "Lecture Prelim Grade = (0.6 * Prelim Exam) + (0.4 * Prelim Class Standing)\n" +
            "Prelim Class Standing = (0.6 * Prelim Quizzes) + (0.4 * Attendance)\n" +
            "Prelim Quizzes = (Essay + PVM + Java Basics + Intro to JS) / 4\n" +
            "Attendance = 100 - (10 * Lecture Absences)\n\n" +
            "Lab Prelim Grade = (0.6 * Prelim Exam Lab) + (0.4 * Prelim Class Standing Lab)\n" +
            "Prelim Exam Lab = (0.2 * Java 1) + (0.3 * Java 2) + (0.2 * JS 1) + (0.3 * JS 2)\n" +
            "Prelim Class Standing Lab = (0.6 * Lab Work) + (0.4 * Attendance)\n" +
            "Lab Work = (MP1 + MP2 + MP3 + MP3 (Docu)) / 4\n" +
            "Attendance = 100 - (10 * Lab Absences)"
        );
    } catch (NumberFormatException ex) {
        showCustomMessageDialog("Please enter valid numbers!", "Input Error");
    }
}

    private int getAbsencesCount(JPanel absencesPanel) {
        int count = 0;
        for (Component component : absencesPanel.getComponents()) {
            if (component instanceof JPanel) { // Check if the component is a JPanel (for each absence)
                for (Component innerComponent : ((JPanel) component).getComponents()) {
                    if (innerComponent instanceof JCheckBox) {
                        JCheckBox checkBox = (JCheckBox) innerComponent;
                        if (checkBox.isSelected()) {
                            count++;
                        }
                    }
                }
            } else if (component instanceof JCheckBox) { // Directly check if the component is a JCheckBox
                JCheckBox checkBox = (JCheckBox) component;
                if (checkBox.isSelected()) {
                    count++;
                }
            }
        }
        return count;
    }

    private String getFeedback(double grade) {
        if (grade >= 90) {
            return "Excellent!";
        } else if (grade >= 80) {
            return "Very Good!";
        } else if (grade >= 75) {
            return "Good!";
        } else if (grade >= 60) {
            return "Needs Improvement.";
        } else {
            return "Failed. Please review your work.";
        }
    }

    private void showCustomMessageDialog(String message, String title) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(45, 45, 45));
        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        panel.add(label);
        JOptionPane.showMessageDialog(this, panel, title, JOptionPane.ERROR_MESSAGE);
    }
}
