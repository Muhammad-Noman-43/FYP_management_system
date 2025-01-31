/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Muhammad Noman
 */
public class Login_Signup_Page implements ActionListener {
    JFrame lsframe = new JFrame("Login/SignIn or Register");

    Color bc = new Color(0x38ABBB);
    Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
    ImageIcon icon = new ImageIcon("640px-COMSATS_new_logo.jpg");
    JButton stdRegister = new JButton("Register");
    JButton facRegister = new JButton("Register");
    JButton supRegister = new JButton("Register");
    JButton signup = new JButton("Signup/Register as new user");
    JButton login = new JButton("Login to your account");
    Conn connection = new Conn();


    public Login_Signup_Page(){
        lsframe.setIconImage(icon.getImage());
        lsframe.setLayout(null);
        lsframe.pack();
        lsframe.setVisible(true);
        lsframe.setSize(500, 500);
        lsframe.setResizable(false);
        lsframe.getContentPane().setBackground(bc);
        lsframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        lsframe.setLocationRelativeTo(null);

        login_or_signup();
    }

    void login(){

        JFrame loginFrame = new JFrame("Login to your account");

        loginFrame.setLayout(null);
        loginFrame.setSize(500, 500);
        loginFrame.setResizable(false);
        loginFrame.getContentPane().setBackground(bc);
        loginFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.setBackground(bc);
        panel1.setLayout(null);
        panel1.setBounds(20, 40, 460, 460);

        JButton loginBtn = new JButton("LogIn");

        JLabel loginText = new JLabel("Login using your ID (Reg. No) and password");
        loginText.setFont(new Font("Consolas", Font.BOLD, 19));
        loginText.setBounds(0,30, 460, 50);
        panel1.add(loginText);

        JLabel regNo = new JLabel("Enter your ID : ");
        JLabel password = new JLabel("Enter you password : ");

        JLabel radioLbl = new JLabel("I am a");
        JPanel radioPanel = new JPanel(new FlowLayout());
        JRadioButton stdRadio = new JRadioButton("Student");
        JRadioButton facRadio = new JRadioButton("Faculty");
        JRadioButton supRadio = new JRadioButton("Supervisor");
        JRadioButton corRadio = new JRadioButton("Coordinator");
        ButtonGroup group = new ButtonGroup();
        stdRadio.setActionCommand("Student");
        group.add(stdRadio);
        facRadio.setActionCommand("Faculty");
        group.add(facRadio);
        supRadio.setActionCommand("Supervisor");
        group.add(supRadio);
        corRadio.setActionCommand("Coordinator");
        group.add(corRadio);

        JTextField regIn = new JTextField(20);
        JPasswordField passIn = new JPasswordField(20);

        regNo.setBounds(30, 100, 200, 30);
        regNo.setFont(new Font(null, Font.BOLD, 16));
        regIn.setBounds(regNo.getWidth()+2, 104, 160, 20);
        password.setBounds(30, 150, 200, 30);
        password.setFont(new Font(null, Font.BOLD, 16));
        passIn.setBounds(password.getWidth()+2, 154, 160, 20);

        Font radioFont = new Font("Consolas", Font.BOLD, 18);
        radioLbl.setFont(new Font(null, Font.BOLD, 20));
        radioLbl.setBounds(170, 200, panel1.getWidth(), 30);
        stdRadio.setFont(radioFont);
        stdRadio.setBackground(bc);
        supRadio.setFont(radioFont);
        supRadio.setBackground(bc);
        facRadio.setFont(radioFont);
        facRadio.setBackground(bc);
        corRadio.setFont(radioFont);
        corRadio.setBackground(bc);
        radioPanel.setBounds(30, 240, 350, 100);
        radioPanel.setBackground(bc);
        radioPanel.add(stdRadio);
        radioPanel.add(facRadio);
        radioPanel.add(supRadio);
        radioPanel.add(corRadio);

        loginBtn.setBounds(150, 360, 100, 30);
        loginBtn.setFont(new Font(null, Font.BOLD, 16));

        panel1.add(radioLbl);
        panel1.add(loginBtn);
        panel1.add(regNo);
        panel1.add(regIn);
        panel1.add(password);
        panel1.add(passIn);
        panel1.add(radioPanel);
        loginFrame.add(panel1);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(regIn.getText().isEmpty() || passIn.getText().isEmpty() || getSelectedRadioButtonText(group).isEmpty()){
                    JOptionPane.showMessageDialog(loginFrame, "Fill all fields please!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String id = regIn.getText();
                    String password = passIn.getText();
                    String role = getSelectedRadioButtonText(group);

                    //Checking for Students
                    if(role.equalsIgnoreCase("Student")){
                        String findQuery = "SELECT * FROM Student WHERE regNo = ?";
                        try (PreparedStatement preparedStatement = Conn.conn.prepareStatement(findQuery)) {
                            preparedStatement.setString(1, id);
                            try (ResultSet result = preparedStatement.executeQuery()) {
                                if (!result.next()) {
                                    JOptionPane.showMessageDialog(loginFrame, "No such student found 😢", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if(!(result.getString("password").equals(password))){
                                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Password", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    new Student(id);
                                    loginFrame.dispose();
                                    lsframe.dispose();
                                }
                            } catch (Exception e3){
                                System.out.println(e3.getMessage());
                            }
                        } catch (SQLException e2){
                            System.out.println(e2.getMessage());
                        }
                    }
                    //Checking for Faculties
                    else if(role.equalsIgnoreCase("Faculty")){
                        String findQuery = "SELECT * FROM Faculty WHERE facultyId = ?";
                        try (PreparedStatement preparedStatement = Conn.conn.prepareStatement(findQuery)) {
                            preparedStatement.setString(1, id);
                            try (ResultSet result = preparedStatement.executeQuery()) {
                                if (!result.next()) {
                                    JOptionPane.showMessageDialog(loginFrame, "No such faculty member found 😢", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if(!(result.getString("password").equals(password))){
                                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Password", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    new Faculty(id);
                                    loginFrame.dispose();
                                    lsframe.dispose();
                                }
                            } catch (Exception e3){
                                System.out.println(e3.getMessage());
                            }
                        } catch (SQLException e2){
                            System.out.println(e2.getMessage());
                        }
                    }
                    //Checking for supervisor
                    else if (role.equalsIgnoreCase("Supervisor")) {
                        String findQuery = "SELECT * FROM Supervisor WHERE facultyId = ?";
                        try (PreparedStatement preparedStatement = Conn.conn.prepareStatement(findQuery)) {
                            preparedStatement.setString(1, id);
                            try (ResultSet result = preparedStatement.executeQuery()) {
                                if (!result.next()) {
                                    JOptionPane.showMessageDialog(loginFrame, "No such supervisor found 😢", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if(!(result.getString("password").equals(password))){
                                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Password", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    new Supervisor(id);
                                    loginFrame.dispose();
                                    lsframe.dispose();
                                }
                            } catch (Exception e3){
                                System.out.println(e3.getMessage());
                            }
                        } catch (SQLException e2){
                            System.out.println(e2.getMessage());
                        }
                    }
                    //Checking for Coordinator
                    else if(role.equalsIgnoreCase("Coordinator")){
                        String findQuery = "SELECT * FROM Coordinator WHERE name = ?";
                        try (PreparedStatement preparedStatement = Conn.conn.prepareStatement(findQuery)) {
                            preparedStatement.setString(1, id);
                            try (ResultSet result = preparedStatement.executeQuery()) {
                                if (!result.next()) {
                                    JOptionPane.showMessageDialog(loginFrame, "No such Coordinator found😢", "Error", JOptionPane.ERROR_MESSAGE);
                                } else if(!(result.getString("password").equals(password))){
                                    JOptionPane.showMessageDialog(loginFrame, "Incorrect Password", "Wrong Password", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    new Coordinator(id);
                                    loginFrame.dispose();
                                    lsframe.dispose();
                                }
                            } catch (Exception e3){
                                System.out.println(e3.getMessage());
                            }
                        } catch (SQLException e2){
                            System.out.println(e2.getMessage());
                        }
                    }
                }
            }
        });
    }

    void signup(){

        lsframe.setLayout(null);
        lsframe.setSize(500, 500);
        lsframe.setResizable(false);
        lsframe.getContentPane().setBackground(bc);
        lsframe.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        lsframe.setLocationRelativeTo(null);
        lsframe.setVisible(true);

        String []roles = {"Student", "Faculty", "Supervisor"};
        int roleSelected = JOptionPane.showOptionDialog(lsframe, "I am a : ", "Role Selection", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, roles, null);

        switch(roleSelected){
            case 0:
                studentReg(); break;
            case 1:
                facultyRegistration(); break;
            case 2:
                supervisorReg(); break;
            default:
                JOptionPane.showMessageDialog(lsframe, "Can't decide your intention? Leave it.", "Bye Bye", JOptionPane.ERROR_MESSAGE);
        }
    }

    void studentReg(){

        JFrame stdFrame = new JFrame("Student Registration Form");
        stdFrame.setLayout(null);
        stdFrame.setSize(500, 500);
        stdFrame.setResizable(false);
        stdFrame.getContentPane().setBackground(bc);
        stdFrame.setDefaultCloseOperation(stdFrame .DISPOSE_ON_CLOSE);
        stdFrame.setLocationRelativeTo(null);
        stdFrame.setVisible(true);

        //Main Panel to hold all items (i.e. labels, textFields, passwordFields, comboBoxes etc)
        JPanel innerPanel = new JPanel();
        JPanel outerPanel = new JPanel();


        //Main Heading
        JLabel stdRegLbl = new JLabel("Student Registration Form");

        //Labels
        JLabel nameLbl = new JLabel("Enter your name ");
        JLabel regNoLbl = new JLabel("<html>Enter your registration <br>no. <html>");
        JLabel passwordLbl = new JLabel("Set password ");

        //Setting Registration number using combo boxes (3 components: batch+dept+regNo)
        String[] batchArr = {"SP20", "FA20", "SP21", "FA21"};
        String[] deptArr = {"BCS"};
        JTextField regNoInput = new JTextField(3);
        regNoInput.setPreferredSize(new Dimension(regNoInput.getWidth(), 26));
        JComboBox batchCombo = new JComboBox(batchArr);
        batchCombo.setSelectedItem(batchArr[0]);
        JComboBox deptCombo = new JComboBox(deptArr);
        deptCombo.setSelectedItem(deptArr[0]);
        //Panel to hold registration number items
        JPanel regPanel = new JPanel();
        regPanel.add(batchCombo);
        regPanel.add(deptCombo);
        regPanel.add(regNoInput);

        //Text and password fields
        JTextField nameIn = new JTextField(20);
        JPasswordField passwordIn = new JPasswordField(20);

        //Setting styles for each item

        //outerPanel
        outerPanel.setLayout(null);
        outerPanel.setBounds(0, 0, 500, 500);
        outerPanel.setBackground(Color.DARK_GRAY);

        //innerPanel
        innerPanel.setLayout(null);
        innerPanel.setBounds(0,100, 500, 450);

        //stdRegLbl
        stdRegLbl.setBounds(80,40, 300, 30);
        stdRegLbl.setFont(new Font(null, Font.BOLD, 24));
        stdRegLbl.setForeground(Color.BLACK);

        //nameLbl
        nameLbl.setBounds(20, 30,160, 24);
        nameLbl.setFont(new Font(null, Font.BOLD, 14));

        //nameIn
        nameIn.setBounds(nameLbl.getWidth()+60, 30, 200, 24);
        nameIn.setFont(new Font(null, Font.PLAIN, 14));

        //regNoLbl
        regNoLbl.setBounds(20, 80, 200, 30);
        regNoLbl.setLayout(new FlowLayout());
        regNoLbl.setFont(new Font(null, Font.BOLD, 14));

        //regNoInput
        regPanel.setBounds(regNoLbl.getWidth(), 70, 200,30);

        //passwordLbl
        passwordLbl.setBounds(20, 140, 160, 24);
        passwordLbl.setFont(new Font(null, Font.BOLD, 14));

        //passwordIn
        passwordIn.setBounds(passwordLbl.getWidth()+70, 140, 200, 24);

        //register
        stdRegister.setBounds(180, 220, 100, 30);
        stdRegister.setFont(new Font(null, Font.BOLD, 16));


        //Adding labels, textFields, combo boxes and panels having combo boxes and radio button groups
        innerPanel.add(nameLbl);
        innerPanel.add(nameIn);
        innerPanel.add(regNoLbl);
        innerPanel.add(regPanel);
        innerPanel.add(passwordLbl);
        innerPanel.add(passwordIn);
        innerPanel.add(stdRegister);

        stdRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String totalRegNo = (String) batchCombo.getSelectedItem() + "-" + (String) deptCombo.getSelectedItem() + "-" + regNoInput.getText();
                if(nameIn.getText().isEmpty() || regNoInput.getText().isEmpty() || Arrays.toString(passwordIn.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(stdFrame, "Pleas the fill all fields first!", "Fill forms first", JOptionPane.WARNING_MESSAGE);
                } else {
                    String name = nameIn.getText();
                    String password = passwordIn.getText();

                    String query = "INSERT INTO Student (name, regNo, password) VALUES (?, ?, ?)";

                    try {
                        PreparedStatement preparedStatement = Conn.conn.prepareStatement(query);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, totalRegNo);
                        preparedStatement.setString(3, password);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(stdFrame, "Student data added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                        nameIn.setText("");
                        regNoInput.setText("");
                        passwordIn.setText("");
                        stdFrame.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(stdFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());;
                    }
                }
            }
        });

        outerPanel.add(stdRegLbl);
        outerPanel.add(innerPanel);

        stdFrame.add(outerPanel);

        //Clearing what lsform already had
        stdFrame.revalidate();
        stdFrame.repaint();

    }

    void facultyRegistration(){
        JFrame facFrame = new JFrame("Faculty Registration Frame");

        facFrame.setLayout(null);
        facFrame.setSize(500, 500);
        facFrame.setResizable(false);
        facFrame.getContentPane().setBackground(bc);
        facFrame.setDefaultCloseOperation(facFrame.DISPOSE_ON_CLOSE);
        facFrame.setLocationRelativeTo(null);
        facFrame.setVisible(true);

        //Main Panel to hold all items (i.e. labels, textFields, passwordFields, comboBoxes etc)
        JPanel innerPanel = new JPanel();
        JPanel outerPanel = new JPanel();


        //Main Heading
        JLabel facRegLbl = new JLabel("Faculty Registration Form");

        //Labels
        JLabel nameLbl = new JLabel("Enter your name ");
        JLabel facNoLbl = new JLabel("<html>Enter your faculty ID<html>");
        JLabel passwordLbl = new JLabel("Set password ");

        //Setting faculty ID


        //Text and password fields
        JTextField nameIn = new JTextField(20);
        JPasswordField passwordIn = new JPasswordField(20);
        JTextField facNoIn = new JTextField(20);

        //Setting styles for each item

        //outerPanel
        outerPanel.setLayout(null);
        outerPanel.setBounds(0, 0, 500, 500);
        outerPanel.setBackground(Color.DARK_GRAY);

        //innerPanel
        innerPanel.setLayout(null);
        innerPanel.setBounds(0,100, 500, 450);

        //stdRegLbl
        facRegLbl.setBounds(80,40, 300, 30);
        facRegLbl.setFont(new Font(null, Font.BOLD, 24));
        facRegLbl.setForeground(Color.BLACK);

        //nameLbl
        nameLbl.setBounds(20, 30,160, 24);
        nameLbl.setFont(new Font(null, Font.BOLD, 14));

        //nameIn
        nameIn.setBounds(nameLbl.getWidth()+60, 30, 200, 24);
        nameIn.setFont(new Font(null, Font.PLAIN, 14));

        //facNoLbl
        facNoLbl.setBounds(20, 80, 200, 30);
        facNoLbl.setLayout(new FlowLayout());
        facNoLbl.setFont(new Font(null, Font.BOLD, 14));

        //facNoInput
        facNoIn.setBounds(facNoLbl.getWidth()+20, 80, 200, 24);

        //passwordLbl
        passwordLbl.setBounds(20, 140, 160, 24);
        passwordLbl.setFont(new Font(null, Font.BOLD, 14));

        //passwordIn
        passwordIn.setBounds(passwordLbl.getWidth()+60, 140, 200, 24);

        //register
        facRegister.setBounds(180, 260, 100, 30);
        facRegister.setFont(new Font(null, Font.BOLD, 16));


        //Adding labels, textFields, combo boxes and panels having combo boxes and radio button groups
        innerPanel.add(nameLbl);
        innerPanel.add(nameIn);
        innerPanel.add(facNoLbl);
        innerPanel.add(facNoIn);
        innerPanel.add(passwordLbl);
        innerPanel.add(passwordIn);
        innerPanel.add(facRegister);

        facRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameIn.getText().isEmpty() || facNoIn.getText().isEmpty() || Arrays.toString(passwordIn.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(facFrame, "Pleas the fill all fields first!", "Fill forms first", JOptionPane.WARNING_MESSAGE);
                } else {
                    String name = nameIn.getText();
                    String facNo = facNoIn.getText();
                    String password = passwordIn.getText();

                    String query = "INSERT INTO Faculty (name, facultyId, password) VALUES (?, ?, ?)";

                    try {
                        PreparedStatement preparedStatement = Conn.conn.prepareStatement(query);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, facNo);
                        preparedStatement.setString(3, password);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(facFrame, "Faculty added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                        nameIn.setText("");
                        facNoIn.setText("");
                        passwordIn.setText("");
                        facFrame.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(facFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());;
                    }
                }
            }
        });

        outerPanel.add(facRegLbl);
        outerPanel.add(innerPanel);

        facFrame.add(outerPanel);

        //Clearing what lsform already had
        facFrame.revalidate();
        facFrame.repaint();
    }

