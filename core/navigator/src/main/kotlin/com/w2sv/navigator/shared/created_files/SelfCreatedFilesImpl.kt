package com.w2sv.navigator.shared.created_files

import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

@Singleton
internal class SelfCreatedFilesImpl @Inject constructor() :
    SelfCreatedFiles,
    EmitSelfCreatedFile {

    final override val flow: SharedFlow<SelfCreatedFileIdentifiers>
        field = MutableSharedFlow<SelfCreatedFileIdentifiers>(extraBufferCapacity = 16)

    override suspend fun invoke(value: SelfCreatedFileIdentifiers) {
        flow.emit(value)
    }
}
