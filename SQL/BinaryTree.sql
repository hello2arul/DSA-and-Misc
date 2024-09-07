-- print leaf, root, mid nodes of a binary tree using sql for the following table
-- eg)
-- node, parent_node
-- 2,  null
-- 3, 2
-- 4, 2
-- 5, 3

CREATE TABLE BinaryTree (
                            node INT PRIMARY KEY,
                            parent_node INT
);

INSERT INTO BinaryTree (node, parent_node) VALUES (2, NULL);
INSERT INTO BinaryTree (node, parent_node) VALUES (3, 2);
INSERT INTO BinaryTree (node, parent_node) VALUES (4, 2);
INSERT INTO BinaryTree (node, parent_node) VALUES (5, 3);


-- Solution
SELECT 
    node,
    CASE
        WHEN parent_node IS NULL THEN 'root'
        WHEN
            parent_node IS NOT NULL
                AND node IN (SELECT DISTINCT
                    parent_node
                FROM
                    BinaryTree)
        THEN
            'inner'
        ELSE 'leaf'
    END AS node_type
FROM
    BinaryTree;