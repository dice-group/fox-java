fox-java
========

Java bindings for FOX - Federated Knowledge Extraction Framework


[Here] (https://github.com/renespeck/fox-java/blob/master/src/main/java/org/aksw/fox/binding/java/Examples.java) you can find an example.

```Java
IFoxApi fox = new FoxApi();

//URL api = new URL("http://0.0.0.0:4444/api");
//fox.setApiURL(api);

fox.setTask(FoxParameter.TASK.NER);
fox.setOutputFormat(FoxParameter.OUTPUT.TURTLE);
fox.setLang(FoxParameter.LANG.EN);
fox.setInput("The philosopher and mathematician Leibniz was born in Leipzig.");
// fox.setLightVersion(FoxParameter.FOXLIGHT.ENStanford);

FoxResponse response = fox.send();

System.out.println(response.getInput());
System.out.println(response.getOutput());
System.out.println(response.getLog());
```
### Maven
    <dependencies>
      <dependency>
        <groupId>com.github.renespeck</groupId>
        <artifactId>fox-java</artifactId>
        <version>904cabeee3</version>
      </dependency>
    </dependencies>
    
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
