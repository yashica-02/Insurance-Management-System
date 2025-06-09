Yashica Sharma Lab 3 README

Input Validation Pass

This page provides a form for creating a new film in the system. Users can fill out the details of a film, such as the Film ID, Title, Release Year, Language ID, Rental Duration, Rental Rate, Length, Replacement Cost, and Rating. The form submits this data to the server to create a new film entry.

Form (Before Submitting)
![image](https://github.com/user-attachments/assets/5722731d-4883-4d75-8b3c-b18b59160e13)

Confirmation View (after)
![image](https://github.com/user-attachments/assets/36f59773-34ec-4e4f-ba9d-806b2a7eaccd)

Data added in the DB
![image](https://github.com/user-attachments/assets/74c9fced-5b1b-462b-a461-20f698846774)


Input Validation Fail

If the user leaves any field empty it would show up error, or if they give wrong values, in this case, value lower than 0
If there are any validation errors, they are displayed below the form in a table format. Each row shows the field that has an error and the associated error message, helping the user correct any issues with their input.

Form with bad input (before)
![image](https://github.com/user-attachments/assets/39d68e78-793a-4458-8d48-a1b914840f97)

Form with error messages (after)
![image](https://github.com/user-attachments/assets/6d949ea3-28ed-4678-b49d-3f6919f90917)


Summary paragraph
1. Your understanding of the difference between the forward and redirect operations.
A forward sends the request to another resource on the server without changing the URL in the browser, while a redirect sends the client a response to request a different URL, updating the browser's address bar.
2. How would you be validating user submissions without the Bean Validation API standard?
I probaby would manually check form fields in the servlet or controller, using conditions to ensure data meets expected patterns or constraints.
3. How do you think this approach would scale to a real application with 100's of entities?
This approach may struggle with performance as the app grows because manual validation and data handling can become cumbersome, requiring additional frameworks
4. Why didn't we need to include any additional dependencies (i.e. Bean Validation, JDBC) in this project?
We didn't need extra dependencies because the application is straightforward and leverages built-in Java EE (Jakarta EE) features, such as JSP and servlets, which handle basic validation and database connection pooling by default.
