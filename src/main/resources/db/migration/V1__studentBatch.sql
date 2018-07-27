alter table student_batch ADD COLUMN batch_status_type INT(11) DEFAULT 0;
alter table student_batch ADD COLUMN id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY;



update student_batch set batch_status_type =0;
