/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Muhammad Noman
 */
public class Faculty {
//
//    String name;
//    String facultyID;
//    private String password;
//    boolean isCommitteeMember;
    Color frameBc = new Color(0x4188ff);
    Color mainPanelBc = new Color(0x78aaff);
    JButton logout = new JButton("Logout");

    Faculty(){};

    public Faculty(String id){
        JFrame frame = new JFrame();
        JPanel mainPanel, sidePanel;
        frame.setTitle("Faculty Dashboard");
        frame.setLayout(null);
        frame.getContentPane().setBackground(frameBc);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);JLabel name = new JLabel();
        JLabel facNo = new JLabel();
        JLabel committeeNo = new JLabel();
        JLabel greetingsLbl = new JLabel();

        //Data labels
        try{
            String extractDataQuery = "SELECT * FROM Faculty WHERE facultyId = ?";
            PreparedStatement p1 = Conn.conn.prepareStatement(extractDataQuery);
            p1.setString(1, id);
            ResultSet result = p1.executeQuery();
            result.next();
            name.setText(result.getString("name"));
            facNo.setText(result.getString("facultyId"));
            committeeNo.setText(result.getString("committeeNumber"));
            greetingsLbl.setText("Hello, "+result.getString("name"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //Setting bounds for main and side panels manually (parent:null)
        mainPanel = new JPanel(null);
        mainPanel.setBackground(mainPanelBc);
        mainPanel.setBounds((frame.getWidth()/4), 0, (frame.getWidth()/4)*3-12, frame.getHeight()-10);
//        mainPanel.setBorder(new LineBorder(Color.RED, 6));
        sidePanel = new JPanel(null);
        sidePanel.setBounds(0, 0, frame.getWidth()/4, frame.getHeight()-10);
//        sidePanel.setBorder(new LineBorder(Color.GREEN, 6));
        sidePanel.setBackground(frameBc);

        //Creating two panels in the main panel
        //Panels Colors
        Color topColor = new Color(0x2196f3);
        Color bottomColor = new Color(0x03a9f5);
        // Fonts
        Font dataFont = new Font("Consolas", Font.BOLD, 18); // Font for data
        Font metaFont = new Font("Consolas", Font.ITALIC, 15); // Font for metadata (Lbls)

        // Create a JPanel for the dashboard
        JPanel topPanel = new JPanel(null); // Absolute positioning for the panel
        topPanel.setBounds(30, 30, 900, 150);
        topPanel.setBorder(new LineBorder(Color.BLACK));
        topPanel.setBackground(topColor);

        // Name
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setFont(metaFont);
        nameLbl.setBounds(20, 20, 300, 30);

        name.setFont(dataFont);
        name.setBounds(360, 20, 300, 30);

        // Registration Number
        JLabel facNoLbl = new JLabel("Faculty ID:");
        facNoLbl.setFont(metaFont);
        facNoLbl.setBounds(20, 60, 300, 30);

        facNo.setFont(dataFont);
        facNo.setBounds(360, 60, 300, 30);

        // Project Status
        JLabel committeeNoLbl = new JLabel("Committee Number");
        committeeNoLbl.setFont(metaFont);
        committeeNoLbl.setBounds(20, 100, 300, 30);

        committeeNo.setFont(dataFont);
        committeeNo.setBounds(360, 100, 300, 30);

        // Add components to the top panel
        topPanel.add(nameLbl);
        topPanel.add(name);
        topPanel.add(facNoLbl);
        topPanel.add(facNo);
        topPanel.add(committeeNoLbl);
        topPanel.add(committeeNo);
        // Add topPanel to the frame
        frame.add(topPanel);


        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(30, 200, 900, 430);
        bottomPanel.setBorder(new LineBorder(Color.BLACK));
        bottomPanel.setBackground(bottomColor);

        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        //Sidepanel buttons
        Font font = new Font("Consolas", Font.BOLD, 18);
        JPanel greetingPanel = new JPanel();
        greetingPanel.setOpaque(false);
        greetingsLbl.setBorder(new EmptyBorder(50, 0, 30, 0));
        greetingsLbl.setFont(font);
        JPanel buttonsPanel = new JPanel(new GridLayout(4, 1));
        buttonsPanel.setOpaque(false);
        JButton showAllCommittees = new JButton("Show all Committees");
        showAllCommittees.setFont(font);
        JButton projectStatus = new JButton("<html>See students under<br>my supervision<html>");
        projectStatus.setFont(font);
        JButton showCommittee = new JButton("Show my committee");
        showCommittee.setFont(font);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);

        greetingPanel.setBounds(0, 0, sidePanel.getWidth(), sidePanel.getHeight()/5);
        buttonsPanel.setBounds(0, greetingPanel.getHeight()+10, sidePanel.getWidth(), (sidePanel.getHeight()/5)*2);
        emptyPanel.setBounds(0, greetingPanel.getHeight()+buttonsPanel.getHeight()+20, sidePanel.getWidth(), 200);

        logout.setOpaque(false);
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Login_Signup_Page();
            }
        });
        greetingPanel.add(logout);
        greetingPanel.add(greetingsLbl);

        buttonsPanel.add(projectStatus);
        buttonsPanel.add(showAllCommittees);
        buttonsPanel.add(showCommittee);

        sidePanel.add(greetingPanel);
        sidePanel.add(buttonsPanel);
        sidePanel.add(emptyPanel);

        frame.add(mainPanel);
        frame.add(sidePanel);

        frame.repaint();
        frame.revalidate();
    }

}
