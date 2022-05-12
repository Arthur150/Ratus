package fr.univ.lyon1.lpiem.ratus.ui.fund_details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.univ.lyon1.lpiem.ratus.databinding.ViewHolderUserBinding
import fr.univ.lyon1.lpiem.ratus.model.User


class UserViewHolder private constructor(
    private val binding: ViewHolderUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): UserViewHolder {
            return UserViewHolder(
                ViewHolderUserBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(user: User) {
        with(binding){
            userName.text = user.username
            userImage.load(user.thumbnail)
        }
    }
}