CREATE DATABASE IF NOT EXISTS project;
USE project;

CREATE TABLE Committee(
	committeeNumber INT PRIMARY KEY,
	dateOfPresentation DATE
);


CREATE TABLE Supervisor(
	facultyId VARCHAR(10) PRIMARY KEY,
	name VARCHAR(100),
	password VARCHAR(100),
	projectsSupervised INT,
	committeeNumber INT,
	FOREIGN KEY (committeeNumber) REFERENCES Committee(committeeNumber)
);

CREATE TABLE Projects(
	name VARCHAR(100) PRIMARY KEY,
	supervisorId VARCHAR(100) UNIQUE,
	FOREIGN KEY (supervisorId) REFERENCES Supervisor(facultyId),
	committeeSupervising INT,
	FOREIGN KEY (committeeSupervising) REFERENCES Committee(committeeNumber),
	ProjectStatus ENUM("Completed", "Not taken", "Already taken"),
	noOfStdsReq INT
);

CREATE TABLE Student(
	name VARCHAR(100),
	regNo VARCHAR(12) PRIMARY KEY,
	projectName VARCHAR(100),
	FOREIGN KEY (projectName) REFERENCES Projects(name),
	project_status VARCHAR(20),
	password VARCHAR(100),
	dateOfPresentation DATE,
	supervisorId VARCHAR(10),
	FOREIGN KEY (supervisorId) REFERENCES Supervisor(facultyId),
	isGrouped BOOLEAN,
	groupNumber INT,
	committeeSupervising INT,
	FOREIGN KEY (committeeSupervising) REFERENCES Committee(committeeNumber)
);

CREATE TABLE Faculty(
	facultyId VARCHAR(10) PRIMARY KEY,
	name VARCHAR(100),
	password VARCHAR(100),
	committeeNumber INT,
	FOREIGN KEY (committeeNumber) REFERENCES Committee(committeeNumber)
);

CREATE TABLE Coordinator(
	name VARCHAR(100) PRIMARY KEY,
	password VARCHAR(100)
);

CREATE TABLE proposedProjectsToSupervisor(
	title VARCHAR(100),
	filePath VARCHAR(500),
	studentId VARCHAR(20),
	foreign key (studentId) REFERENCES Student(regNo),
	supervisorId VARCHAR(20),
	foreign key (supervisorId) REFERENCES Supervisor(facultyId),
	approvalStatus ENUM("Pending", "Approved", "Rejected") DEFAULT "Pending",
	noOfStdsReq INT
);

CREATE TABLE proposedProjectsToCoordinator(
	title VARCHAR(100),
	filePath VARCHAR(500),
	studentId VARCHAR(20),
	foreign key (studentId) REFERENCES Student(regNo),
	supervisorId VARCHAR(20),
	foreign key (supervisorId) REFERENCES Supervisor(facultyId),
	approvalStatus ENUM("Pending", "Approved", "Rejected") DEFAULT "Pending",
	noOfStdsReq INT
);


CREATE TABLE ApprovedProjects(
	title VARCHAR(100) PRIMARY KEY,
	supervisorId VARCHAR(20) UNIQUE,
	FOREIGN KEY (supervisorId) REFERENCES Supervisor(facultyId),
	studentIdWhoProposed VARCHAR(20),
	FOREIGN KEY (studentIdWhoProposed) REFERENCES Student(regNo),
	noOfStdsReq INT,
	committeeSupervising INT,
	FOREIGN KEY (committeeSupervising) REFERENCES Committee(committeeNumber),
	projectStatus ENUM("Completed", "Not taken", "Already taken")
);

INSERT INTO Coordinator(name, password) VALUES ("Muhammad", "admin123"); 
-- You can insert your own login data for your project here for coordinator section

