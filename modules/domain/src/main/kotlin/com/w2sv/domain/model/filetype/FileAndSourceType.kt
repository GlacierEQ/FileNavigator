package com.w2sv.domain.model.filetype

import android.content.Context
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.w2sv.modules.common.R
import kotlin.collections.listOf
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class FileAndSourceType(val fileType: FileType, val sourceType: SourceType) : Parcelable {

    @IgnoredOnParcel
    @get:DrawableRes
    val iconRes: Int by lazy {
        when {
            sourceType in listOf(SourceType.Screenshot, SourceType.Camera, SourceType.Recording) -> sourceType.iconRes
            else -> fileType.iconRes
        }
    }

    /**
     * @return
     * - Gif -> 'GIF'
     * - SourceType.Camera -> 'Photo' or 'Video"
     * - SourceType in (Screenshot, Recording) -> sourceTypeLabel
     * - else -> fileTypeLabel
     */
    fun label(context: Context, isGif: Boolean): String =
        when {
            isGif -> context.getString(R.string.gif)
            sourceType == SourceType.Camera -> context.getString(
                when (fileType.wrappedPresetTypeOrNull) {
                    PresetFileType.Image -> R.string.photo
                    PresetFileType.Video -> R.string.video
                    else -> error(
                        "wrapped file type should be PresetFileType.Image or PresetFileType.Video but was ${fileType.wrappedPresetTypeOrNull}"
                    )
                }
            )

            sourceType in listOf(SourceType.Screenshot, SourceType.Recording) -> context.getString(
                sourceType.labelRes
            )

            else -> fileType.label(context)
        }
}
