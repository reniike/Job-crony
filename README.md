# Job Crony Application

## Overview

Job Crony is a job board application built using Java Spring Boot, Spring Security, and MySQL. The application serves as a platform for admin, employers, job seekers, and event attendees, facilitating the job posting and application process, as well as event management.

## Features

- **User Roles:**
    - **Admins:**
        - Admins have the highest level of access and can manage the entire platform.
        - They can post physical and virtual events.
        - Admins can verify job postings.
    - **Employers:**
        - Companies can register, and employees can sign up as employers.
        - Employers use a unique company code to post jobs, manage job postings, and handle applications.
    - **Job Seekers:**
        - Individuals can sign up, browse jobs, and apply for positions.
    - **Event Attendees:**
        - Users can attend physical and virtual events posted by admins.

- **Job Management:**
    - Employers can post jobs, review applications, and accept or reject candidates.
    - Job seekers can search for jobs, apply, and track application status.
    - Admins can verify job postings.

- **Event Management:**
    - Admins can post physical and virtual events.
    - Users, specifically event attendees, can attend these events to network and explore opportunities.

- **Authentication and Security:**
    - Spring Security is implemented to ensure secure access to different parts of the application.
    - Passwords are securely hashed and stored.

- **API Testing:**
    - **Swagger:**
    - Explore and test APIs interactively using Swagger.
    - Swagger URL: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

- **Database:**
    - MySQL is used to store user data, job postings, and event details.

## Tech Stack

- **Backend:**
    - Java Spring Boot
    - Spring Security
    - MySQL
    - JUnit (Testing Framework)
    - Mockito (Mocking Framework)

- **Frontend:**
    - React JS

# API Endpoints

## Job Seeker Controller

| Endpoint                                  | Method | Description                                   |
|-------------------------------------------|--------|-----------------------------------------------|
| /api/v1/job-seeker/updateProfileDetails   | PUT    | Update job seeker's profile details            |
| /api/v1/job-seeker/initiateRegistration    | POST   | Start the registration process for a job seeker|
| /api/v1/job-seeker/completeRegistration   | POST   | Complete the registration for a job seeker    |
| /api/v1/job-seeker/getJobSeeker           | GET    | Get information about the logged-in job seeker|
| /api/v1/job-seeker/getJobSeeker/{id}      | GET    | Get information about a specific job seeker   |

## Company Controller

| Endpoint                                    | Method | Description                                 |
|---------------------------------------------|--------|---------------------------------------------|
| /api/v1/job-crony/company/updateCompanyDetails | PUT  | Update details of the company               |

## User Controller

| Endpoint                                   | Method | Description                                 |
|--------------------------------------------|--------|---------------------------------------------|
| /api/v1/job-crony-user/updatePassword      | PUT    | Update the password for the logged-in user  |
| /api/v1/job-crony-user/resetPassword       | PUT    | Reset the password for the logged-in user   |
| /api/v1/job-crony-user/forgotPassword/{email} | PUT  | Initiate the password reset process         |
| /api/v1/job-crony-user/deleteAccount/{confirmationKeyword} | DELETE | Delete the user account               |

## Job Opening Controller

| Endpoint                               | Method | Description                             |
|----------------------------------------|--------|-----------------------------------------|
| /api/v1/job/postJobOpening             | POST   | Post a new job opening                   |

## Application Controller

| Endpoint                                       | Method | Description                                    |
|------------------------------------------------|--------|------------------------------------------------|
| /api/v1/job-crony/application/withdrawApplication | POST | Withdraw a job application                      |
| /api/v1/job-crony/application/reviewApplication   | POST | Review a job application                        |
| /api/v1/job-crony/application/rejectApplication   | POST | Reject a job application                        |
| /api/v1/job-crony/application/getApplication/{id} | POST | Get details of a specific job application       |
| /api/v1/job-crony/application/apply              | POST | Submit a new job application                    |
| /api/v1/job-crony/application/acceptApplication   | POST | Accept a job application                        |
| /api/v1/job-crony/application/getAllApplications  | GET  | Get a list of all job applications              |

## Event Attendee Controller

| Endpoint                           | Method | Description                              |
|------------------------------------|--------|------------------------------------------|
| /api/v1/eventAttendee/attendEvent  | POST   | Register to attend a physical or virtual event |

## Event Controller

| Endpoint                         | Method | Description                            |
|----------------------------------|--------|----------------------------------------|
| /api/v1/event/createEvent        | POST   | Create a new physical or virtual event |

## Employer Controller

| Endpoint                       | Method | Description                         |
|--------------------------------|--------|-------------------------------------|
| /api/v1/employer/register      | POST   | Register as an employer             |

## Authentication Controller

| Endpoint                       | Method | Description                         |
|--------------------------------|--------|-------------------------------------|
| /api/v1/auth/login             | POST   | Authenticate and log in             |

## Admin Controller

| Endpoint                                   | Method | Description                           |
|--------------------------------------------|--------|---------------------------------------|
| /api/v1/admin/sendInvitationLink           | POST   | Send an invitation link to a user     |
| /api/v1/admin/register                     | POST   | Register as an admin                  |
| /api/v1/admin/getAllAdmin                  | GET    | Get a list of all administrators      |

## OAuth Demo Controller

| Endpoint                      | Method | Description                    |
|-------------------------------|--------|--------------------------------|
| /api/v1/demo                  | GET    | A demo endpoint for OAuth       |

## Setup Instructions

1. **Clone the Repository:**
    ```bash
    git clone https://github.com/reniike/Job-crony.git
    ```

2. **Database Setup:**
    - Create a MySQL database.
    - Update `application.properties` with your database configuration.

3. **Run the Application:**
    ```bash
    ./mvnw spring-boot:run
    ```

4. **Access the Application:**
    - Open your web browser and go to [http://localhost:8080](http://localhost:8080).
