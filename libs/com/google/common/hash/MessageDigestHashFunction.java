package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class MessageDigestHashFunction extends AbstractStreamingHashFunction
  implements Serializable
{
  private final int bytes;
  private final MessageDigest prototype;
  private final boolean supportsClone;
  private final String toString;

  MessageDigestHashFunction(String paramString1, int paramInt, String paramString2)
  {
    this.toString = ((String)Preconditions.checkNotNull(paramString2));
    this.prototype = getMessageDigest(paramString1);
    int i = this.prototype.getDigestLength();
    if ((paramInt >= 4) && (paramInt <= i));
    for (boolean bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = Integer.valueOf(i);
      Preconditions.checkArgument(bool, "bytes (%s) must be >= 4 and < %s", arrayOfObject);
      this.bytes = paramInt;
      this.supportsClone = supportsClone();
      return;
    }
  }

  MessageDigestHashFunction(String paramString1, String paramString2)
  {
    this.prototype = getMessageDigest(paramString1);
    this.bytes = this.prototype.getDigestLength();
    this.toString = ((String)Preconditions.checkNotNull(paramString2));
    this.supportsClone = supportsClone();
  }

  private static MessageDigest getMessageDigest(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
      return localMessageDigest;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new AssertionError(localNoSuchAlgorithmException);
    }
  }

  private boolean supportsClone()
  {
    try
    {
      this.prototype.clone();
      return true;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
    }
    return false;
  }

  public int bits()
  {
    return 8 * this.bytes;
  }

  public Hasher newHasher()
  {
    if (this.supportsClone)
      try
      {
        MessageDigestHasher localMessageDigestHasher = new MessageDigestHasher((MessageDigest)this.prototype.clone(), this.bytes, null);
        return localMessageDigestHasher;
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
      }
    return new MessageDigestHasher(getMessageDigest(this.prototype.getAlgorithm()), this.bytes, null);
  }

  public String toString()
  {
    return this.toString;
  }

  Object writeReplace()
  {
    return new SerializedForm(this.prototype.getAlgorithm(), this.bytes, this.toString, null);
  }

  private static final class MessageDigestHasher extends AbstractByteHasher
  {
    private final int bytes;
    private final MessageDigest digest;
    private boolean done;

    private MessageDigestHasher(MessageDigest paramMessageDigest, int paramInt)
    {
      this.digest = paramMessageDigest;
      this.bytes = paramInt;
    }

    private void checkNotDone()
    {
      if (!this.done);
      for (boolean bool = true; ; bool = false)
      {
        Preconditions.checkState(bool, "Cannot use Hasher after calling #hash() on it");
        return;
      }
    }

    public HashCode hash()
    {
      this.done = true;
      if (this.bytes == this.digest.getDigestLength())
        return HashCodes.fromBytesNoCopy(this.digest.digest());
      byte[] arrayOfByte = new byte[this.bytes];
      System.arraycopy(this.digest.digest(), 0, arrayOfByte, 0, this.bytes);
      return HashCodes.fromBytesNoCopy(arrayOfByte);
    }

    protected void update(byte paramByte)
    {
      checkNotDone();
      this.digest.update(paramByte);
    }

    protected void update(byte[] paramArrayOfByte)
    {
      checkNotDone();
      this.digest.update(paramArrayOfByte);
    }

    protected void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      checkNotDone();
      this.digest.update(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  private static final class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    private final String algorithmName;
    private final int bytes;
    private final String toString;

    private SerializedForm(String paramString1, int paramInt, String paramString2)
    {
      this.algorithmName = paramString1;
      this.bytes = paramInt;
      this.toString = paramString2;
    }

    private Object readResolve()
    {
      return new MessageDigestHashFunction(this.algorithmName, this.bytes, this.toString);
    }
  }
}

/* Location:           /home/phil/workspace/labAssist/libs/GlassVoice-dex2jar.jar
 * Qualified Name:     com.google.common.hash.MessageDigestHashFunction
 * JD-Core Version:    0.6.2
 */