USE sistemiautobuseve;
ALTER TABLE routes
ADD CONSTRAINT unique_check UNIQUE (start_city,end_city,schedule_id);