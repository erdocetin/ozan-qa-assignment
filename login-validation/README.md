# Technical Interview Task

## Acceptance Criteria Implementation

### Prerequisition

- Install JDK 8 for OSX (https://docs.oracle.com/javase/8/docs/technotes/guides/install/mac_jdk.html)
- Install Apache Maven (https://maven.apache.org/download.cgi)


### Setup

- Open and Edit login.feature where login-validation>src>test>resources
- This account informations needs to be replaced with the "username" and "password" fields in the "login.feature".

 
### Build

```
mvn clean install
```
### Run

```
mvn test
```

### Content

Using Cucumber (Java) and Selenium/Webdriver, implementing tests on the Chrome browser

### Description

For running this test one valid and one invalid amazon account is required. This account informations needs to be replaced with the "username" and "password" fields in the "login.feature" file under the path qa-test>login-validation>src>test>resources.