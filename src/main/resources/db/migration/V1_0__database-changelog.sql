CREATE TABLE public.user (
    id VARCHAR(36),
    username VARCHAR(200),
    password VARCHAR(200),
    user_id VARCHAR(36),
    CONSTRAINT pk_user PRIMARY KEY(id)
);

CREATE TABLE public.role(
    id VARCHAR(36),
    role VARCHAR(200),
    description VARCHAR(200),
    CONSTRAINT pk_role PRIMARY KEY(id)
);

INSERT INTO role VALUES ('UNASSIGNED', 'UNASSIGNED');

CREATE TABLE public.user_role(
    id VARCHAR(36),
    role_id VARCHAR(36),
    user_id VARCHAR(36),
    CONSTRAINT pk_user_role PRIMARY KEY(id),
    CONSTRAINT fk_user_role_role FOREIGN KEY(role_id) REFERENCES public.role(id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES public.user(id)
);