    void supervisorReg(){

        JFrame supFrame = new JFrame("Supervisor Registration Frame");

        supFrame.setLayout(null);
        supFrame.setSize(500, 500);
        supFrame.setResizable(false);
        supFrame.getContentPane().setBackground(bc);
        supFrame.setDefaultCloseOperation(supFrame.DISPOSE_ON_CLOSE);
        supFrame.setLocationRelativeTo(null);
        supFrame.setVisible(true);

//        supFrame.removeAll();
//        supFrame.repaint();
//        supFrame.revalidate();

        //Main Panel to hold all items (i.e. labels, textFields, passwordFields, comboBoxes etc)
        JPanel innerPanel = new JPanel();
        JPanel outerPanel = new JPanel();


        //Main Heading
        JLabel facRegLbl = new JLabel("Supervisor Registration Form");

        //Labels
        JLabel nameLbl = new JLabel("Enter your name ");
        JLabel facNoLbl = new JLabel("<html>Enter your faculty ID<html>");
        JLabel projectSupervisedLbl = new JLabel("<html>How many projects have <br>you supervised till now?<html>");
        JLabel passwordLbl = new JLabel("Set password ");

        //Setting faculty ID


        //Text and password fields
        JTextField nameIn = new JTextField(20);
        JPasswordField passwordIn = new JPasswordField(20);
        JTextField facNoIn = new JTextField(20);
        JTextField projectSupervisedIn = new JTextField(3);

        //Setting styles for each item

        //outerPanel
        outerPanel.setLayout(null);
        outerPanel.setBounds(0, 0, 500, 500);
        outerPanel.setBackground(Color.DARK_GRAY);

        //innerPanel
        innerPanel.setLayout(null);
        innerPanel.setBounds(0,100, 500, 450);

        //stdRegLbl
        facRegLbl.setBounds(80,40, 400, 30);
        facRegLbl.setFont(new Font(null, Font.BOLD, 23));
        facRegLbl.setForeground(Color.BLACK);

        //nameLbl
        nameLbl.setBounds(20, 30,160, 24);
        nameLbl.setFont(new Font(null, Font.BOLD, 14));

        //nameIn
        nameIn.setBounds(nameLbl.getWidth()+60, 30, 200, 24);
        nameIn.setFont(new Font(null, Font.PLAIN, 14));

        //facNoLbl
        facNoLbl.setBounds(20, 80, 200, 30);
        facNoLbl.setLayout(new FlowLayout());
        facNoLbl.setFont(new Font(null, Font.BOLD, 14));

        //facNoInput
        facNoIn.setBounds(facNoLbl.getWidth()+20, 80, 200, 24);

        //projectSupervisedLbl
        projectSupervisedLbl.setBounds(20, 140, 210, 32);
        projectSupervisedLbl.setFont(new Font(null, Font.BOLD, 14));

        //projectSupervisedIn
        projectSupervisedIn.setBounds(projectSupervisedLbl.getWidth()+10, 140, 200, 24);

        //passwordLbl
        passwordLbl.setBounds(20, 200, 160, 24);
        passwordLbl.setFont(new Font(null, Font.BOLD, 14));

        //passwordIn
        passwordIn.setBounds(passwordLbl.getWidth()+60, 200, 200, 24);

        //register
        supRegister.setBounds(180, 260, 100, 30);
        supRegister.setFont(new Font(null, Font.BOLD, 16));


        //Adding labels, textFields, combo boxes and panels having combo boxes and radio button groups
        innerPanel.add(nameLbl);
        innerPanel.add(nameIn);
        innerPanel.add(facNoLbl);
        innerPanel.add(facNoIn);
        innerPanel.add(projectSupervisedLbl);
        innerPanel.add(projectSupervisedIn);
        innerPanel.add(passwordLbl);
        innerPanel.add(passwordIn);
        innerPanel.add(supRegister);
        supRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nameIn.getText().isEmpty() || facNoIn.getText().isEmpty() || projectSupervisedIn.getText().isEmpty() || Arrays.toString(passwordIn.getPassword()).isEmpty()){
                    JOptionPane.showMessageDialog(supFrame, "Pleas the fill all fields first!", "Fill forms first", JOptionPane.WARNING_MESSAGE);
                } else {
                    String name = nameIn.getText();
                    String facNo = facNoIn.getText();
                    String ps = projectSupervisedIn.getText();
                    String password = passwordIn.getText();

                    String query = "INSERT INTO Supervisor (name, facultyId, projectsSupervised, password) VALUES (?, ?, ?, ?)";

                    try {
                        PreparedStatement preparedStatement = Conn.conn.prepareStatement(query);
                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, facNo);
                        preparedStatement.setString(3, ps);
                        preparedStatement.setString(4, password);
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(supFrame, "Supervisor added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

                        nameIn.setText("");
                        projectSupervisedIn.setText("");
                        facNoIn.setText("");
                        passwordIn.setText("");
                        supFrame.dispose();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(supFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println(ex.getMessage());;
                    }
                }
            }
        });

        outerPanel.add(facRegLbl);
        outerPanel.add(innerPanel);

        supFrame.add(outerPanel);

        //Clearing what lsform already had
        supFrame.revalidate();
        supFrame.repaint();
    }

    void login_or_signup(){

        JPanel panel1 = new JPanel(new FlowLayout());
        JPanel panel2 = new JPanel(new FlowLayout());
        JPanel panel3 = new JPanel();

        panel1.setBounds(0,0,lsframe.getWidth(), lsframe.getHeight()/3);
        panel1.setBorder(new EmptyBorder(120, 0, 0, 0));
        panel2.setBounds(0,lsframe.getHeight()/3,lsframe.getWidth(), lsframe.getHeight()/3);
        panel2.setBorder(new EmptyBorder(60, 0, 0, 0));
        panel3.setBounds(0,(lsframe.getHeight()/3)*2,lsframe.getWidth(), lsframe.getHeight()/3);

        JLabel label = new JLabel("Login or Signup");
        label.setFont(new Font("Georgia", Font.BOLD, 24));
        panel1.add(label);

        login.setFont(new Font("Georgia", Font.BOLD, 14));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        signup.setFont(new Font("Georgia", Font.BOLD, 14));
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
        panel2.add(login);
        panel2.add(signup);

        lsframe.add(panel1);
        lsframe.add(panel2);
        lsframe.add(panel3);


    }

    public String getSelectedRadioButtonText(ButtonGroup group) {
        ButtonModel selectedModel = group.getSelection();
        if (selectedModel != null) {
            return selectedModel.getActionCommand();
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}