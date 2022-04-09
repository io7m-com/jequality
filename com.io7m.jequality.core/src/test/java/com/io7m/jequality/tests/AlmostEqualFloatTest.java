/*
 * Copyright © 2014 Mark Raynsford <code@io7m.com> https://www.io7m.com
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

import com.io7m.jequality.AlmostEqualFloat;
import com.io7m.jequality.AlmostEqualFloat.ContextRelative;
import com.io7m.jequality.validator.AnnotationRequirement;
import com.io7m.jequality.validator.EqualityValidator;
import com.io7m.jequality.validator.ValidatorResult;

public class AlmostEqualFloatTest
{
  private static final int TEST_GRANULARITY = 10000;

  private static final int TEST_ITERATIONS  = 1000;

  @SuppressWarnings("static-method") @Test public void testCloseRandom()
  {
    for (int index = 0; index < AlmostEqualFloatTest.TEST_ITERATIONS; ++index) {
      final float r = (float) Math.random();
      final float d = r / AlmostEqualFloatTest.TEST_GRANULARITY;
      float x = 0.0f;

      for (int k = 0; k < AlmostEqualFloatTest.TEST_GRANULARITY; ++k) {
        x += d;
      }

      System.out.println("r    : " + r);
      System.out.println("x    : " + x);

      final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
      cr.setMaxAbsoluteDifference(0.001f);
      cr.setMaxRelativeDifference(0.0001f);

      Assert.assertTrue(AlmostEqualFloat.almostEqual(cr, r, x));
    }
  }

  @SuppressWarnings("static-method") @Test public void testEquality()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        AlmostEqualFloat.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @SuppressWarnings("static-method") @Test public void testInfinities()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      cr,
      Float.POSITIVE_INFINITY,
      Float.POSITIVE_INFINITY));
    Assert.assertFalse(AlmostEqualFloat.almostEqual(
      cr,
      Float.POSITIVE_INFINITY,
      0.0f));
    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      cr,
      Float.NEGATIVE_INFINITY,
      Float.NEGATIVE_INFINITY));
    Assert.assertFalse(AlmostEqualFloat.almostEqual(
      cr,
      Float.NEGATIVE_INFINITY,
      0.0f));
  }

  @SuppressWarnings("static-method") @Test public void testMaxMax()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      cr,
      Float.MAX_VALUE,
      Float.MAX_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMaxMin()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertFalse(AlmostEqualFloat.almostEqual(
      cr,
      Float.MAX_VALUE,
      Float.MIN_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMinMax()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertFalse(AlmostEqualFloat.almostEqual(
      cr,
      Float.MIN_VALUE,
      Float.MAX_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testMinMin()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertTrue(AlmostEqualFloat.almostEqual(
      cr,
      Float.MIN_VALUE,
      Float.MIN_VALUE));
  }

  @SuppressWarnings("static-method") @Test public void testOne()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertTrue(AlmostEqualFloat.almostEqual(cr, 1.0f, 1.0f));
  }

  @SuppressWarnings("static-method") @Test public void testOneZero()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertFalse(AlmostEqualFloat.almostEqual(cr, 1.0f, 0.0f));
  }

  @SuppressWarnings("static-method") @Test public void testRelative()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.0f);
    cr.setMaxRelativeDifference(0.5f);

    final float x = 1.0f;
    final float y = 1.2f;
    final boolean e = AlmostEqualFloat.almostEqual(cr, x, y);

    Assert.assertTrue(e);
  }

  @SuppressWarnings("static-method") @Test public void testString()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    final String s0 = cr.toString();
    cr.setMaxAbsoluteDifference(1);
    final String s1 = cr.toString();
    cr.setMaxRelativeDifference(2);
    final String s2 = cr.toString();
    final ContextRelative cu = new AlmostEqualFloat.ContextRelative();
    final String s3 = cu.toString();

    Assert.assertFalse(s0.equals(s1));
    Assert.assertFalse(s0.equals(s2));
    Assert.assertFalse(s1.equals(s2));
    Assert.assertTrue(s3.equals(s0));
  }

  @SuppressWarnings("static-method") @Test public void testZero()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertTrue(AlmostEqualFloat.almostEqual(cr, 0.0f, 0.0f));
  }

  @SuppressWarnings("static-method") @Test public void testZeroOne()
  {
    final ContextRelative cr = new AlmostEqualFloat.ContextRelative();
    cr.setMaxAbsoluteDifference(0.00000000001f);
    cr.setMaxRelativeDifference(0.00000000001f);

    Assert.assertFalse(AlmostEqualFloat.almostEqual(cr, 0.0f, 1.0f));
  }
}
