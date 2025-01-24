CREATE TABLE City (
    id SERIAL primary key,
    cityName VARCHAR(255),
    countryId INTEGER,
    FOREIGN KEY (countryId)         
        REFERENCES country(id)      
        ON DELETE CASCADE 
);