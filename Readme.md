Create a ReadMe meta data file for the project. It should include an overview on your project, detailed setup & prerequisites instructions. Keep in mind that it needs to be able to run on a different host and environment than your own. 
You can also use this file to share your decision making process, why you chose to do X over Y, and so on.

### Prerequisites: ###
- Java 11
- Gradle https://gradle.org/install/

### Run: ###
```javascript
gradle test
```

### Test reports: ###
```javascript
build/reports/tests/test/index.html
```

### Test logs: ###
```javascript
logs/tests.log
```

### Multi browsers: ###
Change property
```javascript
systemProperty "webdriver.browser", "firefox"
```
OR
```javascript
systemProperty "webdriver.browser", "chrome"
```
