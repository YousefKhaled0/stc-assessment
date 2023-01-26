--
-- Name: files_data; Type: TABLE;
--
CREATE TABLE IF NOT EXISTS files_data (
    id character varying(255) NOT NULL,
    content bytea,
    item_id character varying(255)
);

--
-- Name: items; Type: TABLE;
--
CREATE TABLE IF NOT EXISTS items (
    id character varying(255) NOT NULL,
    name character varying(500) NOT NULL,
    type character varying(255) NOT NULL,
    group_id character varying(255),
    parent_id character varying(255)
);

--
-- Name: permission_groups; Type: TABLE;
--
CREATE TABLE IF NOT EXISTS permission_groups (
    id character varying(255) NOT NULL,
    group_name character varying(255)
);

--
-- Name: user_permissions; Type: TABLE;
--
CREATE TABLE IF NOT EXISTS user_permissions (
    id character varying(255) NOT NULL,
    email character varying(255),
    permission character varying(255),
    group_id character varying(255)
);

--
-- Name: files_data files_data_pkey; Type: CONSTRAINT;
--
ALTER TABLE ONLY files_data
    ADD CONSTRAINT files_data_pkey PRIMARY KEY (id);

--
-- Name: items items_pkey; Type: CONSTRAINT;
--
ALTER TABLE ONLY items
    ADD CONSTRAINT items_pkey PRIMARY KEY (id);

--
-- Name: permission_groups permission_groups_pkey; Type: CONSTRAINT;
--
ALTER TABLE ONLY permission_groups
    ADD CONSTRAINT permission_groups_pkey PRIMARY KEY (id);

--
-- Name: permission_groups permission_groups_name_unique; Type: CONSTRAINT;
--
ALTER TABLE ONLY permission_groups
    ADD CONSTRAINT permission_groups_name_unique UNIQUE (group_name);

--
-- Name: items uk6l470k8mtvo80qsyj7r504doh; Type: CONSTRAINT;
--
ALTER TABLE ONLY items
    ADD CONSTRAINT uk6l470k8mtvo80qsyj7r504doh UNIQUE (type, name, parent_id);

--
-- Name: user_permissions user_permissions_pkey; Type: CONSTRAINT;
--
ALTER TABLE ONLY user_permissions
    ADD CONSTRAINT user_permissions_pkey PRIMARY KEY (id);

--
-- Name: files_data fk40fvq5vk5jjoqd1org31pduad; Type: FK CONSTRAINT;
--
ALTER TABLE ONLY files_data
    ADD CONSTRAINT fk40fvq5vk5jjoqd1org31pduad FOREIGN KEY (item_id) REFERENCES items(id);

--
-- Name: items fknl8t0cyp8m6vi7hksmlqpj0ek; Type: FK CONSTRAINT;
--
ALTER TABLE ONLY items
    ADD CONSTRAINT fknl8t0cyp8m6vi7hksmlqpj0ek FOREIGN KEY (parent_id) REFERENCES items(id);

--
-- Name: items fkouotkicp88huerhy3ilyad6tu; Type: FK CONSTRAINT;
--
ALTER TABLE ONLY items
    ADD CONSTRAINT fkouotkicp88huerhy3ilyad6tu FOREIGN KEY (group_id) REFERENCES permission_groups(id);

--
-- Name: user_permissions fktqmbl7yrc0um4gs9m8kq5bwr2; Type: FK CONSTRAINT;
--
ALTER TABLE ONLY user_permissions
    ADD CONSTRAINT fktqmbl7yrc0um4gs9m8kq5bwr2 FOREIGN KEY (group_id) REFERENCES permission_groups(id);