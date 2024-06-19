CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       firstName VARCHAR(255),
                       lastName VARCHAR(255),
                       maidenName VARCHAR(255),
                       age INT,
                       gender VARCHAR(50),
                       email VARCHAR(255),
                       phone VARCHAR(50),
                       username VARCHAR(255),
                       password VARCHAR(255),
                       birthDate DATE,
                       image VARCHAR(255),
                       bloodGroup VARCHAR(10),
                       height DOUBLE,
                       weight DOUBLE,
                       eyeColor VARCHAR(50),
                       hair_id BIGINT,
                       ip VARCHAR(50),
                       address_id BIGINT,
                       macAddress VARCHAR(50),
                       university VARCHAR(255),
                       bank_id BIGINT,
                       company_id BIGINT,
                       ein VARCHAR(50),
                       ssn VARCHAR(50),
                       userAgent VARCHAR(255),
                       crypto_id BIGINT,
                       role VARCHAR(50),
                       FOREIGN KEY (hair_id) REFERENCES hair(id),
                       FOREIGN KEY (address_id) REFERENCES address(id),
                       FOREIGN KEY (bank_id) REFERENCES bank(id),
                       FOREIGN KEY (company_id) REFERENCES company(id),
                       FOREIGN KEY (crypto_id) REFERENCES crypto(id)
);

CREATE TABLE logindata (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255),
                       password VARCHAR(255)
)

CREATE TABLE address (
                         id BIGINT PRIMARY KEY,
                         address VARCHAR(255),
                         city VARCHAR(255),
                         state VARCHAR(255),
                         stateCode VARCHAR(50),
                         postalCode VARCHAR(50),
                         coordinates_id BIGINT,
                         country VARCHAR(255),
                         FOREIGN KEY (coordinates_id) REFERENCES coordinates(id)
);

CREATE TABLE bank (
                      id BIGINT PRIMARY KEY,
                      cardExpire VARCHAR(50),
                      cardNumber VARCHAR(50),
                      cardType VARCHAR(50),
                      currency VARCHAR(50),
                      iban VARCHAR(50)
);

CREATE TABLE company (
                         id BIGINT PRIMARY KEY,
                         department VARCHAR(255),
                         name VARCHAR(255),
                         title VARCHAR(255),
                         address_id BIGINT,
                         FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE coordinates (
                             id BIGINT PRIMARY KEY,
                             lat DOUBLE,
                             lng DOUBLE
);

CREATE TABLE crypto (
                        id BIGINT PRIMARY KEY,
                        coin VARCHAR(255),
                        wallet VARCHAR(255),
                        network VARCHAR(255)
);

CREATE TABLE hair (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      color VARCHAR(255),
                      type VARCHAR(255)
);

CREATE TABLE category (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      slug VARCHAR(255) UNIQUE,
                      name VARCHAR(255),
                      url VARCHAR(255)
);
CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255) NOT NULL,
                         description TEXT,
                         category VARCHAR(255),
                         price DECIMAL(10,2),
                         discount_percentage DECIMAL(5,2),
                         rating DOUBLE,
                         stock INT,
                         brand VARCHAR(255),
                         sku VARCHAR(255),
                         weight DOUBLE,
                         dimensions_id BIGINT,
                         warranty_information TEXT,
                         shipping_information TEXT,
                         availability_status VARCHAR(255),
                         return_policy TEXT,
                         minimum_order_quantity INT,
                         metaData_id BIGINT,
                         thumbnail VARCHAR(255),
                         FOREIGN KEY (dimensions_id) REFERENCES dimensions(id),
                         FOREIGN KEY (metaData_id) REFERENCES metaData(id)
);
CREATE TABLE dimensions (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            width DOUBLE,
                            height DOUBLE,
                            depth DOUBLE
);
CREATE TABLE metaData (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          createdAt VARCHAR(255),
                          updatedAt VARCHAR(255),
                          barcode VARCHAR(255),
                          qrCode VARCHAR(255)
);
CREATE TABLE review (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        rating INT,
                        comment TEXT,
                        date DATE,
                        reviewer_name VARCHAR(255),
                        reviewer_email VARCHAR(255),
                        product_id BIGINT,
                        FOREIGN KEY (product_id) REFERENCES product(id)
);
