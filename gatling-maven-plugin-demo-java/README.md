Gatling Java API Udemy Course Code
============================================

* [Gatling Project to CLone](https://github.com/gatling/gatling-maven-plugin-demo-java)

## Run the Simulation locally for debugging Optional

The open-source version of Gatling allows you to run simulations locally, 
generating load from your computer. Running a new or modified simulation 
locally is often useful to ensure it works before launching it on 
Gatling Enterprise Cloud. Using the Java SDK, you can launch your test 
with the following command in the project root directory:

Linux/MacOS 

```bash
./mvnw gatling:test
```

Windows

```cmd
mvnw.cmd gatling:test
```


## Api url for tests

https://www.videogamedb.uk/swagger-ui/index.html#/api-video-games-controller