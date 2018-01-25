[1]: ./src/main/java/org/aksw/fox/binding/java/Examples.java

### The code is out of date and needs an update.

fox-java
========

Java bindings for FOX - Federated Knowledge Extraction Framework


In [Examples.java][1] you can find an example.

```Java
IFoxApi fox = new FoxApi();

URL api = new URL("http://0.0.0.0:4444/fox");
fox.setApiURL(api);

fox.setTask(FoxParameter.TASK.NER);
fox.setOutputFormat(FoxParameter.OUTPUT.TURTLE);
fox.setLang(FoxParameter.LANG.EN);
fox.setInput("The philosopher and mathematician Leibniz was born in Leipzig.");
// fox.setLightVersion(FoxParameter.FOXLIGHT.ENStanford);

String response = fox.send();
```
### Maven
    <dependencies>
      <dependency>
        <groupId>com.github.renespeck</groupId>
        <artifactId>fox-java</artifactId>
        <version>78d7eb103e</version>
      </dependency>
    </dependencies>
    
    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>
