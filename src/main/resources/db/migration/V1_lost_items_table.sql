CREATE TABLE lost_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    found_location VARCHAR(255) NOT NULL,
    found_at DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    claimed_by_rut VARCHAR(20),
    reporter_staff_id BIGINT NOT NULL
);