import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel, usernameLabel, passwordLabel, userTypeLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton;

    public LoginPage() {
        // Set up the window
        setTitle("Course Registration System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        // Add the title label
        titleLabel = new JLabel("Course Registration System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel);
        add(new JLabel());

        // Add the username label and field
        usernameLabel = new JLabel("Username:");
        add(usernameLabel);
        usernameField = new JTextField();
        add(usernameField);

        // Add the password label and field
        passwordLabel = new JLabel("Password:");
        add(passwordLabel);
        passwordField = new JPasswordField();
        add(passwordField);

        // Add the user type label and dropdown
        userTypeLabel = new JLabel("User Type:");
        add(userTypeLabel);
        userTypeComboBox = new JComboBox<String>(new String[]{"Admin", "User"});
        add(userTypeComboBox);

        // Add the login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        // Show the window
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle the login button click
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = (String) userTypeComboBox.getSelectedItem();

        if (userType.equals("Admin") && username.equals("admin") && password.equals("admin")) {
            // Create a new window for the admin dashboard
            JFrame adminDashboard = new JFrame("Admin Dashboard");
            adminDashboard.setSize(400, 300);
            adminDashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adminDashboard.setLayout(new GridLayout(6, 1));

            // Add the options for the admin
            JButton viewAllCoursesButton = new JButton("View all courses");
            JButton viewFullCoursesButton = new JButton("View all courses that are FULL");
            JButton writeFullCoursesToFileButton = new JButton("Write to a file the list of course that are Full");
            JButton viewStudentsInCourseButton = new JButton("View the names of the students being registered in a specific course");
            JButton viewCoursesForStudentButton = new JButton("View the list of courses that a given student is being registered on");
            JButton exitButton = new JButton("Exit");

            adminDashboard.add(viewAllCoursesButton);
            adminDashboard.add(viewFullCoursesButton);
            adminDashboard.add(writeFullCoursesToFileButton);
            adminDashboard.add(viewStudentsInCourseButton);
            adminDashboard.add(viewCoursesForStudentButton);
            adminDashboard.add(exitButton);

            // Add action listeners for the buttons
            viewAllCoursesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispalyAllCourses();
                }
            });

            viewFullCoursesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayAllFullCourses();
                }
            });

            writeFullCoursesToFileButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    writeAllFullCourses(console);
                    try {
                        // Create a new file
                        File file = new File("full_courses.txt");
                        FileWriter writer = new FileWriter(file);

                        // Write the list of full courses to the file
                        ArrayList<String> fullCourses = getFullCourses();
                        for (String course : fullCourses) {
                            writer.write(course + "\n");
                        }

                        // Close the writer
                        writer.close();

                        // Show a success message
                        JOptionPane.showMessageDialog(adminDashboard, "Successfully wrote full courses to file", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        // Show an error message
                        JOptionPane.showMessageDialog(adminDashboard, "Error writing full courses to file", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            viewStudentsInCourseButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    displayAllStudentsByCourse(console);
                }
            });

            viewCoursesForStudentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                     displayCoursesByStudent(console);
                }
            });

            exitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Exit the program
                    System.exit(0);
                }
            });

            // Show the admin dashboard
            adminDashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }

    private ArrayList<String> getFullCourses() {
        // TODO: Implement code to get the list of full courses
        ArrayList<String> fullCourses = new ArrayList<String>();
        fullCourses.add("Course A");
        fullCourses.add("Course B");
        fullCourses.add("Course C");
        return fullCourses;
    }
}
