CREATE TABLE IF NOT EXISTS tipo_producto
(
    id_tipo INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo    TEXT(80) NOT NULL
);

CREATE TABLE IF NOT EXISTS marca_producto
(
    id_marca INTEGER PRIMARY KEY AUTOINCREMENT,
    marca    TEXT(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS producto
(
    id_producto INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre      TEXT(120) NOT NULL,
    tipo        INTEGER DEFAULT 0,
    marca       INTEGER DEFAULT 0,
    FOREIGN KEY (tipo) REFERENCES tipo_producto (id_tipo),
    FOREIGN KEY (marca) REFERENCES marca_producto (id_marca)
);

CREATE TABLE IF NOT EXISTS direccion
(
    id_direccion INTEGER PRIMARY KEY AUTOINCREMENT,
    calle_p      TEXT(150) NOT NULL,
    calle_s      TEXT(150),
    referencia   TEXT(150),
    ciudad       TEXT(150)
);

CREATE TABLE IF NOT EXISTS establecimiento
(
    id_estab   INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre     TEXT(150) NOT NULL,
    estado     INTEGER(1) DEFAULT 1,
    url_img    TEXT(255),
    tipo_local TEXT(50)  NOT NULL,
    direccion  INTEGER,
    FOREIGN KEY (direccion) REFERENCES direccion (id_direccion)
);

CREATE TABLE IF NOT EXISTS usuario
(
    dni       TEXT(10) PRIMARY KEY,
    nombres   TEXT(80) NOT NULL,
    apellidos TEXT(80) NOT NULL,
    telefono  TEXT(10) NOT NULL,
    usuario   TEXT(25) NOT NULL,
    password  TEXT(25) NOT NULL,
    fecha_nac DATE     NOT NULL
);

CREATE TABLE IF NOT EXISTS gerente
(
    dni             TEXT(10) PRIMARY KEY,
    ruc             TEXT(13) NOT NULL UNIQUE,
    establecimiento INTEGER(4),
    FOREIGN KEY (dni) REFERENCES usuario (dni)
);

CREATE TABLE IF NOT EXISTS solicitud
(
    id_solicitud INTEGER PRIMARY KEY AUTOINCREMENT,
    cantidad     INTEGER(3) NOT NULL,
    fecha        DATE     DEFAULT (CURRENT_DATE),
    cliente      TEXT(10)   NOT NULL,
    producto     INTEGER    NOT NULL,
    estado       TEXT(11) DEFAULT ('PENDIENTE'),
    FOREIGN KEY (cliente) REFERENCES usuario (dni),
    FOREIGN KEY (producto) REFERENCES producto (id_producto)
);

CREATE TABLE IF NOT EXISTS respuesta
(
    establecimiento INTEGER,
    solicitud       INTEGER,
    PRIMARY KEY (establecimiento, solicitud)
);

