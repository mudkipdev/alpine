package alpine.json.codec;

import java.util.UUID;

import static alpine.json.codec.PrimitiveCodecs.STRING;

interface StandardCodecs {
    Codec<UUID> UUID = STRING.map(java.util.UUID::fromString, java.util.UUID::toString);
}
