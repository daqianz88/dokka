# Module kotlinx-serialization-core
Core serialization API, serializers for standard library classes, and ready to use JSON
format implementation.

# Module kotlinx-serialization-cbor
Concise Binary Object Representation (CBOR) format implementation, as per [RFC 7049](https://tools.ietf.org/html/rfc7049). Located in separate `kotlinx-serialization-cbor` artifact.

# Module kotlinx-serialization-hocon
Allows deserialization of `Config` object from popular [lightbend/config](https://github.com/lightbend/config) library 
into Kotlin objects.
You can learn about "Human-Optimized Config Object Notation" or HOCON from library's [readme](https://github.com/lightbend/config#using-hocon-the-json-superset).

# Module kotlinx-serialization-properties
Allows converting arbitrary hierarchy of Kotlin classes to a flat key-value structure à la Java Properties.

# Module kotlinx-serialization-protobuf
Protocol buffers serialization format implementation, mostly complaint to [proto2](https://developers.google.com/protocol-buffers/docs/proto) specification. Located in separate `kotlinx-serialization-protobuf` artifact.

# Package kotlinx.serialization
Basic core concepts and annotations that set up serialization process.

# Package kotlinx.serialization.builtins
Serializers for standard Kotlin types, like Int, String, List, etc.

# Package kotlinx.serialization.descriptors
Basic concepts of serial description to programmatically describe the serial form for serializers 
in an introspectable manner.

# Package kotlinx.serialization.encoding
Basic concepts of data encoding and decoding of serialized data.

# Package kotlinx.serialization.modules
Classes that provides runtime mechanisms for resolving serializers, typically used during polymorphic serialization.

# Package kotlinx.serialization.hocon
HOCON serialization format implementation for converting Kotlin classes from and to [Lightbend config](https://github.com/lightbend/config).
Located in separate `kotlinx-serialization-hocon` artifact.

# Package kotlinx.serialization.json
JSON serialization format implementation, JSON tree data structures with builders for them,
and JSON-specific serializers.

# Package kotlinx.serialization.protobuf
Protocol buffers serialization format implementation, mostly complaint to [proto2](https://developers.google.com/protocol-buffers/docs/proto) specification. Located in separate `kotlinx-serialization-protobuf` artifact.

# Package kotlinx.serialization.properties
Properties serialization format implementation that represents the input data as a plain map of properties.
Located in separate `kotlinx-serialization-properties` artifact.

# Package kotlinx.serialization.cbor
Concise Binary Object Representation (CBOR) format implementation, as per [RFC 7049](https://tools.ietf.org/html/rfc7049). Located in separate `kotlinx-serialization-cbor` artifact.
