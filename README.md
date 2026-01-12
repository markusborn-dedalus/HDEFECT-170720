# OAS-Checker for broken Bearer-Authentication
This is a tool which helps to identify OAS-instances with broken Bearer-Authentication. It has to main entrypoints.
It is compiled for usage with Java 8 or newer.

## Internal checks
Run the main method in MainDedalusInternal to check all OAS-instances on DEV, QA and REL environemnts.Authentication

## Tool
Run the main method in Main with an argument `--oas-host=your.oas.host` to check a specific OAS-instance. You can
also build a jar file using `mvn clean package` and then run it using `java -jar target/oas-checker-<VERSION>.jar --oas-host=your.oas.host`.
The resulting jar can also be used as a tool on the client site.