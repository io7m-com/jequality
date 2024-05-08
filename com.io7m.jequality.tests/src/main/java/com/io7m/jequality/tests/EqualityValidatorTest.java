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

import com.io7m.jequality.validator.AnnotationRequirement;
import com.io7m.jequality.validator.EqualityValidator;
import com.io7m.jequality.validator.ValidatorResult;

@SuppressWarnings("static-method") public final class EqualityValidatorTest
{
  @Test public void testReferenceEquality()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        RefEquality.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testReferenceEqualityHashCode()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_INCONSISTENT_HASHCODE,
      EqualityValidator.validateClass(
        RefEqualityHashCodeInconsistent.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testReferenceEqualityHashCodeIgnored()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        RefEqualityHashCodeInconsistent.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        false));
  }

  @Test public void testReferenceEqualityNot()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_WANTED_REFERENCE_EQUALITY,
      EqualityValidator.validateClass(
        RefEqualityWrong.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testReferenceEqualitySubCorrect()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        RefEqualitySubCorrect.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testReferenceEqualitySubIncorrect()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_WANTED_STRUCTURAL_EQUALITY,
      EqualityValidator.validateClass(
        RefEqualitySubIncorrect.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testStructuralEquality()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        StructuralEquality.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testStructuralEqualityHashCode()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_INCONSISTENT_HASHCODE,
      EqualityValidator.validateClass(
        StructuralEqualityHashCodeInconsistent.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testStructuralEqualityHashCodeIgnored()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        StructuralEqualityHashCodeInconsistent.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        false));
  }

  @Test public void testStructuralEqualityNot()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_WANTED_STRUCTURAL_EQUALITY,
      EqualityValidator.validateClass(
        StructuralEqualityWrong.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testStructuralEqualitySuper()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        StructuralEqualitySuper.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }

  @Test public void testUnannotated()
  {
    Assert.assertEquals(ValidatorResult.VALIDATION_OK, EqualityValidator
      .validateClass(
        Unannotated.class,
        AnnotationRequirement.ANNOTATIONS_OPTIONAL,
        true));
  }

  @Test public void testUnannotatedWrong()
  {
    Assert.assertEquals(
      ValidatorResult.VALIDATION_ERROR_WANTED_ANNOTATIONS,
      EqualityValidator.validateClass(
        Unannotated.class,
        AnnotationRequirement.ANNOTATIONS_REQUIRED,
        true));
  }
}
