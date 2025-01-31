/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Scanner;
/**
 *
 * @author Muhammad Noman
 */
public class Coordinator {
    
    String username, password;
    Scanner in = new Scanner(System.in);
    private int noOfFaculties;
    Color frameBc = new Color(0x4188ff);
    Color mainPanelBc = new Color(0x78aaff);
    JButton logout = new JButton("Logout");
    JPanel bottomPanel = new JPanel();
    JFrame frame = new JFrame();
//    Coordinator(String name, String password){
//        this.username = name;
//        this.password = password;
//    }

    public Coordinator(String id){
        JPanel mainPanel, sidePanel;
        frame.setTitle("Coordinator Dashboard");
        frame.setLayout(null);
        frame.getContentPane().setBackground(frameBc);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

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

        JLabel name = new JLabel("Muhammad");
        name.setFont(dataFont);
        name.setBounds(360, 20, 300, 30);

        // Registration Number
        JLabel regNoLbl = new JLabel("Faculty ID:");
        regNoLbl.setFont(metaFont);
        regNoLbl.setBounds(20, 60, 300, 30);

        JLabel regNo = new JLabel("muhammad@gmail.com");
        regNo.setFont(dataFont);
        regNo.setBounds(360, 60, 300, 30);

        // Add components to the top panel
        topPanel.add(nameLbl);
        topPanel.add(name);
        topPanel.add(regNoLbl);
        topPanel.add(regNo);

        // Add topPanel to the frame
        frame.add(topPanel);


        //Bottom panel
        bottomPanel.setBounds(30, 210, 900, 430);
        bottomPanel.setBorder(new LineBorder(Color.BLACK));
        bottomPanel.setBackground(bottomColor);

        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        //Sidepanel buttons
        Font font = new Font("Consolas", Font.BOLD, 16);
        JPanel greetingPanel = new JPanel();
        greetingPanel.setOpaque(false);
        JLabel greetingsLbl = new JLabel("Hello, Muhammad");
        greetingsLbl.setBorder(new EmptyBorder(50, 0, 30, 0));
        greetingsLbl.setFont(new Font("Consolas", Font.BOLD, 20));
        JPanel buttonsPanel = new JPanel(new GridLayout(7, 1));
        buttonsPanel.setOpaque(false);
        JButton showSupervisorsProposedProject = new JButton("Show proposed projects");
        showSupervisorsProposedProject.setFont(font);
        showSupervisorsProposedProject.setBackground(Color.white);
        JButton showAllCommittees = new JButton("See all Committees");
        showAllCommittees.setFont(font);
        showAllCommittees.setBackground(Color.WHITE);
        showAllCommittees.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllCommittees();
            }
        });
        JButton allFaculties = new JButton("See all faculties");
        allFaculties.setFont(font);
        allFaculties.setBackground(Color.WHITE);
        JButton allSupervisors = new JButton("See all supervisors");
        allSupervisors.setFont(font);
        allSupervisors.setBackground(Color.WHITE);
        JButton composeCommittee = new JButton("Compose new committee");
        composeCommittee.setFont(font);
        composeCommittee.setBackground(Color.WHITE);
        showSupervisorsProposedProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProposals();
            }
        });
        JButton addFacOrSupToCommittee = new JButton("<html>Add faculty or supervisor to existing committee<html>");
        addFacOrSupToCommittee.setFont(font);
        addFacOrSupToCommittee.setBackground(Color.WHITE);
        JButton setDOPForCommittee = new JButton("<html>Set date of presentation for committee<html>");
        setDOPForCommittee.setFont(font);
        setDOPForCommittee.setBackground(Color.WHITE);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);

        greetingPanel.setBounds(0, 0, sidePanel.getWidth(), sidePanel.getHeight()/6);
        buttonsPanel.setBounds(0, greetingPanel.getHeight()+10, sidePanel.getWidth(), (int) ((sidePanel.getHeight()/6)*4.3));
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

        buttonsPanel.add(showSupervisorsProposedProject);
        buttonsPanel.add(allSupervisors);
        buttonsPanel.add(allFaculties);
        buttonsPanel.add(showAllCommittees);
        buttonsPanel.add(composeCommittee);
        buttonsPanel.add(addFacOrSupToCommittee);
        buttonsPanel.add(setDOPForCommittee);

        sidePanel.add(greetingPanel);
        sidePanel.add(buttonsPanel);
        sidePanel.add(emptyPanel);

        frame.add(mainPanel);
        frame.add(sidePanel);

        frame.repaint();
        frame.revalidate();
    }

    private void listAllCommittees() {
        
    }

    void handleProposals() {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String getProposalsQuery = "SELECT studentId AS 'Student ID', title AS 'Project Title', supervisorId AS 'Supervisor ID'," +
                "approvalStatus AS 'Proposal Status', noOfStdsReq AS 'No. of students required', filePath AS 'File Path' FROM proposedProjectsToCoordinator";

        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(getProposalsQuery);
            ResultSet proposals = p1.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            ResultSetMetaData metaData = proposals.getMetaData();
            int columnCount = metaData.getColumnCount()-1;
            String[] columnNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnLabel(i);
                tableModel.addColumn(columnNames[i - 1]);
            }

            while (proposals.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = proposals.getObject(i);
                }
                tableModel.addRow(row);
            }

            bottomPanel.setLayout(null);
            JTable proposalTable = new JTable(tableModel){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Makes all cells non-editable
                }
            };

            JScrollPane scrollPane = new JScrollPane(proposalTable);
            JLabel proposalsLabel = new JLabel("Proposals Sent to You (click to accept or reject)");

            proposalsLabel.setFont(new Font("Consolas", Font.BOLD, 18));
            proposalsLabel.setBounds(50, 10, 300, 30);
            scrollPane.setBounds(40, 60, 600, 200);
            scrollPane.setFont(new Font("Consolas", Font.BOLD, 20));

            bottomPanel.add(proposalsLabel);
            bottomPanel.add(scrollPane);

            // Add mouse listener to the table
            proposalTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) { // Single click
                        int selectedRow = proposalTable.getSelectedRow();
                        if (selectedRow != -1) {
                            // Get proposal details from the selected row
                            String studentId = proposalTable.getValueAt(selectedRow, 0).toString();
                            String projectTitle = proposalTable.getValueAt(selectedRow, 1).toString();
                            String supervisorId = proposalTable.getValueAt(selectedRow, 2).toString();
                            String proposalStatus = proposalTable.getValueAt(selectedRow, 3).toString();
                            int noOfStudents = (int) proposalTable.getValueAt(selectedRow, 4);
                            String filePath = "";
                            String getFilePathQuery = "SELECT filePath FROM proposedProjectsToCoordinator WHERE studentId = ?";
                            try {
                                PreparedStatement p = Conn.conn.prepareStatement(getFilePathQuery);
                                p.setString(1, studentId);
                                ResultSet r = p.executeQuery();
                                r.next();
                                filePath = r.getString(1);
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }

                            // Show details in a JOptionPane
                            if (!(proposalStatus.equalsIgnoreCase("Approved")) && !(proposalStatus.equalsIgnoreCase("Rejected"))) {
                                int option = JOptionPane.showOptionDialog(
                                        frame,
                                        "Proposal Details:\n" +
                                                "Student ID: " + studentId + "\n" +
                                                "Project Title: " + projectTitle + "\n" +
                                                "Supervisor ID: "+supervisorId + "\n" +
                                                "Number of students required: " + noOfStudents,
                                        "Handle Proposal",
                                        JOptionPane.YES_NO_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        new Object[]{"Open Proposal File", "Accept Proposal", "Reject Proposal"},
                                        "Open Proposal"
                                );

                                if (option == 0) {
                                    openProposalFile(filePath);
                                } else if(option == 1){
                                        updateProposalStatus(studentId, supervisorId, projectTitle, "Approved", noOfStudents);
                                } else if(option == 2){
                                    updateProposalStatus(studentId, supervisorId, projectTitle, "Rejected", 0);
                                }
                            } else {
                                JOptionPane.showMessageDialog(frame,
                                        "Proposal is already " + proposalStatus,
                                        "Proposal already processed", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }
            });

            bottomPanel.repaint();
            bottomPanel.revalidate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while retrieving proposals: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateProposalStatus(String studentId, String supervisorId, String projectTitle, String status, int noOfStd) {

        String updateSupToCoProposalsQuery = "UPDATE proposedProjectsToCoordinator SET approvalStatus = ? WHERE studentId = ? AND supervisorId = ?";
        String updateStdToSupProposalQuery = "UPDATE proposedProjectsToSupervisor SET approvalStatus = ? WHERE studentId = ? AND supervisorId = ?";
        try {
            PreparedStatement updateCoordinatorStmt = Conn.conn.prepareStatement(updateSupToCoProposalsQuery);
            PreparedStatement updateSupervisorStmt = Conn.conn.prepareStatement(updateStdToSupProposalQuery);

            updateCoordinatorStmt.setString(1, status);
            updateCoordinatorStmt.setString(2, studentId);
            updateCoordinatorStmt.setString(3, supervisorId);


            // Update the related supervisor proposals
            updateSupervisorStmt.setString(1, status);
            updateSupervisorStmt.setString(2, studentId);
            updateSupervisorStmt.setString(3, supervisorId);

            int supToCorRowsUpdated = updateCoordinatorStmt.executeUpdate();
            int stdToSupRowsUpdated = updateSupervisorStmt.executeUpdate();

            if (supToCorRowsUpdated > 0 && stdToSupRowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame,
                        "Proposal has been " + status.toLowerCase() + " successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Refresh the proposals table after the update
                handleProposals();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while updating proposal: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }

    void openProposalFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                JOptionPane.showMessageDialog(null, "File not found: " + filePath, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File opening not supported on this system.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error opening file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


//    void setDateOfPresentation(Student s){
//        if("Selected".equalsIgnoreCase(s.Project_status) || "Completed".equalsIgnoreCase(s.Project_status)){
//            System.out.println("Enter the date for the presentation of this student's project : ");
//            s.dateOfPresentation = in.nextLine();
//        } else {
//            System.out.println("Please approve your project first from the Committee");
//        }
//    }
//
//    void composeCommittee(){
//
//        System.out.println("Enter the number of the committee : ");
//        int committeeNumber = in.nextInt();
//        in.nextLine();
//        if(searchCommitteeIdxForComposing(committeeNumber)<0){
//            FYP_management_system.registeredCommittees.add(new Committee(committeeNumber));
//
//            System.out.println("Enter the total number of faculties/supervisor : ");
//            noOfFaculties = in.nextInt();
//
//            while((noOfFaculties<3) || (noOfFaculties>5)){
//                System.out.println("Please enter the number within the specified length (i.e. 3-5)");
//                System.out.println("Enter the total number of faculties : ");
//                noOfFaculties = in.nextInt();
//            }
//
//            if(noOfFaculties>FYP_management_system.registeredFaculties.size()+FYP_management_system.registeredSupervisors.size()) {
//                System.out.println("Your entered number of faculties exceeds the total registered faculties/supervisors. Please register faculties/supervisors first.");
//                return;
//            } else {
//                addFacultyOrSupervisorToCommittee(FYP_management_system.registeredCommittees.get(searchCommitteeIdx(committeeNumber)));
//            }
//
//            System.out.println("Enter the total number of projects under supervision of this committee : ");
//            int noOfProjectsForCommittee = in.nextInt();
//
//            if(noOfProjectsForCommittee>10){
//                System.out.println("You are assigning more than 10 Projects to a single committee which is not allowed");
//            } else if (noOfProjectsForCommittee<0){
//                System.out.println("Your entered number of projects is not valid");
//            } else if(noOfProjectsForCommittee>FYP_management_system.registeredProjects.size()){
//                System.out.println("Your entered number of projects exceeds the total registered projects. Please register projects first.");
//            } else {
//                assignProjects(noOfProjectsForCommittee, committeeNumber);
//            }
//
//        } else {
//            System.out.println("Committee with this number is already available. Try registering with another committee number.");
//        }
//    }
//
//    private void addFacultyOrSupervisorToCommittee(Committee c){
//        System.out.println("Here's the list of faculties you can add to committee");
//        displayAvailableFaculties();
//        displayAvailableSupervisors();
//        for(int i=0;i<noOfFaculties;i++){
//            System.out.println("Enter the ID of faculty "+i+1+" : ");
//            String enteredFacultyId = in.nextLine();
//
//            System.out.println("Is he/she a supervisor (1=YES, 0=NO) : ");
//            int isSupervisor = in.nextInt();
//            if(isSupervisor==0){
//                int returnedIdxAfterSearch = reEnterFacultyId(enteredFacultyId);
//                if(returnedIdxAfterSearch!=-1){
//                    c.facultyList.add(FYP_management_system.registeredFaculties.get(returnedIdxAfterSearch));
//                    FYP_management_system.registeredFaculties.get(returnedIdxAfterSearch).isCommitteeMember=true;
//                }
//            } else {
//                int returnedIdxAfterSearch = reEnterSupervisorId(enteredFacultyId);
//                if(returnedIdxAfterSearch!=-1){
//                    c.facultyList.add(FYP_management_system.registeredSupervisors.get(returnedIdxAfterSearch));
//                    FYP_management_system.registeredSupervisors.get(returnedIdxAfterSearch).isCommitteeMember=true;
//                }
//            }
//        }
//    }
//
//    private int assignProjects(int nop, int cn){
//        int temp=0;
//        for(int i=0;i<nop;i++){
//            System.out.println("Enter the title of project "+i+1);
//            String title = in.nextLine();
//            if(searchProjectIdx(title)<0){
//                System.out.println("If you have misspelled the name of the project, kindly press 1");
//                int temp2 = in.nextInt();
//                in.nextLine();
//                if(temp2==1){
//                    System.out.println("Now, you can enter project "+i+1+"\'s title again : ");
//                    --i;
//                } else {
//                    continue;
//                }
//            } else {
//                FYP_management_system.registeredCommittees.get(searchCommitteeIdx(cn)).projectsUnderSupervision.add(FYP_management_system.registeredProjects.get(searchProjectIdx(title)));
//                temp = 1;
//            }
//        }
//        return temp;
//    }
//
//    private int searchProjectIdx(String title){
//        int j;
//        for(j=0;j<FYP_management_system.registeredProjects.size();j++){
//            if(FYP_management_system.registeredProjects.get(j).title.equalsIgnoreCase(title)){
//                break;
//            }
//            else if(j==FYP_management_system.registeredProjects.size()-1){
//                System.out.println("Please enter the correct title of the Project or register the project first : ");
//                return -1;
//            }
//        }
//        return j;
//    }
//
//    private int searchCommitteeIdx(int committeeNo){
//        int j;
//        for(j=0;j<FYP_management_system.registeredCommittees.size();j++){
//            if(FYP_management_system.registeredCommittees.get(j).committeeNumber == committeeNo){
//                break;
//            }
//            else if(j==FYP_management_system.registeredCommittees.size()-1){
//                System.out.println("Committee with this number is not registered : ");
//                return -1;
//            }
//        }
//        return j;
//    }
//
//    private int searchCommitteeIdxForComposing(int committeeNo){
//        int temp  = -1;
//        for(int j=0;j<FYP_management_system.registeredCommittees.size();j++){
//            if(FYP_management_system.registeredCommittees.get(j).committeeNumber == committeeNo){
//                temp = j;
//                break;
//            }
//        }
//        if(temp==-1){
//            System.out.println("Committee with this number is not registered : ");
//        }
//        return temp;
//    }
//
//    void displayCoordinatorDetails(){
//        System.out.println("Co-ordinator Name : " + username);
//    }
//
//    int reEnterSupervisorId(String id){
//        int check=1;
//        while(searchSupervisorIdx(id)==-1){
//            System.out.println("Well, we don't recognize faculty with this ID. It's either you have misspelled the ID or such faculty is not registered yet");
//            System.out.println("Check again if you have have misspelled the ID? (1 = Yes, 0 = No) ");
//            check = in.nextInt();
//            if(check==1){
//                System.out.println("Ok then. Enter the ID again.");
//                System.out.println("Enter the ID : ");
//                id = in.nextLine();
//            } else {
//                System.out.println("Please register faculty with this ID first");
//            }
//        }
//        if(check==1){
//            return searchSupervisorIdx(id);
//        } else {
//            return -1;
//        }
//    }
//
//    int reEnterFacultyId(String id){
//        int check=1;
//        while(searchFacultyIdx(id)==-1){
//            System.out.println("Well, we don't recognize faculty with this ID. It's either you have misspelled the ID or such faculty is not registered yet");
//            System.out.println("Check again if you have have misspelled the ID? (1 = Yes, 0 = No) ");
//            check = in.nextInt();
//            if(check==1){
//                System.out.println("Ok then.");
//                System.out.println("Enter the ID : ");
//                id = in.nextLine();
//            } else {
//                System.out.println("Please register faculty with this ID first");
//                break;
//            }
//        }
//        if(check==1){
//            return searchFacultyIdx(id);
//        } else {
//            return -1;
//        }
//    }
//
//    private int searchSupervisorIdx(String id){
//        int temp=-1;
//        for(int i=0;i<FYP_management_system.registeredSupervisors.size();i++){
//            if(FYP_management_system.registeredSupervisors.get(i).facultyID.equalsIgnoreCase(id)){
//                temp=i;
//                break;
//            }
//        }
//        return temp;
//    }
//
//
//    private int searchFacultyIdx(String id){
//        int temp=-1;
//        for(int i=0;i<FYP_management_system.registeredFaculties.size();i++){
//            if(FYP_management_system.registeredFaculties.get(i).facultyID.equalsIgnoreCase(id)){
//                temp=i;
//                break;
//            }
//        }
//        return temp;
//    }
//
//    void displayAvailableFaculties(){
//        for(int i=0;i<FYP_management_system.registeredFaculties.size();i++){
//            if(!FYP_management_system.registeredFaculties.get(i).isCommitteeMember)
//                FYP_management_system.registeredFaculties.get(i).displayFacultyDetails();
//        }
//    }
//
//    void displayAvailableSupervisors(){
//        for(int i=0;i<FYP_management_system.registeredSupervisors.size();i++){
//            if(!FYP_management_system.registeredSupervisors.get(i).isCommitteeMember)
//                FYP_management_system.registeredSupervisors.get(i).displayFacultyDetails();
//        }
//    }
}
