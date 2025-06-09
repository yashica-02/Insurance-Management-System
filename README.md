# Yashica Sharma - Final Project README file

# Final Project: Insurance Management System

## Project Summary

This project is a secure, full-stack **Insurance Management System** built using **Jakarta EE 10** technologies:  
- **JSF** for the front-end,  
- **EJB** for business logic, and  
- **JPA** for persistence.  

The application simulates real-world operations of an insurance company, supporting three user roles with role-based access:
- **Admin** can manage both agents and customers.
- **Agent** can manage customers and their insurance policies.
- **Customer** can view their own insurance policies.

Each role has its own dashboard and functions. The application includes:
- Server-side validation and clean UI feedback.
- Bootstrap-based responsive design.
- Navigation controlled through role-specific views.
- Password hashing for security (PBKDF2).

## Requirements Fulfillment

---

### 1. Use of JSF Capabilities
- Layout structured with `template.xhtml`.
- Pages organized with reusable Facelets and scoped controllers.

---

### 2. MVC Architecture Separation
- Managed Beans (`@Named`, `@RequestScoped`) handle logic.
- XHTML files use EL expressions to bind to beans.
- Stateless EJBs perform transactional DB operations.
- Clear modular design separates data, logic, and UI layers.

---

### 3. Admin Account Creation
- Admin can add **Agent** and **Customer** accounts.
- Forms are built with validation and Bootstrap.

---

### 4. Entity Search, Filtering, and Selection
- Admin and Agent views use tables to list users.
- Policies linked to customers are browsable under Agent view.
- Filters/searches for user-friendly browsing.

---

### 5. Display Using Tables and Forms
- Insurance Policies shown in tabular form.
- Create/edit forms for Agents, Customers, and Policies.
- Bootstrap used for clarity and consistency.

---

### 6. Role-Based CRUD
- Admin: Add/Edit/Delete Agents and Customers.
- Agent: Add Customers and Add/Edit/Delete Insurance Policies.
- Customer: View policies only.

---

### 7. Navigation and Usability
- Dynamic menus for each role.
- Session-based login redirection.
- Error messages and feedback via `FacesMessage`.

---

### 8. Server-Side Validation
- Uses `@NotBlank`, `@Email`, and more in entities.
- Validations appear on the form with `<h:messages>`.

---

### 9. Bootstrap Styling
- Bootstrap used for navbars, tables, forms, and buttons.
- Fully responsive interface across views.

---

### 10. Sample Data
- `DatabaseInitializer.java` seeds roles and test accounts:
  - `admin / admin`
  - `agent1 / agent1`
  - `cust1 / cust1`

---

### 11. Logging and Error Handling
- Logs added to service and controller layers.
- Errors logged via Payara and shown via UI messages.
- `error.xhtml` shows user-friendly fallbacks.

## Extra Credit

- Password hashing with **PBKDF2**.
- Bootstrap 5 responsive interface.
- Clean URL navigation and scoped session control.

## Design

- **Model**: Entities for `Agent`, `Customer`, `InsurancePolicy`, `User`, and `Group`.
- **Service Layer**: Stateless EJBs (`CustomerService`, `PolicyService`, etc.)
- **Web Layer**: JSF beans (e.g., `LoginController`, `AgentWelcomeController`).
- **Security**: Implemented with Jakarta Security, using custom groups and roles.

### Navigation Flow
- Login ➝ Role-based homepage ➝ Entity views (Create/View/Edit)
- Admin ➝ Create Agent/Customer
- Agent ➝ Create Customers ➝ Create Policies for them
- Customer ➝ View assigned insurance policies

---

## Requirements (Installation, Compile, Runtime, Database, etc.)

### Tools & Frameworks
- Java 17+
- Jakarta EE 10
- Payara Server 6.2023
- MySQL 8+
- Maven
- Bootstrap (via CDN)

### Setup Instructions

