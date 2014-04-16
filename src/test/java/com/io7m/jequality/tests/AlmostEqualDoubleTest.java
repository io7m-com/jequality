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

package com.io7m.jequality.tests;

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jequality.AlmostEqualDouble;
import com.io7m.jequality.AlmostEqualDouble.ContextRelative;

public class AlmostEqualDoubleTest
{
  private static final int TEST_ITERATIONS  = 1000;
  private static final int TEST_GRANULARITY = 1000000;

  @SuppressWarnings("static-method") @Test public void testCloseRandom()
  {
    for (int index = 0; index < AlmostEqualDoubleTest.TEST_ITERATIONS; ++index) {
      final double r = Math.random();
      final double d = r / AlmostEqualDoubleTest.TEST_GRANULARITY;
      double x = 0.0;

      for (int k = 0; k < AlmostEqualDoubleTest.TEST_GRANULARITY; ++k) {
        x += d;
      }

      System.out.println("r    : " + r);
      System.out.println("x    : " + x);

      final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
      cr.setMaxAbsoluteDifference(0.0000000001);
      cr.setMaxRelativeDifference(0.00000000001);

      Assert.assertTrue(AlmostEqualDouble.almostEqual(cr, r, x));
    }
  }

  @SuppressWarnings("static-method") @Test public void testInfinities()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();

    Assert.assertTrue(AlmostEqualDouble.almostEqual(
      cr,
      Double.POSITIVE_INFINITY,
      Double.POSITIVE_INFINITY));
    Assert.assertFalse(AlmostEqualDouble.almostEqual(
      cr,
      Double.POSITIVE_INFINITY,
      0.0f));
    Assert.assertTrue(AlmostEqualDouble.almostEqual(
      cr,
      Double.NEGATIVE_INFINITY,
      Double.NEGATIVE_INFINITY));
    Assert.assertFalse(AlmostEqualDouble.almostEqual(
      cr,
      Double.NEGATIVE_INFINITY,
      0.0f));
  }

  @SuppressWarnings("static-method") @Test public void testMaxMax()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertTrue(AlmostEqualDouble.almostEqual(
      cr,
      Double.MAX_VALUE,
      Double.MAX_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMaxMin()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertFalse(AlmostEqualDouble.almostEqual(
      cr,
      Double.MAX_VALUE,
      Double.MIN_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMinMax()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertFalse(AlmostEqualDouble.almostEqual(
      cr,
      Double.MIN_VALUE,
      Double.MAX_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMinMin()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertTrue(AlmostEqualDouble.almostEqual(
      cr,
      Double.MIN_VALUE,
      Double.MIN_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testOne()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertTrue(AlmostEqualDouble.almostEqual(cr, 1.0, 1.0));
  }

  @SuppressWarnings("static-method") @Test public void testOneZero()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertFalse(AlmostEqualDouble.almostEqual(cr, 1.0, 0.0));
  }

  @SuppressWarnings("static-method") @Test public void testRelative()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.0);
    cr.setMaxRelativeDifference(0.5);

    final double x = 1.0;
    final double y = 1.2;
    final boolean e = AlmostEqualDouble.almostEqual(cr, x, y);

    Assert.assertTrue(e);
  }

  @SuppressWarnings("static-method") @Test public void testString()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    final String s0 = cr.toString();
    cr.setMaxAbsoluteDifference(1);
    final String s1 = cr.toString();
    cr.setMaxRelativeDifference(2);
    final String s2 = cr.toString();
    final ContextRelative cu = new AlmostEqualDouble.ContextRelative();
    final String s3 = cu.toString();

    Assert.assertFalse(s0.equals(s1));
    Assert.assertFalse(s0.equals(s2));
    Assert.assertFalse(s1.equals(s2));
    Assert.assertTrue(s3.equals(s0));
  }

  @SuppressWarnings("static-method") @Test public void testZero()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertTrue(AlmostEqualDouble.almostEqual(cr, 0.0, 0.0));
  }

  @SuppressWarnings("static-method") @Test public void testZeroOne()
  {
    final ContextRelative cr = new AlmostEqualDouble.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001);
    cr.setMaxRelativeDifference(0.00000000001);

    Assert.assertFalse(AlmostEqualDouble.almostEqual(cr, 0.0, 1.0));
  }
}
