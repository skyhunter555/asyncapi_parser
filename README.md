# Service for parsing orders from file.
Library name: asyncapi-parser

Parsers can parse asyncapi from yml file and output result to console.
System events output to log file.
Simple implementation.

## Build
mvn clean install

## Example
java -jar asyncapi-parser-1.0.0.jar http-kafka.yml