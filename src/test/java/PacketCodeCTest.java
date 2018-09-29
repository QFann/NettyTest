import com.qf.netty.JSONSerializer;
import com.qf.netty.LoginRequestPacket;
import com.qf.netty.Serializer;
import org.junit.Test;

/**
 * Created by qifan on 2018/9/27.
 */
public class PacketCodeCTest {

    @Test
    public void encode(){
        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion(((byte) 1));
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUsername("qfann");
        loginRequestPacket.setPassword("123456");


//        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(ByteBufAllocator.DEFAULT.ioBuffer().alloc(),loginRequestPacket);
//        Packet decodedPacket = PacketCodeC.INSTANCE.decode(byteBuf);

//        Assert.assertArrayEquals(serializer.serializer(loginRequestPacket), serializer.serializer(decodedPacket));
    }

}
