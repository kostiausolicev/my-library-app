CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL      NOT NULL,

    fullName VARCHAR(50) NOT NULL,
    email    VARCHAR(50) NOT NULL,
    phone    VARCHAR(15) NOT NULL,
    login    TEXT        NOT NULL,
    password TEXT        NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS books
(
    id          SERIAL NOT NULL,

    title       TEXT   NOT NULL,
    author      TEXT   NOT NULL,
    image       TEXT,
    available   BOOLEAN DEFAULT TRUE,
    rating      FLOAT  NOT NULL,
    category    TEXT   NOT NULL,
    date        DATE   NOT NULL,
    description TEXT,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS orders
(
    id          SERIAL      NOT NULL,

    userId     INTEGER     NOT NULL,
    bookId     INTEGER     NOT NULL,
    orderDate  TIMESTAMP   NOT NULL DEFAULT NOW(),

    PRIMARY KEY (id)
);

ALTER TABLE orders ADD FOREIGN KEY (userId) REFERENCES users (id);
ALTER TABLE orders ADD FOREIGN KEY (bookId) REFERENCES books (id);

CREATE TABLE IF NOT EXISTS filials
(
    id      SERIAL NOT NULL,
    name    TEXT,
    address TEXT,
    openAt  INTEGER, -- Час открытия
    closeAt INTEGER, -- Час закрытия
    image   TEXT,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS events
(
    id          SERIAL    NOT NULL,

    title       TEXT      NOT NULL,
    description TEXT,
    startAt     TIMESTAMP NOT NULL,
    image       TEXT,
    filialId    INTEGER   NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE events ADD FOREIGN KEY (filialId) REFERENCES filials (id);