package xword.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.UUID;

/**
 * Generate unique editIds that are friendly enough to put in URLs.
 *
 * http://stackoverflow.com/questions/772802/storing-uuid-as-base64-string/18057117#18057117
 */
public class IdGenerator {

    private static Base64 ENCODER = new Base64(true);

    /**
     * Create a unique, url-safe key.
     *
     * @return the generated key
     */
    public static String generate(){
        UUID uuid = UUID.randomUUID();
        byte[] uuidArray = IdGenerator.toByteArray(uuid);
        byte[] encodedArray = ENCODER.encode(uuidArray);
        String returnValue = new String(encodedArray);
        returnValue = StringUtils.removeEnd(returnValue, "\r\n");
        return returnValue;
    }

    /**
     * Decode a generated key back to UUID format.
     *
     * @param key a generated key
     * @return the original UUID
     */
    public static UUID decode(String key){
        UUID returnValue = null;
        if(StringUtils.isNotBlank(key)){
            byte[] decodedArray = ENCODER.decode(key);
            returnValue = IdGenerator.fromByteArray(decodedArray);
        }
        return returnValue;
    }

    private static byte[] toByteArray(UUID uuid) {
        byte[] byteArray = new byte[(Long.SIZE / Byte.SIZE) * 2];
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        LongBuffer longBuffer = buffer.asLongBuffer();
        longBuffer.put(new long[] { uuid.getMostSignificantBits(), uuid.getLeastSignificantBits() });
        return byteArray;
    }

    private static UUID fromByteArray(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        LongBuffer longBuffer = buffer.asLongBuffer();
        return new UUID(longBuffer.get(0), longBuffer.get(1));
    }
}
