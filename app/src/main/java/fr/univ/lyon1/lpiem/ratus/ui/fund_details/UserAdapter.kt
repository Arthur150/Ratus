package fr.univ.lyon1.lpiem.ratus.ui.fund_details

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.univ.lyon1.lpiem.ratus.model.User

class UserAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<UserViewHolder>() {

    companion object {
        private const val TAG = "TrickAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() = userList.size
}