package fr.univ.lyon1.lpiem.ratus.ui.trick_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import fr.univ.lyon1.lpiem.ratus.MainActivity
import fr.univ.lyon1.lpiem.ratus.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URLDecoder


class TrickDetailFragment : Fragment() {

    private val viewModel by viewModel<TrickDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_trick_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("trickId")

        (activity as MainActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        if (id != null) {
            viewModel.getTrick(id)
        }

        val webView = view.findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()

        viewModel.trick.observe(viewLifecycleOwner) { trick ->
            webView.loadUrl(URLDecoder.decode(trick.content_url, "UTF-8"))
            (activity as MainActivity).supportActionBar?.apply {
                title = trick.title.replaceFirstChar { it.uppercase() }
            }

        }

    }
}