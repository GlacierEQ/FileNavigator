package com.w2sv.navigator.shared.created_files

import kotlinx.coroutines.flow.SharedFlow

internal interface SelfCreatedFiles {
    val flow: SharedFlow<SelfCreatedFileIdentifiers>
}
