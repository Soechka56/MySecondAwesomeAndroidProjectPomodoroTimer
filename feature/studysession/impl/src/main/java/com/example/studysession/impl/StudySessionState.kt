package com.example.studysession.impl

data class StudySessionState(
    val timerText: String = "02:34:10",
    val timerProgress: Float = 0.2f, // data only for test
    val friends: List<FriendStudyState> = listOf(
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "11:00:00", StudySessionColor.Orange), // The design shows a salmon/orange color
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:10:03", StudySessionColor.Green),
        FriendStudyState("angelina", "00:23:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "6:14:23", StudySessionColor.Yellow),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green),
        FriendStudyState("angelina", "00:00:00", StudySessionColor.Green)
    )
)

data class FriendStudyState(
    val name: String,
    val time: String,
    val backgroundColor: StudySessionColor
)

enum class StudySessionColor {
    Green, Orange, Yellow
}
