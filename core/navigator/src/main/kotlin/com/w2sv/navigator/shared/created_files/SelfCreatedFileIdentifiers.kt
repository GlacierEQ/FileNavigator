package com.w2sv.navigator.shared.created_files

import com.anggrayudi.storage.media.MediaType
import com.w2sv.common.uri.MediaId

/**
 * Used for blacklisting of created files during moving.
 */
internal data class SelfCreatedFileIdentifiers(val mediaId: MediaId, val mediaType: MediaType)