CREATE TABLE RequestLogs (
	requestId INT AUTO_INCREMENT PRIMARY KEY, -- Unique ID for each request
	regNo VARCHAR(12),                        -- Student who made the request
	FOREIGN KEY (regNo) REFERENCES Student(regNo),
	projectName VARCHAR(100),                 -- Project requested
	FOREIGN KEY (projectName) REFERENCES Projects(name),
	requestDate DATETIME DEFAULT CURRENT_TIMESTAMP,    -- Date of request
	requestStatus ENUM('Pending', 'Accepted', 'Rejected') DEFAULT 'Pending', -- Status of request
	supervisorId VARCHAR(10),                 -- Supervisor handling the request
	FOREIGN KEY (supervisorId) REFERENCES Supervisor(facultyId)
);

DELIMITER $$
CREATE TRIGGER set_request_date
BEFORE INSERT ON RequestLogs
FOR EACH ROW
BEGIN
	IF NEW.requestDate IS NULL THEN
		SET NEW.requestDate = NOW();
	END IF;
END$$

DELIMITER ;

-- DROP TRIGGER after_request_accepted;

 -- Trigger for request from student to supervisor 
DELIMITER $$
CREATE TRIGGER after_request_accepted 
AFTER UPDATE ON RequestLogs
FOR EACH ROW
BEGIN
	DECLARE studentGroupNumber INT;
    DECLARE newGroup INT;
    IF NEW.requestStatus = 'Accepted' THEN

        SET newGroup = (SELECT IFNULL(MAX(groupNumber), 0) + 1 FROM Student);
        -- Get the group number of the student whose request is accepted
		SELECT IFNULL(groupNumber, newGroup) INTO studentGroupNumber
        FROM Student
        WHERE regNo = NEW.regNo;

		-- Setting group number of student
		UPDATE Student
        SET groupNumber = studentGroupNumber
        WHERE regNo = NEW.regNo;
        
        -- Update all students in the same group to have the same projectName and supervisorId
        UPDATE Student
        SET projectName = NEW.projectName,
            supervisorId = NEW.supervisorId,
            project_status = "Working upon",
            committeeSupervising = (SELECT committeeNumber FROM Supervisor WHERE facultyId = NEW.supervisorId)
        WHERE groupNumber = studentGroupNumber;
	
        -- Mark the project as taken in the Projects table
        UPDATE Projects
        SET ProjectStatus = 'Already taken'
        WHERE name = NEW.projectName;

        -- Update the status in ApprovedProjects as well
        UPDATE ApprovedProjects
        SET projectStatus = 'Already taken'
        WHERE title = NEW.projectName;
    END IF;
END$$

DELIMITER ;

-- Request from student to student
CREATE TABLE groupRequests (
	requestId INT AUTO_INCREMENT PRIMARY KEY,
	requesterId VARCHAR(12), -- Student who sent the request
	requestedId VARCHAR(12), -- Student to whom the request is sent
	requestDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	status ENUM('Pending', 'Accepted', 'Rejected') DEFAULT 'Pending',
	FOREIGN KEY (requesterId) REFERENCES Student(regNo),
	FOREIGN KEY (requestedId) REFERENCES Student(regNo)
);

-- DROP TRIGGER onGroupRequestAccept;

