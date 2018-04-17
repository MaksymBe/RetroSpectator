INSERT INTO retro.APoint (id, date, status, title, retro_id) VALUES (1, '2018-04-18', 'Done', 'Title a.p.', 1);
INSERT INTO retro.Point (id, date, title, type, retro_id, author_id) VALUES (1, '2018-04-18', 'Point title', 'plus', 1, 1);
INSERT INTO retro.Retro (id, finishDate, impression, team_id) VALUES (1, '2018-04-18', 'impression retro', 1);
INSERT INTO retro.Retro (id, finishDate, impression, team_id) VALUES (2, '2018-04-18', 'impression retroo', 2);
INSERT INTO retro.Team (id, link, status, retro_id) VALUES (1, 'sdf', 'done', 1);
INSERT INTO retro.User (id) VALUES (1);
INSERT INTO retro.user_team (user_id, team_id) VALUES (1, 1);