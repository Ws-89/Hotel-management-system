ALTER TABLE reservation
RENAME COLUMN from_date TO START_DATE;

ALTER TABLE reservation
RENAME COLUMN to_date TO END_DATE;