1. Install Payara Server and MySQL.
2. Create a MySQL database (e.g., `insurance_db`).
3. Configure JDBC in Payara Admin Console:
   - JDBC Resource: `jdbc/itmd4515DS`
   - Username/Password matches your DB
4. Build the app:
   ```
   mvn clean package
   ```
5. Deploy to Payara (via NetBeans or `autodeploy` folder).
6. Open in browser:
   ```
   http://localhost:8080/ysharma7-fp/login.xhtml
   ```

---

## Screen Captures

- Login page
![image](https://github.com/user-attachments/assets/3f8c76fe-ceb7-4797-b9c0-8542e192829e)

### ADMIN LOGIN
- Welcome page
![image](https://github.com/user-attachments/assets/96a38d25-2ed8-4ae0-b1e1-528004be089c)
- Admin Customer Controls
![image](https://github.com/user-attachments/assets/860124b3-e0fe-4ad1-9ac2-63791cf49eea)
- Add Customer
![image](https://github.com/user-attachments/assets/194c3947-97b9-43ba-be7d-5f0ce0ab82b5)
- Edit Customer
![image](https://github.com/user-attachments/assets/50658dc9-c9e1-483b-9040-0334cfbfd39c)
- Delete Customer
![image](https://github.com/user-attachments/assets/89a2a608-0421-4e24-afef-c3bc3b0644be)

### AGENT LOGIN  
- Welcome page
![image](https://github.com/user-attachments/assets/492ea389-50d5-4166-8470-ffffe7907472)
- CRUD Operations on policies
![image](https://github.com/user-attachments/assets/e05dcffe-3f27-4d17-bfb1-79c939754423)
![image](https://github.com/user-attachments/assets/48c39bbe-d302-4438-94a4-dd7e5a44e7ed)
![image](https://github.com/user-attachments/assets/28b089ae-fb07-42ca-a9ca-e10d1703a85e)
![image](https://github.com/user-attachments/assets/647a27b7-5150-458b-89b8-8018a99b096b)
- Create Policy for new customer -> Lands on Create customer then moves to create policy
![image](https://github.com/user-attachments/assets/1dab8450-117b-4f3a-b753-c8961c8930fb)

- Customer viewing their policies
![image](https://github.com/user-attachments/assets/15077bc4-4907-4297-ba5c-c2c4e8b29a99)

- Error handling pages
![image](https://github.com/user-attachments/assets/47125b35-5f58-4089-a6b4-fa9d31255daa)
- Denied Access Page
![image](https://github.com/user-attachments/assets/a2ab7a83-e28b-44f0-bd65-c911539cf91a)


---

## Expected Results / Known Issues

### Expected Behavior
- Admin manages all data.
- Agent manages customers + policies.
- Customer views their own policies.
- Role-based access enforced.

### Known Issues
- Date input rendering may differ across browsers.
- Some records may not immediately reflect without page refresh.

---

### Default Users
- Admin: `admin / admin`
- Agent: `agent1 / agent1`
- Customer: `cust1 / cust1`

### Suggested Test Script
1. Login as admin.
2. Create a new agent.
3. Login as agent → Create a new customer.
4. Create a policy for that customer.
5. Login as that customer → View the policy.
6. Attempt restricted actions to test role isolation.

---

## Development Insights

This project deepened my experience with Jakarta EE’s ecosystem:
- Learned how to structure JSF + JPA + EJB projects effectively.
- Implemented role-based security using groups.
- Used scoped beans to manage user sessions and views.
- Gained appreciation for UI/UX with Bootstrap.

What challenged me most was debugging deployment issues in Payara and syncing JPA relations. I plan to explore asynchronous jobs and external API integration in the future. Despite hurdles, this was a very rewarding experience.

## References

- [Jakarta EE](https://jakarta.ee/specifications/)
- [Payara Server Docs](https://docs.payara.fish/)
- [Bootstrap Docs](https://getbootstrap.com/)
- Instructor lab templates (for security, JPA, and JSF)
