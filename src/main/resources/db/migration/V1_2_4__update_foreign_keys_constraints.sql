ALTER TABLE room
DROP FOREIGN KEY room_ibfk_1;

ALTER TABLE room
ADD CONSTRAINT room_ibfk_1 FOREIGN KEY (`HOTEL_ID`) REFERENCES hotel (HOTEL_ID) ON DELETE CASCADE ON UPDATE CASCADE;