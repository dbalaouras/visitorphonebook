Visitor Phonebook
====================================

Visitor Phonebook is a web application that that aims to provide quick and easy access on telephone numbers and website addresses of the most essential organisations and services of countries.
It targets travelers, foreign students but also locals that don't want to search for long to find essential contact information.

I started the project for educational purposes, focusing on the capabilities of a few technologies that interest me:
* [Spring Framework's](http://www.springframework.org/) technologies
  * Spring MVC
  * Spring DI
  * Spring Security
* The [JPA](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html) project
* Twitter's [Bootstrap](http://getbootstrap.com/) responsive framework

This version of the project implements a phonebook for Greece, but it should be easy to create similar web apps for other countries.

Finaly, the project comes with an Android application which is also open-sourced and accessible via a github [repository](https://github.com/dbalaouras/greece-phonebook-android).

## Dependencies

The project uses [Maven](http://maven.apache.org/) for managing it's dependencies as well as it's entire liefcycle (from test to build and then deploy).
Most dependencies are described in the pom.xml file and maven will take care of all the dependency downloads during the first build or test or the project.

Additionally, you will need to install a mysql connector on your application container:
* download the connector directly from [mysql.com](http://dev.mysql.com/downloads/connector/j)
* place the jar file (which will look like this: mysql-connector-java-5.1.24-bin.jar) in 

Although any web container should work fine, the project has been tested on [Tomcat 7.0](http://tomcat.apache.org/index.html).


## Database Setup

The project comes with a precompiled mysql database, formed in an sql script file. The file is located under the folder "project-assets".

After setting up the database (in UTF8), you will need to add a resource to the container's context:


  	  <Resource name="jdbc/PhoneBookSrc" auth="Container" type="javax.sql.DataSource"
  		username="<username>" password="<password>" driverClassName="com.mysql.jdbc.Driver"
  		url="jdbc:mysql://<host>/<database>?characterEncoding=UTF-8"
  		maxActive="8" />



## Setup the project in Eclipse

Althouth [STS](http://spring.io/tools) is the recommended IDE, you can use Eclipse without any issues.

1. Checkout or download the project to your local machine
2. Import project in Eclipse. You have two options: 
  1. Install the [M2Eclipse](http://m2eclipse.sonatype.org/) and use `File->Import->Existing Maven Projects`, or
  2. Run `mvn eclipse:eclipse` on your command line while being inside the project's folder. The command will create two eclipse files `.project` and `.classpath` and with those created you can now use `File->Import->Existing projects into workspace`
3. You are done

I would recommend to use a Maven plugin for Eclipse, or even better use [STS](http://spring.io/tools), the Spring's flavor of Eclipse.

## License

This software is licensed under the permissive [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html). Basically you are free to use and copy the source code if this project with very few restrictions. For a less vague description, go ahead and read [this FAQ](http://www.apache.org/foundation/license-faq.html#WhatDoesItMEAN) or [this description](http://www.tldrlegal.com/license/apache-license-2.0-%28apache-2.0%29).

    Copyright (C) 2013 Dimitris Balaouras

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## Contact me

Feel free to contact me sending your questions or suggestions. I will be happy to discuss every details you might think of.

Here's my contact details:

    Dimitris Balaouras
    Email: dbalaouras@gmail.com
    Tel. : +30 2410 2310 20
    Website: http://bytecode.gr
    Linked-in: http://www.linkedin.com/in/dbalaouras
    Twitter: https://twitter.com/dbalaouras

[1]: https://github.com/dbalaouras/visitorphonebook
[2]: http://actionbarsherlock.com/
[3]: http://www.youtube.com/watch?v=4GJ6yY1lNNY#t=119
[4]: http://bytecode.gr
