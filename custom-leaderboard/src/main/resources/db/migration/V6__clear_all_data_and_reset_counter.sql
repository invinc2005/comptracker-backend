-- Delete all data
DELETE FROM participant;
DELETE FROM competition;

-- Reset auto-increment counters
ALTER TABLE participant AUTO_INCREMENT = 1;
ALTER TABLE competition AUTO_INCREMENT = 1;
