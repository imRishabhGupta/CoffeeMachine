# CoffeeMachine

## Prerequisites
Please have `java` and `gradle` installed.

## How to run project

It is simple gradle project, which can be run using command -

`./gradlew clean build`

This command will also run tests.

## How to run tests

Please find the function integration tests in CoffeeMachineApplicationTest file in tst folder.

Input files are present in resources folder. In order to use your custom input,
you can either edit one of the existing files or create new files.


### Important Note

**Since there is multi-threading involved, therefore I'd advise you to run test individually else you will see distorted output on console.**