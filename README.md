[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.walleyy/xml_duplicate_validator/badge.svg?style=plastic)](https://maven-badges.herokuapp.com/maven-central/io.github.walleyy/xml_duplicate_validator)

# xml_duplicate_validator

### Library which aims to validate XML received from API.
Contains one method which receive the xml and a new instance of class:

### Usage
simply call findDuplicate method and handle the exception returned
```
 findDuplicate(xml, new POJO());
 ......
 xml param => XML string to be validated
 new POJO() param => a class that will be used in mapping back XML
```


>[!WARNING]
Exception list thrown.

```agsl
DuplicatedXmlField Exception :  When duplicates are found.
ClassNotFound Exception : When you pass a class param thats not instantiated.
IncorrectXml Exception : When you pass incorrect XML string.
```

