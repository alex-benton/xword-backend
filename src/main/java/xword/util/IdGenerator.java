package xword.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.UUID;

/**
 *
 * http://stackoverflow.com/questions/772802/storing-uuid-as-base64-string/18057117#18057117
 *
 * @author alex
 */
public class IdGenerator {

    private static Base64 ENCODER = new Base64(true);

    public static String generate(){
        UUID uuid = UUID.randomUUID();
        byte[] uuidArray = IdGenerator.toByteArray(uuid);
        byte[] encodedArray = ENCODER.encode(uuidArray);
        String returnValue = new String(encodedArray);
        returnValue = StringUtils.removeEnd(returnValue, "\r\n");
        return returnValue;
    }

    public static UUID decode(String key){
        UUID returnValue = null;
        if(StringUtils.isNotBlank(key)){
            // Convert base64 string to a byte array
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
