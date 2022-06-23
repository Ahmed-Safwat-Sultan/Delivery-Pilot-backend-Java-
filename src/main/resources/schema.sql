

CREATE TABLE if NOT EXISTS CITIES (
    id INT PRIMARY KEY auto_increment,
    name varchar(25) NOT NULL
);


CREATE TABLE IF NOT EXISTS ROADS(
    cityId1 INT,
    cityId2 INT,
    foreign key (cityId1) references CITIES(id) ,
    foreign key (cityId2) references CITIES(id),
    distance DOUBLE
);
