CREATE TABLE Users (
    id              INTEGER PRIMARY KEY,
    user_id         VARCHAR(50),
    password        VARCHAR(199),
    mail_address    VARCHAR(199),
    phone_code      VARCHAR(11),
    last_name       VARCHAR(199),
    first_name      VARCHAR(199),
    age             INTEGER,
    gender          INTEGER,
    birthday        CHAR(8),
    post_code       CHAR(7),
    preference_code INTEGER,
    address         VARCHAR(199),
)
/
CREATE TABLE Facilitys (
    id              INTEGER PRIMARY KEY,
    facility_code   INTEGER,
    facility_name   VARCHAR(199),

)