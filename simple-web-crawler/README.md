# Technical Interview Task

## Simple Site Crawl

### Prerequisition

- Install JDK 8 for OSX (https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
- Install Apache Maven (https://maven.apache.org/download.cgi)
  

### Build

```
mvn clean install
```
### Run

```
mvn test
```

### Content

Writing a crawler that opens up the “Shop By Department” dropdown menu on the amazon website, obtains a list of all department links and visits them to make sure that there are no dead links

### Description

This app writes files under the TEMP folder. Because of every computer has different TEMP paths on their own, the path we are writing currently will be stated on console