-- Trigger for group requests sent by student to students
DELIMITER $$
CREATE TRIGGER onGroupRequestAccept
AFTER UPDATE ON groupRequests
FOR EACH ROW
BEGIN
    DECLARE newGroup INT;
    DECLARE requesterGroupNumber INT;
    DECLARE requestedGroupNumber INT;
    
    DECLARE requesterProjectName VARCHAR(100);
    DECLARE requestedProjectName VARCHAR(100);
    DECLARE finalProjectName VARCHAR(100);
    
    DECLARE requesterCommittee INT;
    DECLARE requestedCommittee INT;
    DECLARE finalCommittee INT;
    
    DECLARE requesterSupervisorId VARCHAR(10);
    DECLARE requestedSupervisorId VARCHAR(10);
    DECLARE finalSupervisorId VARCHAR(10);
    
    DECLARE requesterProjectStatus VARCHAR(20);
    DECLARE requestedProjectStatus VARCHAR(20);
    DECLARE finalProjectStatus VARCHAR(20);

    IF NEW.status = 'Accepted' THEN
		SELECT groupNumber INTO requesterGroupNumber FROM Student WHERE regNo = NEW.requesterId;
		SELECT groupNumber INTO requestedGroupNumber FROM Student WHERE regNo = NEW.requestedId;
        
        -- Store the requester's details into variables
        SELECT projectName, committeeSupervising, supervisorId, project_status
        INTO requesterProjectName, requesterCommittee, requesterSupervisorId, requesterProjectStatus
        FROM Student 
        WHERE regNo = NEW.requesterId;
        
        -- Store the requested's details into variables
        SELECT projectName, committeeSupervising, supervisorId, project_status
        INTO requestedProjectName, requestedCommittee, requestedSupervisorId, requestedProjectStatus
        FROM Student 
        WHERE regNo = NEW.requestedId;
        
        -- Selecting groupNumber if either reuqester or requested student have one.
        -- Otherwise new group will be created
        IF requesterGroupNumber IS NULL 
        THEN
			IF requestedGroupNumber IS NULL THEN
				SET newGroup = (SELECT IFNULL(MAX(groupNumber), 0) + 1 FROM Student);
			ELSE
				SET newGroup = requestedGroupNumber;
			END IF;
		ELSE
			IF requestedGroupNumber IS NOT NULL THEN
				SET newGroup = (SELECT MIN(groupNumber) FROM Student WHERE regNo IN (NEW.requestedId, NEW.requesterId));
			ELSE
				SET newGroup = requesterGroupNumber;
			END IF;
		END IF;
        
        
        -- Selecting finalProjectName if either reuqester or requested student have one.
        -- Otherwise none will be created
        IF requesterProjectName IS NULL 
        THEN
			IF requestedProjectName IS NULL THEN
				SET finalProjectName = null;
			ELSE
				SET finalProjectName = requestedProjectName;
			END IF;
		ELSE
			SET finalProjectName = requesterProjectName;
		END IF;
		
        
        -- Selecting finalCommittee if either reuqester or requested student have one.
        -- Otherwise none will be created
        IF requesterCommittee IS NULL 
        THEN
			IF requestedCommittee IS NULL THEN
				SET finalCommittee = null;
			ELSE
				SET finalCommittee = requestedCommittee;
			END IF;
		ELSE
			SET finalCommittee = requesterCommittee;
		END IF; 
        
        
        -- Selecting finalSupervisorId if either reuqester or requested student have one.
        -- Otherwise none will be created
        IF requesterSupervisorId IS NULL 
        THEN
			IF requestedSupervisorId IS NULL THEN
				SET finalSupervisorId = null;
			ELSE
				SET finalSupervisorId = requestedSupervisorId;
			END IF;
		ELSE
			SET finalSupervisorId = requesterSupervisorId;
		END IF;
		

        -- Determine the final project status for the group
        IF requesterProjectStatus IN ('Completed', 'Working upon') OR requestedProjectStatus IN ('Completed', 'Working upon') THEN
            SET finalProjectStatus = 
                CASE 
                    WHEN requesterProjectStatus = 'Completed' OR requestedProjectStatus = 'Completed' THEN 'Completed'
                    ELSE 'Working upon'
                END;
        ELSE
            SET finalProjectStatus = 'Not selected';
        END IF;

        -- Update both students to be in the same group with the consistent project status
        UPDATE Student 
        SET groupNumber = newGroup,
            projectName = finalProjectName,
            committeeSupervising = finalCommittee,
            supervisorId = finalSupervisorId,
            isGrouped = TRUE,
            project_status = finalProjectStatus
        WHERE regNo IN (NEW.requesterId, NEW.requestedId)
        OR groupNumber IN (requesterGroupNumber, requestedGroupNumber);
    END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER add_to_project_when_approved
