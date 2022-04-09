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

package com.io7m.jequality.validator;

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.jequality.annotations.EqualityStructural;
import com.io7m.junreachable.UnreachableCodeException;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * <p> A validator that examines a class and tries to determine whether the
 * class uses reference equality or structural equality. </p> <p> A class is
 * assumed to use reference equality if neither it nor any of its supertypes
 * overrides the {@code equals} method provided by {@link Object}. </p> <p> A
 * class is assumed to use structural equality if it overrides the {@code
 * equals} method provided by {@link Object}. </p> <p> The {@code equals} method
 * of a class is assumed to be <i>consistent</i> with the {@code hashCode}
 * method if they are both defined in the same class. Checking for true
 * consistency is obviously undecidable. </p> <p> A class is <i>valid</i> if it
 * uses the correct type of equality relative to its annotations ({@link
 * EqualityReference}, {@link EqualityStructural}). </p>
 */

public final class EqualityValidator
{
  private EqualityValidator()
  {
    throw new UnreachableCodeException();
  }

  /**
   * Validate the given class according to the annotation requirements.
   *
   * @param c              The class
   * @param requirement    The annotation requirements
   * @param check_hashcode {@code true} if {@code hashCode} methods should be
   *                       checked for consistency
   *
   * @return {@code true} if the class is valid according to its annotations
   */

  public static ValidatorResult validateClass(
    final Class<?> c,
    final AnnotationRequirement requirement,
    final boolean check_hashcode)
  {
    Objects.requireNonNull(c, "Class");
    Objects.requireNonNull(requirement, "Requirement");

    final EqualityReference er = c.getAnnotation(EqualityReference.class);
    if (er != null) {
      return validateClassReferenceEquality(c, check_hashcode);
    }

    final EqualityStructural es = c.getAnnotation(EqualityStructural.class);
    if (es != null) {
      return validateClassStructuralEquality(c, check_hashcode);
    }

    if (requirement == AnnotationRequirement.ANNOTATIONS_REQUIRED) {
      return ValidatorResult.VALIDATION_ERROR_WANTED_ANNOTATIONS;
    }

    return ValidatorResult.VALIDATION_OK;
  }

  private static ValidatorResult validateClassReferenceEquality(
    final Class<?> c,
    final boolean check_hashcode)
  {
    boolean equals_ok = false;
    boolean hashcode_ok = false;

    final Method[] ms = c.getMethods();
    for (final Method m : ms) {
      if (Objects.equals("equals", m.getName())) {
        if (Objects.equals(m.getDeclaringClass(), Object.class)) {
          equals_ok = true;
        }
      }
      if (Objects.equals("hashCode", m.getName())) {
        if (check_hashcode) {
          if (Objects.equals(m.getDeclaringClass(), Object.class)) {
            hashcode_ok = true;
          }
        }
      }
    }

    if (!equals_ok) {
      return ValidatorResult.VALIDATION_ERROR_WANTED_REFERENCE_EQUALITY;
    }

    if (check_hashcode) {
      if (!hashcode_ok) {
        return ValidatorResult.VALIDATION_ERROR_INCONSISTENT_HASHCODE;
      }
    }

    return ValidatorResult.VALIDATION_OK;
  }

  private static ValidatorResult validateClassStructuralEquality(
    final Class<?> c,
    final boolean check_hashcode)
  {
    boolean equals_ok = false;
    boolean hashcode_ok = false;

    final Method[] ms = c.getMethods();
    for (final Method m : ms) {
      if (Objects.equals("equals", m.getName())) {
        if (!Objects.equals(m.getDeclaringClass(), Object.class)) {
          equals_ok = true;
        }
      }
      if (Objects.equals("hashCode", m.getName())) {
        if (check_hashcode) {
          if (!Objects.equals(m.getDeclaringClass(), Object.class)) {
            hashcode_ok = true;
          }
        }
      }
    }

    if (!equals_ok) {
      return ValidatorResult.VALIDATION_ERROR_WANTED_STRUCTURAL_EQUALITY;
    }

    if (check_hashcode) {
      if (!hashcode_ok) {
        return ValidatorResult.VALIDATION_ERROR_INCONSISTENT_HASHCODE;
      }
    }

    return ValidatorResult.VALIDATION_OK;
  }
}
