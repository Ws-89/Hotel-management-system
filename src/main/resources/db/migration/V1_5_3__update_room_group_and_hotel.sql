ALTER TABLE room_group ADD COLUMN HOTEL_ID INT NOT NULL;

ALTER TABLE room_group
ADD CONSTRAINT HOTEL_ID
FOREIGN KEY (HOTEL_ID) REFERENCES hotel(HOTEL_ID) ON DELETE CASCADE;