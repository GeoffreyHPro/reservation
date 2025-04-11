CREATE TABLE Bedroom (
    id SERIAL primary key,
    nbMaxPeople INTEGER,
    bedroomName VARCHAR(255),
    hotelId INTEGER,
    FOREIGN KEY (hotelId)         
        REFERENCES hotel(id)      
        ON DELETE CASCADE 
);