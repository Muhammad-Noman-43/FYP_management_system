# Database Schema Sketch

## Tables and Relationships

### 1. **Committee**
- **Fields**:
    - `committeeNumber` (Primary Key)
    - `dateOfPresentation`
- **Relationships**:
    - Linked to `Supervisor`, `Projects`, `Student`, and `Faculty`.

### 2. **Supervisor**
- **Fields**:
    - `facultyId` (Primary Key)
    - `name`, `password`, `projectsSupervised`, `committeeNumber` (Foreign Key)
- **Relationships**:
    - Linked to `Committee`, `Projects`, `Student`, and `proposedProjectsToSupervisor`.

### 3. **Projects**
- **Fields**:
    - `name` (Primary Key)
    - `supervisorId` (Foreign Key), `committeeSupervising` (Foreign Key)
    - `ProjectStatus`, `noOfStdsReq`
- **Relationships**:
    - Linked to `Supervisor`, `Committee`, and `Student`.

### 4. **Student**
- **Fields**:
    - `regNo` (Primary Key)
    - `name`, `projectName` (Foreign Key), `project_status`, `password`
    - `dateOfPresentation`, `supervisorId` (Foreign Key), `isGrouped`, `groupNumber`, `committeeSupervising` (Foreign Key)
- **Relationships**:
    - Linked to `Projects`, `Supervisor`, `Committee`, and `groupRequests`.

### 5. **Faculty**
- **Fields**:
    - `facultyId` (Primary Key)
    - `name`, `password`, `committeeNumber` (Foreign Key)
- **Relationships**:
    - Linked to `Committee`.

### 6. **Coordinator**
- **Fields**:
    - `name` (Primary Key), `password`
- **Relationships**:
    - No direct relationships with other tables.

### 7. **proposedProjectsToSupervisor**
- **Fields**:
    - `title`, `filePath`, `studentId` (Foreign Key), `supervisorId` (Foreign Key)
    - `approvalStatus`, `noOfStdsReq`
- **Relationships**:
    - Linked to `Student` and `Supervisor`.

### 8. **proposedProjectsToCoordinator**
- **Fields**:
    - `title`, `filePath`, `studentId` (Foreign Key), `supervisorId` (Foreign Key)
    - `approvalStatus`, `noOfStdsReq`
- **Relationships**:
    - Linked to `Student` and `Supervisor`.

### 9. **ApprovedProjects**
- **Fields**:
    - `title` (Primary Key), `supervisorId` (Foreign Key), `studentIdWhoProposed` (Foreign Key)
    - `noOfStdsReq`, `committeeSupervising` (Foreign Key), `projectStatus`
- **Relationships**:
    - Linked to `Supervisor`, `Student`, and `Committee`.

### 10. **RequestLogs**
- **Fields**:
    - `requestId` (Primary Key), `regNo` (Foreign Key), `projectName` (Foreign Key)
    - `requestDate`, `requestStatus`, `supervisorId` (Foreign Key)
- **Relationships**:
    - Linked to `Student`, `Projects`, and `Supervisor`.

### 11. **groupRequests**
- **Fields**:
    - `requestId` (Primary Key), `requesterId` (Foreign Key), `requestedId` (Foreign Key)
    - `requestDate`, `status`
- **Relationships**:
    - Linked to `Student`.

---

## Key Relationships
- **Committee** is central to managing `Supervisor`, `Faculty`, `Projects`, and `Student`.
- **Supervisor** manages `Projects` and interacts with `Student` through `proposedProjectsToSupervisor` and `RequestLogs`.
- **Student** interacts with `Projects`, `Supervisor`, and other students through `groupRequests`.
- **Coordinator** is a standalone entity with no direct relationships but oversees the system.

---

## Notes
- For a detailed understanding of the database, refer to the `.sql` file or explore the database directly.
- Triggers and procedures are used to automate tasks like group formation, project approval, and status updates.