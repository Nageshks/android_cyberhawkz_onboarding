package com.nageshempire.cybersecurityonboarding.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nageshempire.cybersecurityonboarding.data.OnboardingDataSource.slides
import com.nageshempire.cybersecurityonboarding.databinding.FragmentOnboardingBinding
import com.nageshempire.cybersecurityonboarding.domain.OnboardingSlideContent


class OnboardingFragment : Fragment() {

    private lateinit var content: OnboardingSlideContent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            content = slides[it.getInt(ARG_POSITION)]
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentOnboardingBinding.inflate(inflater, container, false).apply {
            title.text = getString(content.titleRes)
            description.text = getString(content.descriptionRes)
            illustration.setImageResource(content.imageRes)
        }.root
    }

    companion object {
        private const val ARG_POSITION = "Position"

        @JvmStatic
        fun newInstance(position: Int) = OnboardingFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }
}