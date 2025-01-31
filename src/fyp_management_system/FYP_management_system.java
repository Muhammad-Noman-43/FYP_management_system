/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fyp_management_system;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Muhammad Noman
 */
public class FYP_management_system {
//    Scanner in = new Scanner(System.in);
    static ArrayList<Faculty> registeredFaculties = new ArrayList<>();
    static ArrayList<Student> registeredStudents = new ArrayList<>();
    static ArrayList<Committee> registeredCommittees = new ArrayList<>();
    static ArrayList<Supervisor> registeredSupervisors = new ArrayList<>();
    static ArrayList<Project> registeredProjects = new ArrayList<>();
//    static Coordinator c = new Coordinator("Muhammad", "m123");



//    int reg;
    
//    void registrationForms(){
//        while(true){
//            System.out.println("Here are the options. Press:");
//            System.out.println("1 for registering student\n2 for regstering faculty\n3 for registering supervisor");
//            System.out.println("4 for registering any project\n5 for making group with other students");
//            System.out.println("6 for composing committee\n0 for exiting the program");
//            reg = in.nextInt();
//            in.nextLine();
//            switch(reg){
//            case 1:
//                System.out.println("Now, you are a Student");
//                System.out.println("Enter your registration number : ");
//                String stregNo = in.nextLine();
//                if(searchStudentIdx(stregNo)!=-1){
//                    System.out.println("Student with this Registration number already exists.");
//                    break;
//                }
//                System.out.println("Enter your name : ");
//                String stname = in.nextLine();
//                System.out.println("Enter your projects status (Completed, selected, not selected)");
//                String stProject_status = in.nextLine();
//                System.out.println("Enter your password : ");
//                String stpassword = in.nextLine();
//                registeredStudents.add(new Student(stname, stregNo, stProject_status, stpassword));
//                System.out.println("Students data added successfully");
//                break;
//            case 2:
//                System.out.println("Now, you are a Faculty member");
//                System.out.println("Enter your faculty ID : ");
//                String fId = in.nextLine();
//                if(searchFacultyIdx(fId)!=-1){
//                    System.out.println("Faculty with this ID is already registered.");
//                    break;
//                }
//                System.out.println("Enter your name : ");
//                String fname = in.nextLine();
//                System.out.println("Enter your password : ");
//                String fpassword = in.nextLine();
//                registeredFaculties.add(new Faculty(fname, fId, fpassword));
//                System.out.println("Faculty's data added successfully");
//                break;
//            case 3:
//                System.out.println("Now, you are a Supervisor");
//                System.out.println("Enter your registration number : ");
//                String spId = in.nextLine();
//                if(searchSupervisorIdx(spId)!=-1){
//                    System.out.println("Supervisor with this ID already exists.");
//                    break;
//                }
//                System.out.println("Enter your name : ");
//                String spname = in.nextLine();
//                System.out.println("Enter your password : ");
//                String spPassword = in.nextLine();
//                System.out.println("How many projects have you supervised till now : ");
//                int spProjectsSupervised = in.nextInt();
//                registeredSupervisors.add(new Supervisor(spname, spId, spPassword, spProjectsSupervised));
//                System.out.println("Supervisor's data added successfully");
//                break;
//            case 4:
//                System.out.println("Project insertion by Coordinator");
//                System.out.println("Enter co-ordinator username : ");
//                String cusername = in.nextLine();
//                System.out.println("Enter co-ordinator password : ");
//                String cpassword = in.nextLine();
//                if(cusername.equalsIgnoreCase(c.username) && cpassword.equals(c.password)){
//                    System.out.println("👋 Welcome, "+cusername);
//                    System.out.println("Enter the project title : ");
//                    String projectTitle = in.nextLine();
//                    int temp = searchProjectIdx(projectTitle);
//                    if(temp!=-1){
//                        System.out.println("This project is already registered. 🙆‍♂️🙆‍♂️");
//                    } else {
//                        System.out.println("Enter the id of the supervisor for this project to be assigned : ");
//                        String supervisorId = in.nextLine();
//                        registeredProjects.add(new Project(projectTitle, registeredSupervisors.get(temp)));
//                    }
//                } else {
//                    System.out.println("Intruder 😱😱😱");
//                }
//                break;
//            case 5:
//                String projectTitleForGrouping=null;
//                if(registeredStudents.isEmpty()){
//                    System.out.println("There's no Student registered yet.");
//                } else if(registeredStudents.size()==1){
//                    System.out.println("There's no Student registered except you.");
//                } else if(registeredProjects.isEmpty()){
//                    System.out.println("There's no project registered to Conn upon");
//                } else {
//                    System.out.println("Now, you are a student and are selecting student for grouping");
//                    System.out.println("Enter your ID : ");
//                    String id = in.nextLine();
//                    int temp1 = reEnterStudentId(id);
//                    if(temp1 != -1){
//                        System.out.println("Enter the project title : ");
//                        projectTitleForGrouping = in.nextLine();
//                    } else {
//                        break;
//                    }
//                    int temp2 = reEnterProjectTitle(projectTitleForGrouping);
//                    if(temp2!=-1){
//                        System.out.println("Enter the number of students you want to group with (except you) : ");
//                        int noOfStudents = in.nextInt();
//                        in.nextLine();
//
//                        groupStudents(noOfStudents, projectTitleForGrouping);
//                        registeredProjects.get(temp2).addStudents(registeredStudents.get(temp1));
//                    }
//                }
//                break;
//            case 6:
//                System.out.println("You have entered coordinator's site.");
//                System.out.println("Enter username : ");
//                String username = in.nextLine();
//                System.out.println("Enter password : ");
//                String password = in.nextLine();
//                if(username.equalsIgnoreCase(c.username) && password.equals(c.password)){
//                    System.out.println("Welcome, "+c.username);
//                    System.out.println("You can compose committee now.");
//                    c.composeCommittee();
//                } else {
//                    System.out.println("Access Denied");
//                }
//                break;
//            case 0:
//                return;
//            default:
//                System.out.println("Please enter a number out of the provided ones.");
//            }
//        }
//    }
//
//    private int searchSupervisorIdx(String id){
//        int i=0;
//        for(;i<registeredSupervisors.size();i++){
//            if(registeredSupervisors.get(i).facultyID.equalsIgnoreCase(id)){
//                break;
//            }
//        }
//        return i;
//    }
//
//    private int searchFacultyIdx(String id){
//        int temp=-1;
//        for(int i=0;i<registeredSupervisors.size();i++){
//            if(registeredSupervisors.get(i).facultyID.equalsIgnoreCase(id)){
//                temp=i;
//                break;
//            }
//        }
//        return temp;
//    }
//
//    int searchStudentIdx(String id){
//        int temp=-1;
//        for(int j=0;j<registeredStudents.size();j++){
//            if(registeredStudents.get(j).regNo.equalsIgnoreCase(id)){
//                temp=j;
//                break;
//            }
//        }
//        return temp;
//    }
//
//    int searchProjectIdx(String title){
//        int temp=-1;
//        for(int k=0;k<registeredProjects.size();k++){
//            if(registeredProjects.get(k).title.equalsIgnoreCase(title)){
//                temp = k;
//                break;
//            }
//        }
//        return temp;
//    }
//
//    void groupStudents(int nos, String pt){
//        System.out.println("This is the list of Students you can group with.");
//        listStudentsUngrouped();
//        for(int i=0;i<nos;i++){
//            System.out.println("Enter the registration number of the students you want to group with : ");
//            String regNo = in.nextLine();
//            if(reEnterStudentId(regNo)==1)
//                registeredProjects.get(searchProjectIdx(pt)).addStudents(registeredStudents.get(searchStudentIdx(regNo)));
//        }
//    }
//
//    int reEnterProjectTitle(String projectTitleForGrouping){
//        int check=1;
//        while(searchProjectIdx(projectTitleForGrouping)==-1){
//            System.out.println("No such project registered yet. Please enter the correct title or register the project first.");
//            System.out.println("Would you like to enter the correct title of the project? (1 = Yes, 0 = No");
//            check = in.nextInt();
//            if(check==0){
//                System.out.println("No worries :) You can enter the title of the project again.");
//                System.out.println("Enter the project title : ");
//                projectTitleForGrouping = in.nextLine();
//            } else {
//                System.out.println("Please make sure project with this name is registered.");
//                break;
//            }
//        }
//        if(check==1){
//            return searchProjectIdx(projectTitleForGrouping);
//        } else {
//            return -1;
//        }
//    }
//
//    int reEnterStudentId(String id){
//        int check=1;
//        while(searchStudentIdx(id)==-1){
//            System.out.println("Well, we don't recognize student with this ID. It's either you have misspelled the Reg. no or such student is not registered yet");
//            System.out.println("Check again if you have have misspelled the registration number? (1 = Yes, 0 = No) ");
//            check = in.nextInt();
//            if(check==1){
//                System.out.println("Ok then. Enter the ID again.");
//                System.out.println("Enter the ID : ");
//                id = in.nextLine();
//            } else {
//                System.out.println("Please register student with this ID first");
//            }
//        }
//        if(check==1){
//            return searchStudentIdx(id);
//        } else {
//            return -1;
//        }
//    }
//
//    void listStudentsUngrouped(){
//        for (Student registeredStudent : registeredStudents) {
//            if (!registeredStudent.isGrouped)
//                registeredStudent.displayStudentDetails();
//        }
//    }
}
