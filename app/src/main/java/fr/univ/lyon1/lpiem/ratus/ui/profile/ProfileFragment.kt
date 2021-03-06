package fr.univ.lyon1.lpiem.ratus.ui.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
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

        (activity as ProfileActivity).supportActionBar?.apply {
            title = getString(R.string.profile)
            setDisplayHomeAsUpEnabled(true)
        }

        viewModel.getUser().observe(viewLifecycleOwner) { user ->
            view.findViewById<TextView>(R.id.username).text = user.username
            view.findViewById<TextView>(R.id.userUID).apply {
                text = user.uid
                setOnClickListener {
                    val clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clipData = ClipData.newPlainText("text", user.uid)
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(requireContext(), context.getString(R.string.text_copied_to_clipboard), Toast.LENGTH_LONG).show()
                }
            }
            view.findViewById<ImageView>(R.id.userThumbnail).load(user.thumbnail)

            view.findViewById<ImageView>(R.id.qr_codeCreator).setOnClickListener {
                val qrCodeView = ImageView(requireContext())

                val size = 900 //pixels
                val qrCodeContent = user.uid
                val bits = QRCodeWriter().encode(qrCodeContent, BarcodeFormat.QR_CODE, size, size)
                val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565).also {
                    for (x in 0 until size) {
                        for (y in 0 until size) {
                            it.setPixel(x, y, if (bits[x, y]) Color.BLACK else Color.WHITE)
                        }
                    }
                }

                qrCodeView.setImageBitmap(bitmap)
                val alertDialog = AlertDialog.Builder(requireContext())
                    .setView(qrCodeView)
                    .create()
                alertDialog.show()
            }
        }


        viewModel.loadUser()

        view.findViewById<Button>(R.id.disconnectButton).setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(
                Intent(requireContext(), LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }

        return view
    }
}