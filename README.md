# terminal-os
A basic terminal based Operating System wrote in Kotlin

## CURRENT LAYOUT
kotlin-terminal-os/
├── build.gradle
├── gradle/
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
├── gradlew.bat
├── settings.gradle
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── Command.kt
│   │   │   ├── CommandHandler.kt
│   │   │   ├── InputDriver.kt
│   │   │   ├── Main.kt
│   │   │   ├── UserConfig.kt
│   │   │   ├── commands/
│   │   │   │   ├── GreetCommand.kt
│   │   │   │   ├── HelpCommand.kt
│   │   │   │   └── WebsiteCommand.kt
│   │   │   └── errors/
│   │   │       └── UnknownCommandException.kt
│   │   └── resources/
│   │       └── user.yml
│   └── test/
│       └── kotlin/
│           └── CommandHandlerTest.kt
└── userConfig.yml
