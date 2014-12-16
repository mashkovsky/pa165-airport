Requirements
==============
Running Apache Derby DB named `pa165` on host:port `localhost:1527` with username:password `pa165:pa165`

Build
=============
Run from project root folder `$APP_ROOT` (where parent pom.xml is located):

`mvn clean install`


Run WebApp
=============
From `airport-ui` module start jetty server:

`cd $APP_ROOT/airport-ui`

`mvn jetty:run`

Run CLI application
====================
From `airport-cli` module run CLI application

`cd $APP_ROOT/airport-cli`

List plane entity:

`java -jar target/airport-cli-1.0-SNAPSHOT-jar-with-dependencies.jar -entity=plane -list`

Create entity:

`java -jar target/airport-cli-1.0-SNAPSHOT-jar-with-dependencies.jar -entity=plane -create="{\"name\":\"Boeing\",\"type\":\"B747\",\"capacity\":512}"`

