# Computer Science Department App

This is an Android app made to facilitate event planning and a program of study requirement checker for students in The
Department of Computer and Mathematical Sciences (CMS) at the University of Toronto Scarborough. The app was made using
Android Studio, with Firebase for the backend. It was tested using stubbing and mocking with Mockito along with JUnit.

## Admin Features

- Post messages to an announcement page visible to all users
- Review complaints submitted by students
- Create department events with a name, description, start time, end time, and maximum capacity
- View student reviews for department events

Whenever a department event is created, all users receive a notification.

## Student Features

- Determine eligibility for CMS programs of study through a questionnaire in the app
- Submit complaints for the admin(s) to view
- RSVP to scheduled events (depending on the event's capacity)
- Submit reviews for events attended
