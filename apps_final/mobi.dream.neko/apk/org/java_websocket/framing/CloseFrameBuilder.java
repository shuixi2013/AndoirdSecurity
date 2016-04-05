package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.util.Charsetfunctions;

public class CloseFrameBuilder
  extends FramedataImpl1
  implements CloseFrame
{
  static final ByteBuffer emptybytebuffer = ByteBuffer.allocate(0);
  private int code;
  private String reason;
  
  public CloseFrameBuilder()
  {
    super(Framedata.Opcode.CLOSING);
    setFin(true);
  }
  
  public CloseFrameBuilder(int paramInt)
    throws InvalidDataException
  {
    super(Framedata.Opcode.CLOSING);
    setFin(true);
    setCodeAndMessage(paramInt, "");
  }
  
  public CloseFrameBuilder(int paramInt, String paramString)
    throws InvalidDataException
  {
    super(Framedata.Opcode.CLOSING);
    setFin(true);
    setCodeAndMessage(paramInt, paramString);
  }
  
  private void initCloseCode()
    throws InvalidFrameException
  {
    code = 1005;
    ByteBuffer localByteBuffer1 = super.getPayloadData();
    localByteBuffer1.mark();
    if (localByteBuffer1.remaining() >= 2)
    {
      ByteBuffer localByteBuffer2 = ByteBuffer.allocate(4);
      localByteBuffer2.position(2);
      localByteBuffer2.putShort(localByteBuffer1.getShort());
      localByteBuffer2.position(0);
      code = localByteBuffer2.getInt();
      if ((code == 1006) || (code == 1015) || (code == 1005) || (code > 4999) || (code < 1000)) {
        throw new InvalidFrameException("closecode must not be sent over the wire " + code);
      }
    }
    localByteBuffer1.reset();
  }
  
  private void initMessage()
    throws InvalidDataException
  {
    if (code == 1005)
    {
      reason = Charsetfunctions.stringUtf8(super.getPayloadData());
      return;
    }
    ByteBuffer localByteBuffer = super.getPayloadData();
    int i = localByteBuffer.position();
    try
    {
      localByteBuffer.position(localByteBuffer.position() + 2);
      reason = Charsetfunctions.stringUtf8(localByteBuffer);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      throw new InvalidFrameException(localIllegalArgumentException);
    }
    finally
    {
      localByteBuffer.position(i);
    }
  }
  
  private void setCodeAndMessage(int paramInt, String paramString)
    throws InvalidDataException
  {
    Object localObject = paramString;
    if (paramString == null) {
      localObject = "";
    }
    int i = paramInt;
    if (paramInt == 1015)
    {
      i = 1005;
      localObject = "";
    }
    if (i == 1005)
    {
      if (!((String)localObject).isEmpty()) {
        throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
      }
    }
    else
    {
      paramString = Charsetfunctions.utf8Bytes((String)localObject);
      localObject = ByteBuffer.allocate(4);
      ((ByteBuffer)localObject).putInt(i);
      ((ByteBuffer)localObject).position(2);
      ByteBuffer localByteBuffer = ByteBuffer.allocate(paramString.length + 2);
      localByteBuffer.put((ByteBuffer)localObject);
      localByteBuffer.put(paramString);
      localByteBuffer.rewind();
      setPayload(localByteBuffer);
    }
  }
  
  public int getCloseCode()
  {
    return code;
  }
  
  public String getMessage()
  {
    return reason;
  }
  
  public ByteBuffer getPayloadData()
  {
    if (code == 1005) {
      return emptybytebuffer;
    }
    return super.getPayloadData();
  }
  
  public void setPayload(ByteBuffer paramByteBuffer)
    throws InvalidDataException
  {
    super.setPayload(paramByteBuffer);
    initCloseCode();
    initMessage();
  }
  
  public String toString()
  {
    return super.toString() + "code: " + code;
  }
}

/* Location:
 * Qualified Name:     org.java_websocket.framing.CloseFrameBuilder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */