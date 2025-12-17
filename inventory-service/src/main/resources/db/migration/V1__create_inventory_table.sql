-- Inventory
CREATE TABLE IF NOT EXISTS inventory (
    inventory_id INT PRIMARY KEY,
    product_id    INT NOT NULL,
    available     INT NOT NULL,
    reserved      INT NOT NULL,
    warehouse     VARCHAR(50) NOT NULL
);

INSERT INTO inventory (inventory_id, product_id, available, reserved, warehouse) VALUES
(1, 101, 120, 5, 'WH-01'),
(2, 102, 80, 0, 'WH-01'),
(3, 103, 60, 2, 'WH-02'),
(4, 104, 200, 10, 'WH-02'),
(5, 105, 50, 1, 'WH-03'),
(6, 106, 40, 0, 'WH-01'),
(7, 107, 30, 3, 'WH-03'),
(8, 108, 70, 0, 'WH-02'),
(9, 109, 25, 2, 'WH-01'),
(10, 110, 15, 1, 'WH-03');