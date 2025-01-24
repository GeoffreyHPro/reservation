CREATE TABLE Hotel (
    id SERIAL primary key,
    hotelName VARCHAR(255),
    cityId INTEGER,
    FOREIGN KEY (cityId)         
        REFERENCES city(id)      
        ON DELETE CASCADE 
);