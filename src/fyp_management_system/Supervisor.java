/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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

/**
 *
 * @author Muhammad Noman
 */
public class Supervisor {
//    String password;
//    int projectsSupervised;
    Color frameBc = new Color(0x4188ff);
    Color mainPanelBc = new Color(0x78aaff);
    JButton logout = new JButton("Logout");
    JFrame frame = new JFrame();
    JPanel bottomPanel = new JPanel();
    JPanel mainPanel, sidePanel;

    public Supervisor(String id){
        frame.setTitle("Supervisor Dashboard");
        frame.setLayout(null);
        frame.getContentPane().setBackground(frameBc);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        JLabel name = new JLabel();
        JLabel facNo = new JLabel();
        JLabel projectsSupervised = new JLabel();
        JLabel committeeNo = new JLabel();
        JLabel greetingsLbl = new JLabel();

        //Data labels
        try{
            String extractDataQuery = "SELECT * FROM Supervisor WHERE facultyId = ?";
            PreparedStatement p1 = Conn.conn.prepareStatement(extractDataQuery);
            p1.setString(1, id);
            ResultSet result = p1.executeQuery();
            result.next();
            name.setText(result.getString("name"));
            facNo.setText(result.getString("facultyId"));
            projectsSupervised.setText(result.getString("projectsSupervised"));
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
        topPanel.setBounds(30, 30, 900, 300);
        topPanel.setBorder(new LineBorder(Color.BLACK));
        topPanel.setBackground(topColor);

        // Name
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setFont(metaFont);
        nameLbl.setBounds(20, 20, 300, 30);

        name.setFont(dataFont);
        name.setBounds(360, 20, 300, 30);

        // Registration Number
        JLabel facIdLbl = new JLabel("Faculty ID:");
        facIdLbl.setFont(metaFont);
        facIdLbl.setBounds(20, 60, 300, 30);

        facNo.setFont(dataFont);
        facNo.setBounds(360, 60, 300, 30);

        // Project Status
        JLabel projectsSupervisedLbl = new JLabel("No. of projects supervised ");
        projectsSupervisedLbl.setFont(metaFont);
        projectsSupervisedLbl.setBounds(20, 100, 300, 30);

        // isCommitteeMember
        projectsSupervised.setFont(dataFont);
        projectsSupervised.setBounds(360, 100, 300, 30);

        // Committee number
        JLabel committeeNoLbl = new JLabel("Committee Number : ");
        committeeNoLbl.setFont(metaFont);
        committeeNoLbl.setBounds(20, 140, 300, 30);

        committeeNo.setFont(dataFont);
        committeeNo.setBounds(360, 140, 300, 30);

//        // Date of Presentation
//        JLabel dopLbl = new JLabel("Date of Presentation:");
//        dopLbl.setFont(metaFont);
//        dopLbl.setBounds(20, 180, 300, 30);
//
//        dop.setFont(dataFont);
//        dop.setBounds(360, 180, 300, 30);

        // Add components to the top panel
        topPanel.add(nameLbl);
        topPanel.add(name);
        topPanel.add(facIdLbl);
        topPanel.add(facNo);
        topPanel.add(projectsSupervisedLbl);
        topPanel.add(projectsSupervised);
        topPanel.add(committeeNoLbl);
        topPanel.add(committeeNo);
//        topPanel.add(dopLbl);
//        topPanel.add(dop);

        // Add topPanel to the frame
        frame.add(topPanel);


        //Bottom panel
        bottomPanel.setBounds(30, 360, 900, 270);
        bottomPanel.setBorder(new LineBorder(Color.BLACK));
        bottomPanel.setBackground(bottomColor);

        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        //Sidepanel buttons
        Font font = new Font("Consolas", Font.BOLD, 16);
        JPanel greetingPanel = new JPanel(new FlowLayout());
        greetingPanel.setOpaque(false);
        greetingsLbl.setBorder(new EmptyBorder(50, 0, 30, 0));
        greetingsLbl.setFont(font);
        JPanel buttonsPanel = new JPanel(new GridLayout(5, 1));
        buttonsPanel.setOpaque(false);
        JButton showStudentRequest = new JButton("Show student requests");
        showStudentRequest.setFont(font);
        showStudentRequest.setBackground(Color.white);
        showStudentRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRequests(id);
            }
        });
        JButton showProposedProjects = new JButton("<html>Show proposed projects <br>by students<html>");
        showProposedProjects.setFont(font);
        showProposedProjects.setBackground(Color.WHITE);
        showProposedProjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleProposals(id);
            }
        });
        JButton projectStatus = new JButton("<html>See students under<br>my supervision<html>");
        projectStatus.setFont(font);
        projectStatus.setBackground(Color.WHITE);
        JButton showCommittee = new JButton("Show my committee");
        showCommittee.setFont(font);
        showCommittee.setBackground(Color.WHITE);
        JButton proposeProject = new JButton("Propose Project");
        proposeProject.setFont(font);
        proposeProject.setBackground(Color.WHITE);
        JPanel emptyPanel = new JPanel();
        emptyPanel.setOpaque(false);

        greetingPanel.setBounds(0, 0, sidePanel.getWidth(), sidePanel.getHeight()/5);
        buttonsPanel.setBounds(0, greetingPanel.getHeight()+10, sidePanel.getWidth(), (sidePanel.getHeight()/5)*3);
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

        buttonsPanel.add(showProposedProjects);
        buttonsPanel.add(projectStatus);
        buttonsPanel.add(showStudentRequest);
        buttonsPanel.add(showCommittee);
        buttonsPanel.add(proposeProject);

        sidePanel.add(greetingPanel);
        sidePanel.add(buttonsPanel);
        sidePanel.add(emptyPanel);

        frame.add(mainPanel);
        frame.add(sidePanel);

        frame.repaint();
        frame.revalidate();
    }

    void handleProposals(String supervisorId) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String getProposalsQuery = "SELECT studentId AS 'Student ID', title AS 'Project Title', " +
                "approvalStatus AS 'Proposal Status', filePath AS 'File Path' FROM proposedProjectsToSupervisor WHERE supervisorId = ?";

        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(getProposalsQuery);
            p1.setString(1, supervisorId); // Use the local supervisorId
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
            JTable proposalTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(proposalTable);
            JLabel proposalsLabel = new JLabel("Proposals Sent to You");

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
                            String proposalStatus = proposalTable.getValueAt(selectedRow, 2).toString();
                            String filePath = "";
                            String getFilePathQuery = "SELECT filePath FROM proposedProjectsToSupervisor WHERE studentId = ?";
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
                                                "Project Title: " + projectTitle,
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
                                    int yesNoCancel = JOptionPane.showConfirmDialog(frame,
                                            "Once accepted, this proposal will be forwarded to the Coordinator.\nDo you want to continue?",
                                            "Note",
                                            JOptionPane.YES_NO_CANCEL_OPTION);

                                    if(yesNoCancel == JOptionPane.YES_OPTION){
                                        int noOfStds = (int) JOptionPane.showInputDialog(frame,
                                                "Enter number of students",
                                                "Set number of students",
                                                JOptionPane.PLAIN_MESSAGE,
                                                null,
                                                new Object[]{1, 2, 3, 4}
                                                ,3);

                                        updateProposalStatus(studentId, supervisorId, projectTitle, "Approved", noOfStds, filePath);
                                    }
                                } else if(option == 2){
                                    updateProposalStatus(studentId, supervisorId, projectTitle, "Rejected", 0, "");
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

    void updateProposalStatus(String studentId, String supervisorId, String projectTitle, String status, int noOfStd, String filePath) {
        String updateProposalQuery;
        if(status.equalsIgnoreCase("Rejected")) {
            updateProposalQuery = "UPDATE proposedProjectsToSupervisor SET approvalStatus = ? WHERE title = ?";
            try {
                PreparedStatement p2 = Conn.conn.prepareStatement(updateProposalQuery);
                p2.setString(1, status);
                p2.setString(2, projectTitle);
                int rowsUpdated = p2.executeUpdate();

                if (rowsUpdated > 0) {
                    JOptionPane.showMessageDialog(frame,
                            "Proposal has been " + status.toLowerCase() + " successfully.",
                            "Success", JOptionPane.INFORMATION_MESSAGE);

                    // Refresh the proposals table after the update
                    handleProposals(supervisorId);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred while updating proposal: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
            }
        } else {
            updateProposalQuery = "UPDATE proposedProjectsToSupervisor SET noOfStdsReq = ? WHERE title = ?";
            sendProposalToCoordinator(projectTitle, studentId, supervisorId, noOfStd, filePath);

            try {
                PreparedStatement p2 = Conn.conn.prepareStatement(updateProposalQuery);
                p2.setInt(1, noOfStd);
                p2.setString(2, projectTitle);
                p2.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred while updating proposal: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println(ex.getMessage());
            }
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

    void sendProposalToCoordinator(String title, String studentId, String supervisorId, int noOfStdsReq, String filePath){
        String sendProposalToCoordinatorQuery = "INSERT INTO proposedProjectsToCoordinator (title, studentId, supervisorId, noOfStdsReq, filePath, approvalStatus)" +
                "VALUES (?, ?, ?, ?, ?, 'Pending')";

        try {
            PreparedStatement p = Conn.conn.prepareStatement(sendProposalToCoordinatorQuery);
            p.setString(1, title);
            p.setString(2, studentId);
            p.setString(3, supervisorId);
            p.setInt(4, noOfStdsReq);
            p.setString(5, filePath);
            int rowsEffected = p.executeUpdate();

            if(rowsEffected>0){
                JOptionPane.showMessageDialog(frame,
                        "Proposal sent to Coordinator successfully",
                        "Sent Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(frame,
                    "An error occured: "+e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


//
//    void showSupervisorDetails(){
//        System.out.println("Supervisor name : "+this.name+"\nSupervisor ID : "+this.facultyID+"\nNumber of projects Supervised till now : "+this.projectsSupervised);
//    }

    void handleRequests(String supervisorId) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String getRequestsQuery = "SELECT requestId AS 'Request ID', regNo AS 'Student Reg No', projectName AS 'Project Name', " +
                "requestDate AS 'Request Date', requestStatus AS 'Request Status' FROM RequestLogs WHERE supervisorId = ?";

        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(getRequestsQuery);
            p1.setString(1, supervisorId); // Use the local supervisorId
            ResultSet requests = p1.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            ResultSetMetaData metaData = requests.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnLabel(i);
                tableModel.addColumn(columnNames[i - 1]);
            }

            while (requests.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = requests.getObject(i);
                }
                tableModel.addRow(row);
            }

            bottomPanel.setLayout(null);
            JTable requestTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(requestTable);
            JLabel requestLabel = new JLabel("Requests Sent to You");

            requestLabel.setFont(new Font("Consolas", Font.BOLD, 18));
            requestLabel.setBounds(50, 10, 300, 30);
            scrollPane.setBounds(40, 60, 600, 200);
            scrollPane.setFont(new Font("Consolas", Font.BOLD, 20));

            bottomPanel.add(requestLabel);
            bottomPanel.add(scrollPane);

            // Add mouse listener to the table
            requestTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) { // Single click
                        int selectedRow = requestTable.getSelectedRow();
                        if (selectedRow != -1) {
                            // Get request details from the selected row
                            int requestId = Integer.parseInt(requestTable.getValueAt(selectedRow, 0).toString());
                            String studentRegNo = requestTable.getValueAt(selectedRow, 1).toString();
                            String projectName = requestTable.getValueAt(selectedRow, 2).toString();
                            String requestDate = requestTable.getValueAt(selectedRow, 3).toString();
                            String requestStatus = requestTable.getValueAt(selectedRow, 4).toString();

                            // Show details in a JOptionPane
                            if(!(requestStatus.equalsIgnoreCase("accepted")) && !(requestStatus.equalsIgnoreCase("Rejected"))){
                                int option = JOptionPane.showOptionDialog(
                                        frame,
                                        "Request Details:\n" +
                                                "Request ID: " + requestId + "\n" +
                                                "Student Reg No: " + studentRegNo + "\n" +
                                                "Project Name: " + projectName + "\n" +
                                                "Request Date: " + requestDate + "\n" +
                                                "Request Status: " + requestStatus,
                                        "Handle Request",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        new Object[]{"Accept", "Reject"},
                                        "Accept"
                                    );

                                    if (option == JOptionPane.YES_OPTION) {
                                        updateRequestStatus(supervisorId, requestId, "Accepted"); // Pass supervisorId
                                    } else if (option == JOptionPane.NO_OPTION) {
                                        updateRequestStatus(supervisorId, requestId, "Rejected"); // Pass supervisorId
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(frame,
                                            "Request is already "+requestStatus,
                                            "Request seen already", JOptionPane.INFORMATION_MESSAGE, null);
                                }
                            }
                        }
                    }
                });

            bottomPanel.repaint();
            bottomPanel.revalidate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while retrieving requests: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void updateRequestStatus(String supervisorId, int requestId, String status) {
        String updateRequestQuery = "UPDATE RequestLogs SET requestStatus = ? WHERE requestId = ?";

        try {
            PreparedStatement p2 = Conn.conn.prepareStatement(updateRequestQuery);
            p2.setString(1, status);
            p2.setInt(2, requestId);
            int rowsUpdated = p2.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(frame,
                        "Request has been " + status.toLowerCase() + " successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);

                // Refresh the requests table after the update
                handleRequests(supervisorId); // Pass supervisorId to refresh the table
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "An error occurred while updating request: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
