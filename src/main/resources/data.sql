INSERT INTO box (txref, weight_limit, battery_capacity, state) VALUES
('BOX001', 500, 100, 'IDLE'),
('BOX002', 400, 50, 'LOADING');

INSERT INTO item (name, weight, code, box_id) VALUES
('Item1', 150, 'ITEM001', 1),
('Item2', 250, 'ITEM002', 2);

