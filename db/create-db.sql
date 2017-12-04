DROP DATABASE IF EXISTS robots;
DROP ROLE IF EXISTS robots_role;
DROP USER IF EXISTS robots_user;

CREATE ROLE robots_role with LOGIN;
CREATE DATABASE robots WITH template = template0 ENCODING 'UTF8';
ALTER DATABASE robots OWNER TO robots_role;

CREATE USER robots_user with PASSWORD 'PASSWORDROBOTS';;

CREATE TABLESPACE robots_data OWNER robots_user LOCATION '/c/Users/l38117419/robots/db/robots_data';
CREATE TABLESPACE robots_index OWNER robots_user LOCATION '/c/Users/l38117419/robots/db/robots_index';
GRANT robots_role TO robots_user;

alter USER robots_user superuser;


