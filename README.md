# Hyperjava

This repository contains some Java exercise projects from the website [Hyperskill](https://hyperskill.org/). These small projects go from the
basics of Java to advanced concepts.

### Structure of this repository:

In order to keep organized and centralized, each project will be made into their own package and have their own Main class (some projects can be made using just a few files).
If a project is too big or requires many folders and files (Spring Boot for example), it will be made in a dedicated repository and the link for it will be provided.

### Tools:

- Java JDK 21
- Apache Maven
- Intellij Idea Community Edition

### Dependencies:

- Junit (for tests)

### How to run

On the terminal, just type the command below to package the whole application into
a jar file with all the dependencies.

```bash
mvn package assembly:single
```

The packaged file will be:

`target\hyperjava-1.0.0-jar-with-dependencies.jar`

You can rename this jar for a shorter name if you want.

The jar file has a `Main` class with an intro message with a list of the projects available and 
commands to run a specific project.

```bash
java -jar [name].jar 
```


## List of projects:

More information about what each project does and the objectives, click on the links below.

- com.asciimirror: ASCII Mirror (https://hyperskill.org/projects/260)
- com.bullsandcows: Bull and Cows (https://hyperskill.org/projects/53?track=12)
- com.chatbot: Simple Chat Bot (https://hyperskill.org/projects/113?track=12)
- com.chucknorriscipher: Chuck Norris Cipher Encoder (https://hyperskill.org/projects/293?track=9)
- com.cinemaroom: Cinema Room Manager (https://hyperskill.org/projects/133)
- com.introduction: Main class of the jar file with an intro message, not a project.
- com.coffeemachine: Coffee Machine Simulator (https://hyperskill.org/projects/33?track=12)
- com.lastpencil: Last Pencil (https://hyperskill.org/projects/341?track=12)
- com.numberconverter: Number Base Converter (https://hyperskill.org/projects/181)
- com.tictactoe: Simple Tic-Tac-Toe (https://hyperskill.org/projects/48?track=15)
- com.zookeeper: Zookeeper (https://hyperskill.org/projects/229?track=12)
