package com.w2sv.navigator.shared.created_files

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal interface SelfCreatedFilesBinderModule {

    @Binds
    fun selfCreatedFiles(impl: SelfCreatedFilesImpl): SelfCreatedFiles

    @Binds
    fun emitSelfCreatedFile(impl: SelfCreatedFilesImpl): EmitSelfCreatedFile
}
