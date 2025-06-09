Yashica Sharma Lab8 README

I. Screenshots and the flow of the application:
 LOGIN WINDOW:
    ![image](https://github.com/user-attachments/assets/9e5eaa24-911a-4e9c-88a5-4771f2807c52)

after logging in the user is landed on the welcome page showing the list of the pages which would be available to them according to their user roles.
   redirects to the Authenticated Landing Page(which is the same for every user) when given correct login info
    ![image](https://github.com/user-attachments/assets/f8b47704-e78d-4295-90e5-d35d79c26e19)

   if given wrong login:
    ![image](https://github.com/user-attachments/assets/3826228e-9f42-49e3-a4ad-36ec37857274)

   when clicked "Logout":
    ![image](https://github.com/user-attachments/assets/b0f618ae-5e9f-4e71-976b-d47d0c5b0592)


   ADMIN LOGIN:
    when clicked "Admin Scetion":
    ![image](https://github.com/user-attachments/assets/4477e408-5930-4770-882e-289894d4b9fb)

   when clicked "Agent Section":
    ![image](https://github.com/user-attachments/assets/4392dfb8-f0d4-4c8c-9564-b39105c2994b)

   when clicked "Customer Section":
    ![image](https://github.com/user-attachments/assets/74983b67-1270-4bd8-948a-35bd7e503b04)


   AGENT LOGIN: 
    when clicked "Agent Section":
    ![image](https://github.com/user-attachments/assets/e532a90c-58ad-42b7-8afe-3a945e6f50ba)
    Agent can add the policies after logging into the system
    
   when clicked "Admin Scetion":
    ![image](https://github.com/user-attachments/assets/a2445fce-7434-4a31-bd2d-6ccd33825c07)

   when clicked "Customer Section":
    ![image](https://github.com/user-attachments/assets/2a32d1aa-799b-4258-9d16-a640499f6ba9)


   CUSTOMER LOGIN:
    when clicked "Customer Section":
    ![image](https://github.com/user-attachments/assets/a4960277-4082-4278-83ba-9c740556f0d8)

   when clicked "Admin Scetion":
    ![image](https://github.com/user-attachments/assets/f946f093-f23b-47e3-87c0-c00720b5241e)

   when clicked "Agent Section":
    ![image](https://github.com/user-attachments/assets/80b05e83-9efe-4867-ba48-5ce2aced7892)


   AGENT2 LOGIN (This user is both agent and customer):
    when clicked "Agent Section":
    ![image](https://github.com/user-attachments/assets/0e33b2f5-bab2-4694-a6a4-48712c7ded08)
    
   when clicked "Customer Section":
    ![image](https://github.com/user-attachments/assets/63783b53-a065-467c-a301-6f39d040685e)
    
   when clicked "Admin Scetion":
    ![image](https://github.com/user-attachments/assets/7cba3d6d-816a-44f4-a806-bddfedb4e12c)

   

II. The username and password combinations
           
    a. Admin Group:
        ("admin", "admin")
    b. Customer Group:
        ("cust1", "cust1")
        ("cust2", "cust2")
        ("agent2", "agent2") //employee AND customer
    c. Employee Group:
        ("agent1", "agent1")
        ("agent2", "agent2")

III. Difficulties Faced:
    I had difficulty in the user roles, like when I logged in with a user and then tried to go back and log in as a different user it would not follow the administrative rules of the user group but it was solved after deleting the databse before every deployment and creating a logout functionality.
