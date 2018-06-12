# SimpleObjectToObjectMapper - WIP


## Usage
```java
IMapper mapper = new Mapper();
// or define default converter (by default is StandardConverter - WIP):
IMapper mapper = new Mapper(new MyConverter());

// Automatic converter:
// SOOM will try to automatically convert your source class to destination class.
DestinationObject dest = mapper.map(sourceObject).to(DestinationObject.class);

// Using specific converter
DestinationObject dest = mapper.map(sourceObject)
                               .usingConverter(new MyConverter())
                               .to(DestinationObject.class);
```
