INSERT INTO certifications (cert_id, cert_name, employee_id, issued_date, expiry_date, status, issuer, score, created_at, updated_at) 
VALUES 
('550e8400-e29b-41d4-a716-446655440000', 'AWS Solutions Architect', '660e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, DATEADD('day', 25, CURRENT_TIMESTAMP), 'VALID', 'Amazon', 95.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440002', 'Java OCP', '660e8400-e29b-41d4-a716-446655440001', CURRENT_TIMESTAMP, DATEADD('day', 10, CURRENT_TIMESTAMP), 'EXPIRING', 'Oracle', 88.0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO renewal_notifications (notification_id, cert_id, employee_id, cert_name, expiry_date, days_until_expiry, status, sent_at, notification_channel, retry_count, created_at, updated_at)
VALUES
('750e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440001', 'AWS Solutions Architect', DATEADD('day', 25, CURRENT_TIMESTAMP), 25, 'PENDING', NULL, 'EMAIL', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);