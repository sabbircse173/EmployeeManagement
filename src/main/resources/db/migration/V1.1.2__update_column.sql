-- Update string values into numeric (still stored as string for now)
UPDATE employee
SET gender = CASE
                 WHEN gender = 'male'   THEN '1'
                 WHEN gender = 'female' THEN '2'
                 WHEN gender = 'other'  THEN '3'
    END;

-- Change column type to INT
ALTER TABLE employee
    MODIFY gender INT;

