USE lms_db;
INSERT INTO languages (language_name)
VALUES ('Python'),
       ('JavaScript'),
       ('Java'),
       ('C++'),
       ('C#'),
       ('Ruby'),
       ('PHP'),
       ('Swift'),
       ('Kotlin'),
       ('Go');
INSERT INTO featured_videos(thumbnail, title, url)
VALUES ('https://i.ytimg.com/vi/7LNl2JlZKHA/hqdefault.jpg', 'How to Create a Flask + React Project | Python Backend + React Frontend', 'https://www.youtube.com/watch?v=7LNl2JlZKHA'),
       ('https://i.ytimg.com/vi/p5AtOUyf250/hqdefault.jpg', 'HOW DOES IT WORK? CONNECT JS FRONTEND AND PYTHON BACKEND TOGETHER', 'https://www.youtube.com/watch?v=p5AtOUyf250'),
       ('https://i.ytimg.com/vi/Wpeym1_lmPo/hqdefault.jpg', 'Java vs JavaScript', 'https://www.youtube.com/watch?v=Wpeym1_lmPo'),
       ('https://i.ytimg.com/vi/sQCkdbu5L1c/hqdefault.jpg', 'Difference between Java, PHP, Ruby and Python', 'https://www.youtube.com/watch?v=sQCkdbu5L1c')