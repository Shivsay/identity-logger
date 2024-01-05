# identity-logger

**identity-logger** is an application used to view data on people you know.

## Dependencies

- [mariadb](https://mariadb.com/)

## Installation

- Step 1: Go into the root directory of the project 

    ```
    cd identity-logger
    ```
- Step 2: start mariadb shell

- Step 3: type the following commands changing variables as you like

    ```
    CREATE USER 'YOUR_USER_NAME'@'localhost' IDENTIFIED BY 'YOUR_PASSWORD';
    CREATE DATABASE peopleViewer;
    GRANT ALL PRIVILEGES ON peopleviewer.* TO 'YOU_USER_NAME'@'localhost';
    ```

- Step 4: exit current shell and open a new shell by using command
    
    ```
    mariadb -u YOUR_USER_NAME -p
    ```

    this will ask you for the password (YOUR_PASSWORD).

- Step 5: create the table and add fields 

    ```
    CREATE TABLE people (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    dob DATE,
    description TEXT,
    imagePath VARCHAR(255),
    );
    ```

- Step 6: now add data 

    ```
    INSERT INTO your_table (name, dob, description)
    VALUES
    ('name1', '2003-03-03', 'Description for name1. This is a paragraph of text.'),
    ('name2', '2003-04-04', 'Description for name2. Another paragraph goes here.');
    ```

- Step 7: now run the following commands in the terminal to compile and execute
    ```
    ./gradlew build

    ./gradlew run
    ```
