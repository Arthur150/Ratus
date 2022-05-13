package fr.univ.lyon1.lpiem.ratus.core.exception

class UserNotFoundException(
    val uid: String,
    override val message: String = "User $uid did not exists"
) : Throwable()