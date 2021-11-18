ALTER TABLE opening_times
DROP FOREIGN KEY opening_times_ibfk_1;

ALTER TABLE opening_times
ADD CONSTRAINT opening_times_ibfk_1 FOREIGN KEY (`HOTEL_ID`) REFERENCES hotel (HOTEL_ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE reservation
DROP FOREIGN KEY reservation_ibfk_1;

ALTER TABLE reservation
ADD CONSTRAINT reservation_ibfk_1 FOREIGN KEY (`GUEST_ID`) REFERENCES guest (GUEST_ID) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE room_reservation
DROP FOREIGN KEY room_reservation_ibfk_2;

ALTER TABLE room_reservation
ADD CONSTRAINT room_reservation_ibfk_2 FOREIGN KEY (`ROOM_ID`) REFERENCES room (ROOM_ID) ON DELETE CASCADE ON UPDATE CASCADE;