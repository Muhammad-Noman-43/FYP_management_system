/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
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
 * @author Muhammad Noman
 */
public class Student implements ActionListener {
    JFrame frame;
    ResultSet result;
    JLabel greetingsLbl = new JLabel();
    JLabel name = new JLabel();
    JLabel regNo = new JLabel();
    JLabel project = new JLabel();
    JLabel projStatus = new JLabel();
    JLabel grouped = new JLabel();
    JLabel supervisor = new JLabel();
    JLabel dop = new JLabel();
    Color frameBc = new Color(0x4188ff);
    Color mainPanelBc = new Color(0x78aaff);
    JButton logout = new JButton("Logout");
    String filePath = "";

    JPanel bottomPanel;


    public Student(String id) {
        frame = new JFrame();
        JPanel mainPanel, sidePanel;
        frame.setTitle("Student Dashboard");
        frame.setLayout(null);
        frame.getContentPane().setBackground(frameBc);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
        Color topColor = new Color(255,255,255);
        Color bottomColor = new Color(255,255,255);
        // Fonts
        Font dataFont = new Font("Consolas", Font.BOLD, 18); // Font for data
        Font metaFont = new Font("Consolas", Font.ITALIC, 15); // Font for metadata (Lbls)

        // Create a JPanel for the dashboard
        JPanel topPanel = new JPanel(null); // Absolute positioning for the panel
        topPanel.setBounds(30, 30, 900, 300);
        topPanel.setBorder(new LineBorder(Color.BLACK));
        topPanel.setBackground(topColor);

        //Data Labels
        try{
            String extractDataQuery = "SELECT * FROM Student WHERE regNo = ?";
            PreparedStatement p1 = Conn.conn.prepareStatement(extractDataQuery);
            p1.setString(1, id);
            result = p1.executeQuery();
            result.next();
            name.setText(result.getString("name"));
            regNo.setText(result.getString("regNo"));
            projStatus.setText(result.getString("project_status"));
            grouped.setText((result.getInt("groupNumber") != 0) ? String.valueOf(result.getInt("groupNumber")) : "None");
            dop.setText(result.getString("dateOfPresentation"));
            project.setText(result.getString("projectName"));
            supervisor.setText(result.getString("supervisorId"));
            greetingsLbl.setText("Hello, "+result.getString("name"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        // Name
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setFont(metaFont);
        nameLbl.setBounds(20, 20, 300, 30);

        name.setFont(dataFont);
        name.setBounds(360, 20, 300, 30);

        // Registration Number
        JLabel regNoLbl = new JLabel("Registration Number:");
        regNoLbl.setFont(metaFont);
        regNoLbl.setBounds(20, 60, 300, 30);

        regNo.setFont(dataFont);
        regNo.setBounds(360, 60, 300, 30);



        // Project Status
        JLabel projStatusLbl = new JLabel("Project Status:");
        projStatusLbl.setFont(metaFont);
        projStatusLbl.setBounds(20, 100, 300, 30);

        projStatus.setFont(dataFont);
        projStatus.setBounds(360, 100, 300, 30);

        //Project
        JLabel projectLbl = new JLabel("Project Name:");
        projectLbl.setFont(metaFont);
        projectLbl.setBounds(20, 140, 300, 30);

        project.setFont(dataFont);
        project.setBounds(360, 140, 300, 30);

        // Supervisor
        JLabel supervisorLbl = new JLabel("Supervisor:");
        supervisorLbl.setFont(metaFont);
        supervisorLbl.setBounds(20, 180, 300, 30);

        supervisor.setFont(dataFont);
        supervisor.setBounds(360, 180, 300, 30);

        // Date of Presentation
        JLabel dopLbl = new JLabel("Date of Presentation:");
        dopLbl.setFont(metaFont);
        dopLbl.setBounds(20, 220, 300, 30);

        dop.setFont(dataFont);
        dop.setBounds(360, 220, 300, 30);

        // Grouped with Students
        JLabel groupedLbl = new JLabel("Group Number :");
        groupedLbl.setFont(metaFont);
        groupedLbl.setBounds(20, 250, 300, 40);

        grouped.setFont(dataFont);
        grouped.setBounds(360, 260, 300, 30);

        // Add components to the top panel
        topPanel.add(nameLbl);
        topPanel.add(name);
        topPanel.add(regNoLbl);
        topPanel.add(regNo);
        topPanel.add(projectLbl);
        topPanel.add(project);
        topPanel.add(projStatusLbl);
        topPanel.add(projStatus);
        topPanel.add(supervisorLbl);
        topPanel.add(supervisor);
        topPanel.add(dopLbl);
        topPanel.add(dop);
        topPanel.add(groupedLbl);
        topPanel.add(grouped);

        // Add topPanel to the frame
        frame.add(topPanel);


        //Bottom panel
        bottomPanel = new JPanel();
        bottomPanel.setBounds(30, 360, 900, 270);
        bottomPanel.setBorder(new LineBorder(Color.BLACK));
        bottomPanel.setBackground(bottomColor);

        mainPanel.add(topPanel);
        mainPanel.add(bottomPanel);

        //Sidepanel buttons
        Font font = new Font("Consolas", Font.BOLD, 16);
        JPanel greetingPanel = new JPanel();
        greetingPanel.setOpaque(false);
        greetingsLbl.setBorder(new EmptyBorder(50, 0, 30, 0));
        greetingsLbl.setFont(new Font("Consolas", Font.BOLD, 20));
        JPanel buttonsPanel = new JPanel(new GridLayout(7, 1));
        buttonsPanel.setOpaque(false);
        JButton proposeProject = new JButton("Propose Project");
        proposeProject.setFont(font);
        proposeProject.setBackground(Color.white);
        proposeProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String checkIfProjectTake = "SELECT projectName FROM Student WHERE regNo = ?";
                try {
                    PreparedStatement p = Conn.conn.prepareStatement(checkIfProjectTake);
                    p.setString(1, id);
                    ResultSet r = p.executeQuery();
                    r.next();
                    if(r.getString(1) != null){
                        JOptionPane.showMessageDialog(frame,
                                "You already have selected a project.",
                                "Project already selected",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        proposeProject(id);
                    }
                } catch (SQLException e2){
                    System.out.println(e2.getMessage());
                }
            }
        });
        JButton selectProject = new JButton("Select a Project");
        selectProject.setFont(font);
        selectProject.setBackground(Color.white);
        selectProject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectProject(id);
            }
        });
        JButton showGroup = new JButton("Show my group");
        showGroup.setFont(font);
        showGroup.setBackground(Color.white);
        showGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyGroup(id);
            }
        });
        JButton groupWithStdsBtn = new JButton("Create group with students");
        groupWithStdsBtn.setFont(font);
        groupWithStdsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getProject = "SELECT projectName, groupNumber FROM Student WHERE regNo = ?";
                try {
                    PreparedStatement p = Conn.conn.prepareStatement(getProject);
                    p.setString(1, id);
                    ResultSet r = p.executeQuery();
                    r.next();
                    if(r.getString(1) != null){
                        String getNoOfStdsReq = "SELECT noOfStdsReq FROM ApprovedProjects WHERE title = ?";
                        PreparedStatement p2 = Conn.conn.prepareStatement(getNoOfStdsReq);
                        p2.setString(1, r.getString(1));
                        ResultSet r2 = p2.executeQuery();
                        r2.next();

                        String countGroupMembers = "SELECT COUNT(*) FROM Student WHERE groupNumber = ?";
                        PreparedStatement p3 = Conn.conn.prepareStatement(countGroupMembers);
                        p3.setInt(1, r.getInt(2));
                        ResultSet r3 = p3.executeQuery();
                        r3.next();

                        //Check if the total group members of this student is < total stds required for the project selected by this student
                        if(r3.getInt(1) < r2.getInt(1)){
                            groupWithStds(id, true);
                        } else {
                            JOptionPane.showMessageDialog(frame,
                                    "Total students required for the project you selected and the" +
                                            "\ntotal members of your group have reached the limit." +
                                            "\nYou can not select more group members for your project.",
                                    "Group members limit reached",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        groupWithStds(id, false);
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        groupWithStdsBtn.setBackground(Color.white);
        JButton showCommittee = new JButton("Show my supervising committee");
        showCommittee.setFont(font);
        showCommittee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showSupervisingCommittee(id);
            }
        });
        showCommittee.setBackground(Color.white);

        JButton showGroupRequests = new JButton("Show group requests");
        showGroupRequests.setFont(font);
        showGroupRequests.setBackground(Color.white);
        showGroupRequests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGroupRequests(id);
            }
        });

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

        buttonsPanel.add(groupWithStdsBtn);
        buttonsPanel.add(selectProject);
        buttonsPanel.add(showGroupRequests);
        buttonsPanel.add(proposeProject);
        buttonsPanel.add(showGroup);
        buttonsPanel.add(showCommittee);

        sidePanel.add(greetingPanel);
        sidePanel.add(buttonsPanel);
        sidePanel.add(emptyPanel);

        frame.add(mainPanel);
        frame.add(sidePanel);

        frame.repaint();
        frame.revalidate();
    }


    void groupWithStds(String id, Boolean hasProject) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String getUngroupedStudents;
        if(hasProject)
            getUngroupedStudents = "SELECT name, regNo FROM Student WHERE isGrouped IS NULL AND NOT regNo = ? AND projectName IS NULL OR isGrouped = FALSE";
        else
            getUngroupedStudents = "SELECT name, regNo, projectName FROM Student WHERE isGrouped IS NULL AND NOT regNo = ? OR isGrouped = FALSE";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(getUngroupedStudents);
            p1.setString(1, id);
            ResultSet ungroupedStudents = p1.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            String[] colNames = {"Name", "Reg. No.", "Project Name"};

            for (String colName : colNames) {
                tableModel.addColumn(colName);
            }

            while (ungroupedStudents.next()) {
                Object[] row = new Object[colNames.length];
                for (int i = 1; i <= colNames.length; i++) {
                    row[i - 1] = ungroupedStudents.getObject(i);
                }
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Makes all cells non-editable
                }
            };

            JScrollPane scrollPane = new JScrollPane(table);
            JLabel stdLbl = new JLabel("Ungrouped Students");
            table.setFont(new Font("Consolas", Font.PLAIN, 16));

            // Add mouse listener for row click
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String selectedStudentName = table.getValueAt(row, 0).toString();
                    String selectedStudentRegNo = table.getValueAt(row, 1).toString();

                    String checkRequestAtRequestLogs = "SELECT * FROM groupRequests WHERE requesterId = ? AND requestedId = ? AND status = 'Pending'";
                    try {
                        PreparedStatement pendingRequestFound = Conn.conn.prepareStatement(checkRequestAtRequestLogs);
                        pendingRequestFound.setString(1, id);
                        pendingRequestFound.setString(2, selectedStudentRegNo);
                        ResultSet r = pendingRequestFound.executeQuery();

                        if(r.next()) {
                            JOptionPane.showMessageDialog(frame,
                                    "Request already sent",
                                    "Request already sent",
                                    JOptionPane.INFORMATION_MESSAGE
                                    );
                            return;
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    if(!hasProject){
                        String getGNOfSelector = "SELECT groupNumber FROM Student WHERE regNo = ?";
                        String getSelectedStdProject = "SELECT projectName FROM Student WHERE regNo = ?";
                        try{
                            PreparedStatement p0 = Conn.conn.prepareStatement(getGNOfSelector);
                            p0.setString(1, id);
                            ResultSet r0 = p0.executeQuery();
                            r0.next();

                            PreparedStatement p1 = Conn.conn.prepareStatement(getSelectedStdProject);
                            p1.setString(1, selectedStudentRegNo);
                            ResultSet r1 = p1.executeQuery();
                            r1.next();


                            if(r1.getString(1)!=null){
                                String getSelectedNoOfStdReq = "SELECT noOfStdsReq FROM ApprovedProjects WHERE title = ?";
                                PreparedStatement p2 = Conn.conn.prepareStatement(getSelectedNoOfStdReq);
                                p2.setString(1, r1.getString(1));
                                ResultSet r2 = p2.executeQuery();
                                r2.next();

                                if(r0.getInt(1) != 0){
                                    String countSelectorGroupMembers = "SELECT COUNT(*) FROM Student WHERE groupNumber = ?";
                                    PreparedStatement p3 = Conn.conn.prepareStatement(countSelectorGroupMembers);
                                    p3.setInt(1, r0.getInt(1));
                                    ResultSet r3 = p3.executeQuery();
                                    r3.next();

                                    if(r3.getInt(1) < r2.getInt(1)){
                                        int choice = JOptionPane.showConfirmDialog(frame,
                                                "Do you want to send a group request to " + selectedStudentName + " (" + selectedStudentRegNo + ")?",
                                                "Group Request", JOptionPane.YES_NO_OPTION);

                                        if (choice == JOptionPane.YES_OPTION) {
                                            sendGroupRequest(id, selectedStudentRegNo, false);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Your total group members exceeds the number of" +
                                                "\nstudents required for the project of this selected student." +
                                                "\nTherefore, you can't make group with this student.",
                                                "Group members count exceeds projects required student",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    if(r2.getInt(1) <= 2){
                                        int choice = JOptionPane.showConfirmDialog(frame,
                                                "Do you want to send a group request to " + selectedStudentName + " (" + selectedStudentRegNo + ")?",
                                                "Group Request", JOptionPane.YES_NO_OPTION);

                                        if (choice == JOptionPane.YES_OPTION) {
                                            sendGroupRequest(id, selectedStudentRegNo, false);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(frame, "Your total group members exceeds the number of" +
                                                        "\nstudents required for the project of this selected student." +
                                                        "\nTherefore, you can't make group with this student.",
                                                "Group members count exceeds projects required student",
                                                JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            } else {
                                int choice = JOptionPane.showConfirmDialog(frame,
                                        "Do you want to send a group request to " + selectedStudentName + " (" + selectedStudentRegNo + ")?",
                                        "Group Request", JOptionPane.YES_NO_OPTION);

                                if (choice == JOptionPane.YES_OPTION) {
                                    sendGroupRequest(id, selectedStudentRegNo, false);
                                }
                            }
                        } catch(SQLException ex2){
                            JOptionPane.showMessageDialog(frame,
                                    ex2.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        int choice = JOptionPane.showConfirmDialog(frame,
                                "Do you want to send a group request to " + selectedStudentName + " (" + selectedStudentRegNo + ")?",
                                "Group Request", JOptionPane.YES_NO_OPTION);

                        if (choice == JOptionPane.YES_OPTION) {
                            sendGroupRequest(id, selectedStudentRegNo, false);
                        }
                    }
                }
            });

            bottomPanel.setLayout(null);
            stdLbl.setBounds(40, 10, 300, 30);
            stdLbl.setFont(new Font("Consolas", Font.BOLD, 18));
            scrollPane.setBounds(30, 60, 600, 200);

            bottomPanel.add(stdLbl);
            bottomPanel.add(scrollPane);

            bottomPanel.repaint();
            bottomPanel.revalidate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void sendGroupRequest(String requesterId, String requestedId, Boolean hasProject) {
        String insertRequest = "INSERT INTO groupRequests (requesterId, requestedId) VALUES (?, ?)";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(insertRequest);
            p1.setString(1, requesterId);
            p1.setString(2, requestedId);
            p1.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Group request sent successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            groupWithStds(requesterId, hasProject);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error sending group request: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    void showSupervisingCommittee(String id){
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String committeeNumber = "SELECT committeeSupervising FROM Student WHERE regNo = ?";
        try {
            PreparedStatement getCN = Conn.conn.prepareStatement(committeeNumber);
            getCN.setString(1, id);
            ResultSet r = getCN.executeQuery();
            r.next();
            int CN = r.getInt(1);

            String getSupQ = "SELECT name, facultyId FROM Supervisor WHERE committeeNumber = ?";
            PreparedStatement getSup = Conn.conn.prepareStatement(getSupQ);
            getSup.setInt(1,CN);
            ResultSet supData = getSup.executeQuery();
//            ResultSetMetaData supMetaData = supData.getMetaData();
            String getFacQ = "SELECT name, facultyId FROM Faculty WHERE committeeNumber = ?";
            PreparedStatement getFac = Conn.conn.prepareStatement(getFacQ);
            getFac.setInt(1,CN);
            ResultSet facData = getFac.executeQuery();
//            ResultSetMetaData facMetaData = facData.getMetaData();


            String[] colNames = {"Name", "Faculty ID"};

            DefaultTableModel supModel = new DefaultTableModel();

            for (String colName : colNames) {
                supModel.addColumn(colName);
            }
            while (supData.next()) {
                Object[] row = new Object[colNames.length];
                for (int i = 1; i <= colNames.length; i++) {
                    row[i-1] = supData.getObject(i);
                }
                supModel.addRow(row);
            }

            DefaultTableModel facModel = new DefaultTableModel();

            for (String colName : colNames) {
                facModel.addColumn(colName);
            }
            while (facData.next()) {
                Object[] row = new Object[colNames.length];
                for (int i = 1; i <= colNames.length; i++) {
                    row[i-1] = facData.getObject(i);
                }
                facModel.addRow(row);
            }

            JTable supTable = new JTable(supModel){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Makes all cells non-editable
                }
            };
            JScrollPane scrollPane1 = new JScrollPane(supTable);
            JTable facTable = new JTable(facModel){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Makes all cells non-editable
                }
            };
            JScrollPane scrollPane2 = new JScrollPane(facTable);

            bottomPanel.setLayout(null);

            JLabel supervisors = new JLabel("Supervisor");
            JLabel facultyMember = new JLabel("Faculty Members");

            scrollPane1.setBounds(10, 40, 400, 200);
            bottomPanel.add(scrollPane1);
            scrollPane2.setBounds(scrollPane1.getWidth()+10, 40, 400, 200);
            bottomPanel.add(scrollPane2);

            supervisors.setBounds(40, 10, scrollPane1.getWidth()-40, 30);
            supervisors.setFont(new Font("Consolas", Font.BOLD, 16));
            facultyMember.setBounds(scrollPane1.getWidth()+30, 10, scrollPane2.getWidth()-40, 30);
            facultyMember.setFont(new Font("Consolas", Font.BOLD, 16));

            bottomPanel.add(facultyMember);
            bottomPanel.add(supervisors);

            bottomPanel.add(scrollPane1);
            bottomPanel.add(scrollPane2);

            bottomPanel.repaint();
            bottomPanel.revalidate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void showMyGroup(String id) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String groupNumber = "SELECT groupNumber FROM Student WHERE regNo = ?";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(groupNumber);
            p1.setString(1, id);
            ResultSet GN = p1.executeQuery();
            GN.next();

            String getGroupedSt = "SELECT name, regNo FROM Student WHERE groupNumber = ? AND NOT regNo = ?";
            PreparedStatement p2 = Conn.conn.prepareStatement(getGroupedSt);
            p2.setInt(1, GN.getInt(1));
            p2.setString(2, id);
            ResultSet stdGroup = p2.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            String[] colNames = {"Name", "Reg. No."};

            for(String colName:colNames){
                tableModel.addColumn(colName);
            }

            while (stdGroup.next()) {
                Object[] row = new Object[colNames.length];
                for (int i = 1; i <= colNames.length; i++) {
                    row[i-1] = stdGroup.getObject(i);
                }
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Makes all cells non-editable
                }
            };
            JScrollPane scrollPane = new JScrollPane(table);
            JLabel stdLbl = new JLabel("Students you are grouped with");
            table.setFont(new Font("Consolas",Font.BOLD, 16));

            bottomPanel.setLayout(null);
            stdLbl.setBounds(40, 10, 300, 30);
            stdLbl.setFont(new Font("Consolas", Font.BOLD, 18));
            scrollPane.setBounds(30, 60, 400, 200);

            bottomPanel.add(stdLbl);
            bottomPanel.add(scrollPane);

            bottomPanel.repaint();
            bottomPanel.revalidate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void selectProject(String id) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String checkForAlreadyTakenProj = "SELECT projectName, groupNumber FROM Student WHERE regNo = ?";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(checkForAlreadyTakenProj);
            p1.setString(1, id);
            ResultSet r = p1.executeQuery();
            r.next();

            if (r.getString(1) != null) {
                JOptionPane.showMessageDialog(frame, "You already have selected a project.",
                        "You want more trouble?", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DefaultTableModel tableModel = new DefaultTableModel();
                String getApprovedProj = "SELECT title AS Title, supervisorId AS 'Supervisor ID', " +
                        "committeeSupervising AS 'Committee Supervising', noOfStdsReq AS 'Number of Students required' " +
                        "FROM ApprovedProjects WHERE ProjectStatus = 'Not taken'";
                PreparedStatement p2 = Conn.conn.prepareStatement(getApprovedProj);
                ResultSet approvedProjects = p2.executeQuery();
                ResultSetMetaData metaData = approvedProjects.getMetaData();
                int getColumnCount = metaData.getColumnCount();
                String[] colNames = new String[getColumnCount];

                for (int i = 1; i <= colNames.length; i++) {
                    colNames[i - 1] = metaData.getColumnLabel(i);
                    tableModel.addColumn(colNames[i - 1]);
                }

                while (approvedProjects.next()) {
                    Object[] row = new Object[getColumnCount];
                    for (int i = 1; i <= getColumnCount; i++) {
                        row[i - 1] = approvedProjects.getObject(i);
                    }
                    tableModel.addRow(row);
                }

                bottomPanel.setLayout(null);
                JTable table = new JTable(tableModel){
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false; // Makes all cells non-editable
                    }
                };

                JScrollPane scrollPane = new JScrollPane(table);
                JLabel approvedProjLbl = new JLabel("List of available projects");

                approvedProjLbl.setFont(new Font("Consolas", Font.BOLD, 18));
                approvedProjLbl.setBounds(50, 10, 300, 30);
                scrollPane.setBounds(40, 60, 600, 200);
                scrollPane.setFont(new Font("Consolas", Font.BOLD, 20));

                bottomPanel.add(approvedProjLbl);
                bottomPanel.add(scrollPane);

                // Add mouse click listener to the table
                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 1) { // Single click
                            int selectedRow = table.getSelectedRow();
                            if (selectedRow != -1) {
                                // Get project details from the selected row
                                String projectTitle = table.getValueAt(selectedRow, 0).toString();
                                String supervisorId = table.getValueAt(selectedRow, 1).toString();
                                String committee = (table.getValueAt(selectedRow, 2) == null)? "" : table.getValueAt(selectedRow, 2).toString();
                                String noOfStudents = table.getValueAt(selectedRow, 3).toString();

                                String checkForPendingRequests = "SELECT * FROM RequestLogs WHERE regNo = ? AND requestStatus = 'Pending'";
                                try {
                                    PreparedStatement getPendingReqsStatement = Conn.conn.prepareStatement(checkForPendingRequests);
                                    getPendingReqsStatement.setString(1, id);
                                    ResultSet getPendingReqs = getPendingReqsStatement.executeQuery();

                                    if(getPendingReqs.next()){
                                        JOptionPane.showMessageDialog(frame,
                                                "Request sent already",
                                                "Request sent already",
                                                JOptionPane.INFORMATION_MESSAGE
                                                );
                                        return;
                                    }
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(frame,
                                            "An SQL error occured" +
                                                    "\n"+ex.getMessage(),
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }


                                int getSameGroupMember;
                                ResultSet r2;
                                try {
                                    String sameGroupMembersCount = "SELECT COUNT(*) FROM Student WHERE groupNumber = ?";
                                    PreparedStatement p3 = Conn.conn.prepareStatement(sameGroupMembersCount);
                                    p3.setInt(1, r.getInt(2));
                                    r2 = p3.executeQuery();
                                    r2.next();
                                    getSameGroupMember = r2.getInt(1);
                                } catch (SQLException ex) {
                                    throw new RuntimeException(ex);
                                }

                                int temp = Integer.parseInt(noOfStudents);
                                if(getSameGroupMember <=  temp){
                                    // Show details in an option pane
                                    int option = JOptionPane.showOptionDialog(
                                            frame,
                                            "Project Details:\n" +
                                                    "Title: " + projectTitle + "\n" +
                                                    "Supervisor ID: " + supervisorId + "\n" +
                                                    "Committee Supervising: " + committee + "\n" +
                                                    "Number of Students Required: " + noOfStudents,
                                            "Select Project",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            new Object[]{"Select this project", "Cancel"},
                                            "Select this project"
                                    );

                                    if (option == JOptionPane.YES_OPTION) {
                                        // Insert a request into RequestLogs table
                                    try {
                                        String insertRequest = "INSERT INTO RequestLogs (regNo, projectName, supervisorId, requestDate) " +
                                                "VALUES (?, ?, ?, CURDATE())";
                                        PreparedStatement p3 = Conn.conn.prepareStatement(insertRequest);
                                        p3.setString(1, id); // Student regNo
                                        p3.setString(2, projectTitle); // Project name
                                        p3.setString(3, supervisorId); // Supervisor ID
                                        p3.executeUpdate();

                                        JOptionPane.showMessageDialog(frame,
                                                "Your request for the project has been sent to the supervisor.",
                                                "Request Sent", JOptionPane.INFORMATION_MESSAGE);
                                    } catch (SQLException ex) {
                                        JOptionPane.showMessageDialog(frame,
                                                "An error occurred while sending the request: " + ex.getMessage(),
                                                "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Your group's total number of students exceeds" +
                                            "\nthe limit of students required for this project.",
                                            "You can't take this project",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                });

                bottomPanel.repaint();
                bottomPanel.revalidate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    void showGroupRequests(String id) {
        bottomPanel.removeAll();
        bottomPanel.repaint();
        bottomPanel.revalidate();

        String getGroupRequests = "SELECT s.name, s.regNo, gr.status, s.projectName FROM groupRequests gr " +
                "JOIN Student s ON gr.requesterId = s.regNo " +
                "WHERE gr.requestedId = ?";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(getGroupRequests);
            p1.setString(1, id);
            ResultSet groupRequests = p1.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            String[] colNames = {"Name", "Reg. No.", "Status", "Project Name"};

            for (String colName : colNames) {
                tableModel.addColumn(colName);
            }

            while (groupRequests.next()) {
                Object[] row = new Object[colNames.length];
                for (int i = 1; i <= colNames.length; i++) {
                    row[i - 1] = groupRequests.getObject(i);
                }
                tableModel.addRow(row);
            }

            JTable table = new JTable(tableModel) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            JScrollPane scrollPane = new JScrollPane(table);
            JLabel reqLbl = new JLabel("Group Requests");
            table.setFont(new Font("Consolas", Font.BOLD, 16));

            // Add mouse listener for row click
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = table.getSelectedRow();
                    String selectedStudentName = table.getValueAt(row, 0).toString();
                    String selectedStudentRegNo = table.getValueAt(row, 1).toString();
                    String currentStatus = table.getValueAt(row, 2).toString();
                    String selectedStdProjectName = (table.getValueAt(row, 3)==null)? "None":table.getValueAt(row, 3).toString();

                    if (!currentStatus.equalsIgnoreCase("Pending")) {
                        JOptionPane.showMessageDialog(frame,
                                "This request is already " + currentStatus + ".",
                                "Request Status", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    Object[] options = {"Accept", "Reject", "Cancel"};
                    int choice = JOptionPane.showOptionDialog(frame,
                            "Do you want to accept or reject the group request from " + selectedStudentName + " (" + selectedStudentRegNo + ")?",
                            "Group Request", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[2]);

                    if (choice == 0) { // Accept
                        updateRequestStatus(selectedStudentRegNo, id, "Accepted");
                    } else if (choice == 1) { // Reject
                        updateRequestStatus(selectedStudentRegNo, id, "Rejected");
                    }
                }
            });

            bottomPanel.setLayout(null);
            reqLbl.setBounds(40, 10, 300, 30);
            reqLbl.setFont(new Font("Consolas", Font.BOLD, 18));
            scrollPane.setBounds(30, 60, 700, 200);

            bottomPanel.add(reqLbl);
            bottomPanel.add(scrollPane);

            bottomPanel.repaint();
            bottomPanel.revalidate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void updateRequestStatus(String senderRegNo, String receiverRegNo, String status) {
        String updateRequest = "UPDATE groupRequests SET status = ? " +
                "WHERE requesterId = ? AND requestedId = ?";
        try {
            PreparedStatement p1 = Conn.conn.prepareStatement(updateRequest);
            p1.setString(1, status);
            p1.setString(2, senderRegNo);
            p1.setString(3, receiverRegNo);
            int rowsAffected = p1.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame,
                        "Request has been " + status.toLowerCase() + ".",
                        "Request Updated", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Failed to update the request. Please try again.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame,
                    "A error occurred while updating request status:\n"+e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public String getSelectedRadioButtonText(ButtonGroup group) {
        ButtonModel selectedModel = group.getSelection();
        if (selectedModel != null) {
            return selectedModel.getActionCommand();
        }
        return null;
    }


    private void proposeProject(String id) {
        int option = JOptionPane.showConfirmDialog(frame,
                "Please make sure you have:" +
                        "\n✔ Project proposal in word document (.docx files only)" +
                        "\n✔ Project title in less than 100 characters" +
                        "\n✔ Supervisor with ID already registered",
                "Items required",
                JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION){
            JFrame projectProposingFrame = new JFrame("Enter project's details");
            projectProposingFrame.setVisible(true);
            projectProposingFrame.setSize(500, 500);
            projectProposingFrame.setLocationRelativeTo(frame);
            projectProposingFrame.setLayout(null);
            projectProposingFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

            JLabel frameDescLbl = new JLabel("Project Proposal");
            JTextField title = new JTextField();
            JLabel titleLbl = new JLabel("<html>Enter title of your project<br>(in less than 100 characters):<html>");
            JTextField supervisorId = new JTextField();
            JLabel supervisorIdLbl = new JLabel("<html>Enter the supervisor ID<br> you want to send proposal to<html>:");
            JButton chooseFileBtn = new JButton("Choose a file");
            JLabel chooseFileLbl = new JLabel("<html>Please choose your file<br>(Word file i.e. docx only)<html>");
            JButton sendProposal = new JButton("Send Proposal");

            Font lblFont = new Font("Consolas", Font.BOLD, 14);
            titleLbl.setBounds(0, 60, 200, 60);
            titleLbl.setFont(lblFont);
            title.setBounds(titleLbl.getWidth()+20, 60, 180, 24);
            supervisorIdLbl.setBounds(0, 160, 200, 60);
            supervisorIdLbl.setFont(lblFont);
            supervisorId.setBounds(supervisorIdLbl.getWidth()+20, 170, 180, 24);
            chooseFileLbl.setBounds(0, 260, 200, 60);
            chooseFileLbl.setFont(lblFont);
            chooseFileBtn.setBounds(chooseFileLbl.getWidth()+20, 260, 180, 24);
            sendProposal.setBounds(100, 340, 180, 30);
            chooseFileBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    chooseFile();
                }
            });

            sendProposal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String checkIfSupervisorIsBusy = "SELECT * FROM ApprovedProjects WHERE supervisorId = ?";
                    try{
                        PreparedStatement p = Conn.conn.prepareStatement(checkIfSupervisorIsBusy);
                        p.setString(1, supervisorId.getText());
                        ResultSet r = p.executeQuery();

                        if(r.next()){
                            JOptionPane.showMessageDialog(frame,
                                    "Sorry! But one supervisor can take only one project. Your entered\n" +
                                            "supervisor has already taken another project. You should opt for another supervisor",
                                    "Supervisor Busy",
                                    JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            if(title.getText().isEmpty() || supervisorId.getText().isEmpty() || filePath.isEmpty()){
                                projectProposingFrame.setVisible(true);
                                JOptionPane.showMessageDialog(frame,
                                        "Please enter all details");
                            } else {
                                addFilePathToDatabase(id, title.getText(),supervisorId.getText(), projectProposingFrame);
                            }
                        }
                    } catch (SQLException ex){
                        JOptionPane.showMessageDialog(frame,
                                "An error occurred"+ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            JPanel panel = new JPanel(null);
            panel.add(titleLbl);
            panel.add(title);
            panel.add(supervisorIdLbl);
            panel.add(supervisorId);
            panel.add(chooseFileLbl);
            panel.add(chooseFileBtn);
            panel.add(sendProposal);

            frameDescLbl.setFont(new Font("Consolas", Font.BOLD, 20));
            frameDescLbl.setBounds(160, 20, 200, 20);
            panel.setBounds(50, 50, 400, 400);
            projectProposingFrame.add(frameDescLbl);
            projectProposingFrame.add(panel);
        }
    }

    void chooseFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Word Documents","docx"));
        int result = chooser.showOpenDialog(frame);
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = chooser.getSelectedFile();
            filePath = selectedFile.getAbsolutePath();
        }
    }

    void addFilePathToDatabase(String proposerId, String projectTitle, String supervisorId, Frame f){
        String query = "INSERT INTO proposedProjectsToSupervisor (title, studentId, supervisorId, filePath, approvalStatus) VALUES (?, ?, ?, ?, 'Pending')";
        try {
            PreparedStatement ps = Conn.conn.prepareStatement(query);
            ps.setString(1, projectTitle); // Replace with actual project ID
            ps.setString(2, proposerId); // Replace with actual student ID
            ps.setString(3, supervisorId);
            ps.setString(4, filePath);

            String duplicateProjects = "SELECT * FROM ApprovedProjects WHERE title = ?";
            PreparedStatement p0 = Conn.conn.prepareStatement(duplicateProjects);
            p0.setString(1, projectTitle);
            ResultSet r0 = p0.executeQuery();

            if(r0.next()){
                JOptionPane.showMessageDialog(frame,
                        "Project with such title already exists",
                        "Duplicate Project",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                String checkForPendingReqs = "SELECT * FROM proposedProjectsToSupervisor WHERE studentId = ? AND approvalStatus = 'Pending'";
                PreparedStatement p = Conn.conn.prepareStatement(checkForPendingReqs);
                p.setString(1, proposerId);
                ResultSet r = p.executeQuery();

                if(r.next()){
                    JOptionPane.showMessageDialog(frame,
                            "Proposal is already sent and is yet pending",
                            "Pending request",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int rowsEffected = ps.executeUpdate();
                    if(rowsEffected>0){
                        JOptionPane.showMessageDialog(frame,
                                "Proposal sent to supervisor",
                                "Sent Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        filePath = "";
                        f.setVisible(false);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame,
                    "An error occurred while sending your proposal:\n"+e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
