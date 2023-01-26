SELECT `User`.`user_id`, `User`.`username`, `Training_details`.`training_id`, `Training_details`.`training_date`, COUNT(`User`.`user_id`)
FROM `User` 
INNER JOIN `Training_details` ON `User`.`user_id` = `Training_details`.`user_id`
GROUP BY `User`.`user_id`, `User`.`username`, `Training_details`.`training_id`, `Training_details`.`training_date`
HAVING COUNT(`User`.`user_id`) > 1
ORDER BY `Training_details`.`training_date` DESC;

-- -----------------------------------------------------
-- Same query but simplified.
-- -----------------------------------------------------
SELECT u.`user_id`, u.`username`, t.`training_id`, t.`training_date`, COUNT(u.`user_id`)
FROM `User` u
INNER JOIN `Training_details` t ON u.`user_id` = t.`user_id`
GROUP BY u.`user_id`, u.`username`, t.`training_id`, t.`training_date`
HAVING COUNT(u.`user_id`) > 1
ORDER BY t.`training_date` DESC;