package com.google.zxing.datamatrix.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GenericGF;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GenericGF.DATA_MATRIX_FIELD_256);
  
  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
    throws ChecksumException
  {
    int j = paramArrayOfByte.length;
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      paramArrayOfByte[i] &= 0xFF;
      i += 1;
    }
    i = paramArrayOfByte.length;
    try
    {
      rsDecoder.decode(arrayOfInt, i - paramInt);
      i = 0;
      while (i < paramInt)
      {
        paramArrayOfByte[i] = ((byte)arrayOfInt[i]);
        i += 1;
      }
      return;
    }
    catch (ReedSolomonException paramArrayOfByte)
    {
      throw ChecksumException.getChecksumInstance();
    }
  }
  
  public DecoderResult decode(BitMatrix paramBitMatrix)
    throws FormatException, ChecksumException
  {
    paramBitMatrix = new BitMatrixParser(paramBitMatrix);
    Object localObject1 = paramBitMatrix.getVersion();
    paramBitMatrix = DataBlock.getDataBlocks(paramBitMatrix.readCodewords(), (Version)localObject1);
    int k = paramBitMatrix.length;
    int j = 0;
    int m = paramBitMatrix.length;
    int i = 0;
    while (i < m)
    {
      j += paramBitMatrix[i].getNumDataCodewords();
      i += 1;
    }
    localObject1 = new byte[j];
    i = 0;
    while (i < k)
    {
      Object localObject2 = paramBitMatrix[i];
      byte[] arrayOfByte = ((DataBlock)localObject2).getCodewords();
      m = ((DataBlock)localObject2).getNumDataCodewords();
      correctErrors(arrayOfByte, m);
      j = 0;
      while (j < m)
      {
        localObject1[(j * k + i)] = arrayOfByte[j];
        j += 1;
      }
      i += 1;
    }
    return DecodedBitStreamParser.decode((byte[])localObject1);
  }
  
  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws FormatException, ChecksumException
  {
    int k = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(k);
    int i = 0;
    while (i < k)
    {
      int j = 0;
      while (j < k)
      {
        if (paramArrayOfBoolean[i][j] != 0) {
          localBitMatrix.set(j, i);
        }
        j += 1;
      }
      i += 1;
    }
    return decode(localBitMatrix);
  }
}

/* Location:
 * Qualified Name:     com.google.zxing.datamatrix.decoder.Decoder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */