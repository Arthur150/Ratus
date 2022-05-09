package fr.univ.lyon1.lpiem.ratus.core.exception

class UserNotAlreadyRegisterException(
    override val message: String = "User isn't register, please create a document with is UID into Firestore"
) : Throwable()