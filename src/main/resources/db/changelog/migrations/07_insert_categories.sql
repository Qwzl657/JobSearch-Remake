INSERT INTO categories (name, parent_id) VALUES
                                             ('IT', NULL),
                                             ('Finance', NULL),
                                             ('Marketing', NULL),
                                             ('Java Developer', 1),
                                             ('Python Developer', 1),
                                             ('Frontend Developer', 1),
                                             ('DevOps', 1),
                                             ('Data Science', 1);

INSERT INTO contact_types (type) VALUES
                                     ('Telegram'),
                                     ('Email'),
                                     ('Phone'),
                                     ('LinkedIn'),
                                     ('Facebook');