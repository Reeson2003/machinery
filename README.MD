### BUILD ###  
`./gradlew clean build cloverGenerateReport -Pintegration -i`

### DEPENDENCY ###  
```groovy
repositories {
    maven {
        url 'https://dl.bintray.com/reeson2003/maven/'
    }
}

dependencies {
    compile 'ru.reeson2003:machinery:1.0.0'
}
```