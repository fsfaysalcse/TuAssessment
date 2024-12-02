package me.fsfaysalcse.tu.ui.util
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getCurrentDateTime(): String {
    val currentMoment = Clock.System.now()
    val dateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    return dateTime.toString() // Format: YYYY-MM-DDTHH:MM:SS
}