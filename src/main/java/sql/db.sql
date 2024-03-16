-- Table for users
CREATE TABLE Users (
                       UserNameDB VARCHAR(255) NOT NULL PRIMARY KEY,
                       UserEmailDB VARCHAR(255) NOT NULL,
);

-- Table for Responsible users
CREATE TABLE Responsible (
                             UserNameDB VARCHAR(255) NOT NULL PRIMARY KEY,
                             UserEmailDB VARCHAR(255) NOT NULL,
                             Finance DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (UserNameDB) REFERENCES Users(UserNameDB)
);

-- Table for Coach users
CREATE TABLE Coach (
                       UserNameDB VARCHAR(255) NOT NULL PRIMARY KEY,
                       UserEmailDB VARCHAR(255) NOT NULL,
                       Salary DECIMAL(10, 2) NOT NULL,
                       Sport VARCHAR(100) NOT NULL,
                       Schedule DATETIME NOT NULL,
                       FOREIGN KEY (UserNameDB) REFERENCES Users(UserNameDB)
);

-- Table for Member (to store attributes specific to members)
CREATE TABLE Member (
                        UserNameDB VARCHAR(255) NOT NULL PRIMARY KEY,
                        UserEmailDB VARCHAR(255) NOT NULL,
                        Membership VARCHAR(100) NOT NULL,
                        Age INT NOT NULL,
                        Height DECIMAL(5, 2) NOT NULL,
                        Weight DECIMAL(5, 2) NOT NULL,
                        Schedule DATETIME NOT NULL,
                        FOREIGN KEY (UserNameDB) REFERENCES Users(UserNameDB)
);

-- Table for Performance (to store performance data)
CREATE TABLE Performance (
                             PerformanceID INT PRIMARY KEY AUTO_INCREMENT,
                             UserNameDB VARCHAR(255) NOT NULL PRIMARY KEY,
                             UserEmailDB VARCHAR(255) NOT NULL,
                             RunsScored DECIMAL(10, 2) NOT NULL,
                             BallsFaced DECIMAL(10, 2) NOT NULL,
                             Fours DECIMAL(10, 2) NOT NULL,
                             Sixes DECIMAL(10, 2) NOT NULL,
                             WicketsTaken DECIMAL(10, 2) NOT NULL,
                             BallsBowled DECIMAL(10, 2) NOT NULL,
                             RunsGave DECIMAL(10, 2) NOT NULL,
                             FOREIGN KEY (UserNameDB) REFERENCES Users(UserNameDB)
);
