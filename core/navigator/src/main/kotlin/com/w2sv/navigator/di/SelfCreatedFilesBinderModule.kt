package com.w2sv.navigator.di

import com.w2sv.navigator.shared.createdfiles.EmitSelfCreatedFile
import com.w2sv.navigator.shared.createdfiles.SelfCreatedFiles
import com.w2sv.navigator.shared.createdfiles.SelfCreatedFilesImpl
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
