package com.w2sv.navigator.domain.moving

import android.content.Context
import com.w2sv.common.util.makeIf
import com.w2sv.navigator.domain.notifications.CancelNotificationEvent

sealed interface MoveOperationSummary {
    val operation: MoveOperation?
    val result: MoveResult
    val cancelNavigateFileNotificationEvent: CancelNotificationEvent?
    private val isPartOfBatch
        get() = operation?.isPartOfBatch == true

    fun makeFeedback(context: Context): MoveOperationFeedback? =
        makeIf(!isPartOfBatch) { result.makeFeedback(context, operation) }

    /**
     * Summary of a move operation that has been cancelled before the [operation] has been fully assembled.
     */
    data class EarlyCancellation(override val result: MoveResult) : MoveOperationSummary {
        override val operation: MoveOperation? = null
        override val cancelNavigateFileNotificationEvent: CancelNotificationEvent? = null
    }

    data class WithOperation(override val result: MoveResult, override val operation: MoveOperation) : MoveOperationSummary {
        override val cancelNavigateFileNotificationEvent: CancelNotificationEvent?
            get() = operation.destinationSelectionManner.cancelNotificationEvent.takeIf { result.cancelNotification }
    }

    companion object {
        operator fun invoke(result: MoveResult, operation: MoveOperation? = null): MoveOperationSummary =
            operation?.let { WithOperation(result, it) } ?: EarlyCancellation(result)
    }
}