AFTER INSERT ON ApprovedProjects
FOR EACH ROW
BEGIN
	INSERT INTO Projects(name, supervisorId, committeeSupervising, ProjectStatus, noOfStdsReq)
    VALUES
    (NEW.title, NEW.supervisorId, NEW.committeeSupervising, NEW.projectStatus, NEW.noOfStdsReq);
END;
$$ DELIMITER ;
    
DELIMITER $$
CREATE TRIGGER before_insert_approvedProjects
BEFORE INSERT ON ApprovedProjects
FOR EACH ROW
BEGIN
    SET NEW.committeeSupervising = (SELECT committeeNumber FROM Supervisor WHERE facultyId = NEW.supervisorId);
END;
$$ DELIMITER ;

-- DROP TRIGGER afterProposalAcceptByCoordinator;

DELIMITER $$
CREATE TRIGGER afterProposalAcceptByCoordinator
AFTER UPDATE ON proposedProjectsToCoordinator
FOR EACH ROW
BEGIN
	DECLARE newGroup INT;
    DECLARE studentGroupNumber INT;
	
    IF NEW.approvalStatus = "Approved" THEN
    
    -- Insert the project into approvedProjects
    INSERT INTO ApprovedProjects(title, supervisorId, studentIdWhoProposed, noOfStdsReq, projectStatus)
    VALUES
    (NEW.title, NEW.supervisorId, NEW.studentId, NEW.noOfStdsReq, "Already taken");
    
    -- Get groupNumber of student whose project proposal is approved
    SELECT groupNumber INTO studentGroupNumber FROM Student WHERE regNo = NEW.studentId;
    
    -- Assign group number to student if he don't have one.
		IF studentGroupNumber IS NULL THEN
			SET newGroup = (SELECT IFNULL(MAX(groupNumber), 0) + 1 FROM Student);
		ELSE
			SET newGroup = studentGroupNumber;
		END IF;
        
        -- Update student's groupNumber
        -- 1. If he had one, nothig will change and the groupNumber will remain the same
        -- 2. If he didn't had one, that means he haven't grouped with other students. Eventually, no need to change the entire group's data
        UPDATE Student
        SET groupNumber = newGroup
        WHERE regNo = NEW.studentId;
        
        -- Update projectName, supervisorId, project_status
        UPDATE Student
        SET projectName = NEW.title,
        project_status = 'Working upon',
        supervisorId = NEW.supervisorId
        WHERE groupNumber = newGroup;
	END IF;
END
$$ DELIMITER ;

-- DROP TRIGGER after_supervisor_insert_to_committee;

DELIMITER $$
CREATE TRIGGER after_supervisor_insert_to_committee
AFTER UPDATE ON Supervisor
FOR EACH ROW
BEGIN
	IF NEW.committeeNumber IS NOT NULL THEN
		UPDATE Student SET committeeSupervising = NEW.committeeNumber WHERE supervisorId = NEW.facultyId;
        UPDATE ApprovedProjects SET committeeSupervising = NEW.committeeNumber WHERE supervisorId = NEW.facultyId;
        UPDATE Projects SET committeeSupervising = NEW.committeeNumber WHERE supervisorId = NEW.facultyId;
	END IF;
END
$$ DELIMITER ;

-- DROP TRIGGER before_insert_approvedProjects;

-- SELECT * FROM Coordinator;
-- SELECT * FROM Faculty;
-- SELECT * FROM Supervisor;
-- SELECT * FROM Projects;
-- SELECT * FROM Committee;
-- SELECT * FROM ApprovedProjects ORDER BY supervisorId;
-- SELECT * FROM Student ORDER BY groupNumber ASC;
-- SELECT * FROM proposedProjectsToSupervisor;
-- SELECT * FROM RequestLogs;
-- SELECT * FROM grouprequests;
-- SELECT * FROM proposedProjectsToCoordinator;