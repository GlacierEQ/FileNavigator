package com.w2sv.navigator.shared.createdfiles

internal interface EmitSelfCreatedFile {
    suspend operator fun invoke(value: SelfCreatedFileIdentifiers)
}
