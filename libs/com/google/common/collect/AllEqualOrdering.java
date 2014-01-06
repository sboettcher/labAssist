package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.List;
import javax.annotation.Nullable;

@GwtCompatible(serializable=true)
final class AllEqualOrdering extends Ordering<Object>
  implements Serializable
{
  static final AllEqualOrdering INSTANCE = new AllEqualOrdering();
  private static final long serialVersionUID;

  private Object readResolve()
  {
    return INSTANCE;
  }

  public int compare(@Nullable Object paramObject1, @Nullable Object paramObject2)
  {
    return 0;
  }

  public <E> ImmutableList<E> immutableSortedCopy(Iterable<E> paramIterable)
  {
    return ImmutableList.copyOf(paramIterable);
  }

  public <S> Ordering<S> reverse()
  {
    return this;
  }

  public <E> List<E> sortedCopy(Iterable<E> paramIterable)
  {
    return Lists.newArrayList(paramIterable);
  }

  public String toString()
  {
    return "Ordering.allEqual()";
  }
}

/* Location:           /home/phil/workspace/labAssist/libs/GlassVoice-dex2jar.jar
 * Qualified Name:     com.google.common.collect.AllEqualOrdering
 * JD-Core Version:    0.6.2
 */