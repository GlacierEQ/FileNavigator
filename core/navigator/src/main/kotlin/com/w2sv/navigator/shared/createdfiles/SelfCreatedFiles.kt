package com.w2sv.navigator.shared.createdfiles

import kotlinx.coroutines.flow.SharedFlow

internal interface SelfCreatedFiles {
    val flow: SharedFlow<SelfCreatedFileIdentifiers>
}
