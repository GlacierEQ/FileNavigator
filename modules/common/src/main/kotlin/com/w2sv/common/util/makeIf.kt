package com.w2sv.common.util

/**
 * Executes [block] and returns its result only if [condition] is true,
 * otherwise returns null.
 *
 * Unlike [kotlin.takeIf], this function evaluates the condition *before*
 * creating the value. This makes it useful when the value is expensive to
 * compute or has side effects and should only be created conditionally.
 */
inline fun <T> makeIf(condition: Boolean, block: () -> T): T? =
    if (condition) block() else null
