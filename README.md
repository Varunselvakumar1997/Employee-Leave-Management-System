Employee Leave Management System
A Spring Boot application for managing employee leave requests with PostgreSQL as the backend database.  
The system supports leave tracking, approval workflow, and leave balance management — including privileged and sick leaves — with configurable rules.

## Run the Application using Docker Compose

### **1 Prerequisites**
Docker & Docker Compose installed
JAR file built at build/libs/employee-leave-management-system-0.0.1-SNAPSHOT.jar

Run the Application (Using Docker Compose) :

### **Step 1 Clone the repository**

### **Step 2 Start the Containers**
Build and start both **PostgreSQL** and the **Spring Boot app**:

docker-compose up --build

### **Step 3 Verify the Containers**
Check running containers:
docker ps

### **To stop and remove containers**
docker-compose down


## 📬 Example API Requests

### **1. Save Employee Details**
Method : POST
curl --location 'http://localhost:8080/api/employee/saveEmployeeDetails' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Varun",
"email": "varunsv@gmail.com",
"joiningDate": "2024-05-18",
"leaveBalance": 30.0
}
'

### **2. Apply for Leave**
Method : POST
curl --location 'http://localhost:8080/api/leaveRequest/submitLeaveRequest' \
--header 'Content-Type: application/json' \
--data '{
"employeeId": 1,
"startDate": "2025-11-08",
"endDate": "2025-11-10",
"leaveType": "PRIVILEGED"
}
'

### **3. Approve or Reject Leave**
Method : POST
curl --location 'http://localhost:8080/api/leaveRequest/approveOrRejectLeaveRequest' \
--header 'Content-Type: application/json' \
--data '{
"leaveRequestId": 1,
"leaveStatus": "APPROVED"
}
'

### **4. Get Leave Balance**
Method : GET
curl --location 'http://localhost:8080/api/employee/leaveBalance/1' \
--data ''


### Business Logic Summary :

The Employee Leave Management System implements a set of business rules to ensure accurate and fair handling of employee leave requests and balances.
Below is a detailed overview of each core logic implemented in the application:

| **Business Logic**                         | **Description**                                                                                                                                                                                                                                                               |
|--------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Leave Balance Cap**                      | Each employee can maintain a maximum of 30 days of privileged leave balance. When new or updated records exceed this limit, the system automatically caps the value at 30 days before saving to the database. This rule enforces consistency and prevents data anomalies.     |
| **Privileged Leave Validation**            | Employees can only request privileged leave (PL) up to their available leave balance. If the requested days exceed the remaining balance, the system rejects the request with a descriptive error message. This ensures that no employee can take more privileged leave than they have accrued. |
| **Sick Leave Handling**                    | Sick leave (SL) has no balance limit and does not deduct from the privileged leave balance. However, all sick leave requests are recorded in the system for auditing and attendance tracking purposes. This helps maintain compliance while allowing flexibility for medical absences. |
| **Leave Accrual (1.75 Days per Month)**    | Employees automatically accrue 1.75 privileged leave days per full month worked from their joining date. For partial months, the accrual is pro-rated based on the number of days worked. This ensures employees earn leave fairly according to their service duration.       |
| **Duplicate Employee Check (Email-Based)** | Employees are uniquely identified using their email address. When a save request is processed: If an employee with the same email already exists, their record is updated with the latest data.Otherwise, a new record is created. This prevents duplicate employee entries and ensures data accuracy. |
| **Automatic Record Update**                | Employee and leave operations are wrapped in transactional methods. This guarantees that if any part of the process fails (e.g., validation or database save),no partial data is committed — maintaining data integrity across the system.                                    |
| **Leave Approval Workflow**                | Only PENDING leave requests can be approved or rejected. Once processed, their status becomes immutable, ensuring a clear audit trail. When a privileged leave request is approved, the system deducts the approved days from the employee’s balance automatically.           |



### Custom SQL / JPQL Queries :

The application uses Spring Data JPA to interact with the PostgreSQL database.
Below are the key repository methods and their specific purposes in the Employee Leave Management System:

| **Query / Method**                                     | **Purpose**                                                                                                                       |
| ------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------- |
| `findByEmail(String email)`                            | Checks if an employee already exists using their email address before creating a new record. Prevents duplicate employee entries. |
| `findById(Long id)`                                    | Retrieves an employee or leave request by its unique identifier (primary key). Used for record lookups in both employee and leave modules. |
| `findByEmployeeId(Long employeeId)` *(if implemented)* | Fetches all leave requests submitted by a specific employee. Useful for displaying an employee’s complete leave history.          |
| Auto-generated `save()` methods                        | Persists or updates Employee and LeaveRequest entities. Automatically handles insert and update operations based on entity state. |




