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

package com.io7m.jequality.validator;

/**
 * The result of validation.
 */

public enum ValidatorResult
{
  /**
   * Validation failed; {@code hashCode} checking was requested, but the {@code
   * hashCode} method was not defined in the same class as {@code equals}.
   */

  VALIDATION_ERROR_INCONSISTENT_HASHCODE,

  /**
   * Validation failed; annotations were required but none were included.
   */

  VALIDATION_ERROR_WANTED_ANNOTATIONS,

  /**
   * Validation failed; the class specified reference equality but actually
   * overrode {@code equals}.
   */

  VALIDATION_ERROR_WANTED_REFERENCE_EQUALITY,

  /**
   * Validation failed; the class specified structural equality but failed to
   * override {@code equals}.
   */

  VALIDATION_ERROR_WANTED_STRUCTURAL_EQUALITY,

  /**
   * Validation succeeded.
   */

  VALIDATION_OK
}
