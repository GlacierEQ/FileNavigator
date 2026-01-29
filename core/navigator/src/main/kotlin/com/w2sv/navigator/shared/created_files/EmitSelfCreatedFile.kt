package com.w2sv.navigator.shared.created_files

internal interface EmitSelfCreatedFile {
    suspend operator fun invoke(value: SelfCreatedFileIdentifiers)
}
