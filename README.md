# Discount-parser
#### Table of Contents
* [Project purpose](#purpose)
* [Project structure](#structure)
* [For developer](#developer-start)
* [Author](#Author)
#
#### <a name="purpose">Project purpose</a>
This project will help you to get some valuable data from a web resource. Simply run this project on your PC
and you will obtain a CSV file with detailed information about discount products of three categories(electronics, health and beauty)
 from https://allegro.pl/strefaokazji/ web-site.
#
#### <a name="purpose">Project structure</a>
* Java 14
* Maven 3.6.3
* maven-checkstyle-plugin 3.2.3
* maven-war-plugin 3.1.1
* log4j 1.2.17
* jsoup 1.13.1
* lombok 1.18.12
* opencsv 5.2
* spring-context 5.2.2.RELEASE
#

#### <a name="purpose">For developers</a>
1. Open the project in your IDE
2. Choose sdk 11.0  or higher in Project Structure
3. Configure logging properties in src/main/resources/log4j.properties 
4. Configure destination directory to store files created during runtime process in you don't want to use a default destination
5. Chose the category you are interested in and configure corresponding properties in src/main/resources/app.properties
6. Run the project

By default, the project parses discount products from three categories. You can change the categories 
you are interested in by configuring corresponding properties in app.properties file. As a result of project execution
a CSV file will be created in resources directory within the project structure. Class CsvHandserImpl is responsible for it.
It's possible to use your custom directory to store the file using the overloaded method of this class.
#

#### Author
Viacheslav Bortnyk: https://github.com/VBortnyk
 
