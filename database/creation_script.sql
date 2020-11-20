CREATE TABLE Users (
    id SERIAL,
    cpf VARCHAR(14) NOT NULL,
    email VARCHAR(128) NOT NULL,
    apikey VARCHAR(64) NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE Admins (
    id SERIAL,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,

    CONSTRAINT pk_admin PRIMARY KEY (id)
);

CREATE TABLE ProblemTypes (
    id SERIAL,
    name VARCHAR(64) NOT NULL,

    CONSTRAINT pk_problemtypes PRIMARY KEY (id)
);

CREATE TABLE CompetentOrgans (
    id SERIAL,
    name VARCHAR(64) NOT NULL,

    CONSTRAINT pk_competentorgans PRIMARY KEY (id)
);

CREATE TABLE Complaints (
    id SERIAL,
    title VARCHAR(64) NOT NULL,
    description VARCHAR(256) NOT NULL,
    urgency INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    competentorgan_id INTEGER NOT NULL,
    problemtype_id INTEGER NOT NULL,

    CONSTRAINT pk_complaints PRIMARY KEY (id),
    CONSTRAINT fk_user_complaint FOREIGN KEY (user_id) REFERENCES Users,
    CONSTRAINT fk_competentorgan_complaint FOREIGN KEY (competentorgan_id) REFERENCES CompetentOrgans,
    CONSTRAINT fk_problemtype_complaint FOREIGN KEY (problemtype_id) REFERENCES ProblemTypes
);

CREATE TABLE Feedback (
    id SERIAL,
    description VARCHAR(256) NOT NULL,
    complaint_id INTEGER NOT NULL,

    CONSTRAINT pk_feedback PRIMARY KEY (id),
    CONSTRAINT fk_complaint_feedback FOREIGN KEY (complaint_id) REFERENCES Complaints
);

CREATE TABLE Images (
    id SERIAL,
    path VARCHAR(128) NOT NULL,
    complaint_id INTEGER NOT NULL,

    CONSTRAINT pk_images PRIMARY KEY (id),
    CONSTRAINT fk_complaint_image FOREIGN KEY (complaint_id) REFERENCES Complaints
);