/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jequality;

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.junreachable.UnreachableCodeException;

/**
 * <p>
 * Better floating point comparisons.
 * </p>
 * <p>
 * See <a href=
 * "http://randomascii.wordpress.com/2012/02/25/comparing-floating-point-numbers-2012-edition"
 * >Comparing floating point numbers 2012</a>.
 * </p>
 */

@EqualityReference public final class AlmostEqualFloat
{
  /**
   * The necessary context for floating point comparisons.
   */

  public static final class ContextRelative
  {
    private float max_absolute_diff;
    private float max_relative_diff;

    /**
     * Construct a new equality context.
     */

    public ContextRelative()
    {
      this.max_absolute_diff = 0.0f;
      this.max_relative_diff = 0.0f;
    }

    /**
     * @return The current maximum absolute difference
     */

    public float getMaxAbsoluteDifference()
    {
      return this.max_absolute_diff;
    }

    /**
     * @return The current maximum relative difference
     */

    public float getMaxRelativeDifference()
    {
      return this.max_relative_diff;
    }

    /**
     * Set the current maximum absolute difference
     * 
     * @param max
     *          The required difference
     */

    public void setMaxAbsoluteDifference(
      final float max)
    {
      this.max_absolute_diff = max;
    }

    /**
     * Set the current maximum relative difference
     * 
     * @param max
     *          The required difference
     */

    public void setMaxRelativeDifference(
      final float max)
    {
      this.max_relative_diff = max;
    }

    @Override public String toString()
    {
      final StringBuilder builder = new StringBuilder();
      builder.append("[ContextRelative [Absolute ");
      builder.append(this.max_absolute_diff);
      builder.append("] [Relative ");
      builder.append(this.max_relative_diff);
      builder.append("]]");
      final String text = builder.toString();
      assert text != null;
      return text;
    }
  }

  /**
   * <p>
   * Compare the floating point numbers <code>x</code> and <code>y</code>
   * using the context <code>context</code>.
   * </p>
   * 
   * <p>
   * Essentially, given <code>diff = abs(x - y)</code>, if
   * <code>diff &lt;= context.getMaxRelativeDifference() * max(abs(x), abs(y))</code>
   * , then <code>x</code> and <code>y</code> can be considered to be close
   * together.
   * </p>
   * 
   * <p>
   * See the article mentioned at the top of this article for suggested
   * absolute and relative epsilon values: There are no values that work well
   * for all possible inputs.
   * </p>
   * 
   * @param context
   *          The equality context
   * @param x
   *          The left parameter
   * @param y
   *          The right parameter
   * @return <code>true</code> if <code>x</code> and <code>y</code> are almost
   *         equal
   */

  public static boolean almostEqual(
    final ContextRelative context,
    final float x,
    final float y)
  {
    if (x == Float.POSITIVE_INFINITY) {
      if (y == Float.POSITIVE_INFINITY) {
        return true;
      }
    }
    if (x == Float.NEGATIVE_INFINITY) {
      if (y == Float.NEGATIVE_INFINITY) {
        return true;
      }
    }

    final float diff = Math.abs(x - y);
    if (diff <= context.getMaxAbsoluteDifference()) {
      return true;
    }

    final float ax = Math.abs(x);
    final float ay = Math.abs(y);
    final float m = Math.max(ax, ay);
    final float k = m * context.getMaxRelativeDifference();

    if (diff <= k) {
      return true;
    }

    return false;
  }

  private AlmostEqualFloat()
  {
    throw new UnreachableCodeException();
  }
}
