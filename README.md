# GUI script editor and runner

This is a simple app that allows you to write discardable scripts and run them built using Compose Multiplatform.
It implements all required and optional functionality required by the task.

It currently only supports running scripts written in Kotlin.

It supports basic syntax (really just keyword) highlighting and jumping from output errors to source code.

While I tried to make the UI look decent, I am neither a designer, nor great at UI design, so please don't hold it
against me.

The code is meant to be kind of modular, so that adding support for other languages would be relatively easy.
Each part of the codebase has an interface and implementation(s). (Not counting UI code)

The code should run on macOS (I don't see why it would not, but I cannot test it), but if it doesn't, you can check out
the [web version](https://compose.djues3.com).
(The code for the web part is in the `js` branch) <br>
The web version uses JS as the scripting language of choice and it doesn't support jumping from errors to source code.

## Prerequisites
The project assumes JDK 21, but it might work with other versions.
`kotlinc` is required and it must be in the `PATH`.

---

This is a Kotlin Multiplatform project targeting Desktop (JVM).

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
      If you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
      folder is the appropriate location.

### Build and Run Desktop (JVM) Application

To build and run the development version of the desktop app, use the run configuration from the run widget
in your IDE’s toolbar or run it directly from the terminal:

- on macOS/Linux
  ```shell
  ./gradlew :composeApp:run
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:run
  ```

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…