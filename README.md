## Synopsis

This application updates the values of specific column in a CSV file.

## Functionality

The application accepts the following arguments:

* Fully qualified file path
* Column name in CSV file
* Existing value of column
* New Value of column

The application can be executed with `java` command, e.g.:

`java -jar csv-update-application.jar <file_path> "column_1" "old_value" "new_value"`

The above command will read the file present at `<file_path>` loaction and update the value of `column_1` for all the rows to `new_value` if the existing value is `old_value`. It will throw an Exception and print the error message in the following scenarios:

* If the file doesn't exist or is not readable
* If the column is not present in the file
* If the file is empty

## Installation

To install the app, run through the following steps:

1. Clone the repo and change the directory to be the cloned directory (i.e. app-bank)
2. Make sure the machine has [Java](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html) and [Maven](https://maven.apache.org/download.cgi) installed.
3. From the command line, type `mvn clean install`, this will start the installation process
4. Once installation is complete, the `jar` file will be present inside `target` folder. This file is an executable file and can be run via `java -jar target/csv-update-application.jar` command with a list of arguments