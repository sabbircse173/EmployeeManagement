-- Default admin (password: Admin@123)
INSERT INTO users (username, email, password, role, enabled)
VALUES ('admin', 'admin@ideascale.com',
    '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewKyNiLMJlV/IFZi',
    'ADMIN', TRUE);
