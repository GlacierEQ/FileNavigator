package com.w2sv.common.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

// TODO: KotlinUtils
fun <A, B> combineToPair(a: Flow<A>, b: Flow<B>): Flow<Pair<A, B>> =
    combine(a, b, ::Pair)
