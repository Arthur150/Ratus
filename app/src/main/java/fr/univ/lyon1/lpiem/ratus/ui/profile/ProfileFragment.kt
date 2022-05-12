package fr.univ.lyon1.lpiem.ratus.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import coil.load
import com.google.firebase.auth.FirebaseAuth
import fr.univ.lyon1.lpiem.ratus.LoginActivity
import fr.univ.lyon1.lpiem.ratus.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val viewModel by viewModel<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        viewModel.user.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.username).text = it.username
            view.findViewById<TextView>(R.id.userUID).text = it.uid
            view.findViewById<ImageView>(R.id.userThumbnail).load(it.thumbnail)
        }

        viewModel.getUser()

        view.findViewById<Button>(R.id.disconnectButton).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
        }

        return view
    }
}