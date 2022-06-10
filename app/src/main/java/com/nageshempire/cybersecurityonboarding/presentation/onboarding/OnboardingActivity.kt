package com.nageshempire.cybersecurityonboarding.presentation.onboarding

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.nageshempire.cybersecurityonboarding.R
import com.nageshempire.cybersecurityonboarding.data.AppConfiguration
import com.nageshempire.cybersecurityonboarding.data.OnboardingDataSource.slides
import com.nageshempire.cybersecurityonboarding.databinding.ActivityOnboardingBinding
import com.nageshempire.cybersecurityonboarding.presentation.home.HomeActivity
import com.nageshempire.cybersecurityonboarding.util.enableFullScreen
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import kotlinx.coroutines.launch

class OnboardingActivity : AppCompatActivity() {

    private var _binding: ActivityOnboardingBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()
        setupOnboardingButton()
        setupOnboardingViewPager()
    }

    override fun onBackPressed() {
        val position = binding.viewPager.currentItem
        if (position == 0) {
            super.onBackPressed()
        } else {
            binding.viewPager.currentItem = position - 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupOnboardingButton() {
        binding.skipButton.setOnClickListener {
            completeOnboarding()
        }
        binding.nextButton.setOnClickListener {
            val position = binding.viewPager.currentItem
            if (position == slides.lastIndex) {
                completeOnboarding()
            } else {
                binding.viewPager.currentItem = position + 1
            }
        }
    }

    private fun setupOnboardingViewPager() {
        // Adapter
        binding.viewPager.adapter = OnboardingPagerAdapter(this)
        // Indicator
        binding.viewpagerIndicator.apply {
            setSliderColor(Color.LTGRAY, Color.CYAN)
            setSlideMode(IndicatorSlideMode.WORM)
            setIndicatorStyle(IndicatorStyle.ROUND_RECT)
            setupWithViewPager(binding.viewPager)
        }
        // Update Button Text on page change
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                updateButton(position)
            }
        })
    }

    // Utility
    private fun updateButton(position: Int) {
        val buttonText = if (position == slides.lastIndex) R.string.get_started
        else R.string.next
        binding.nextButton.text = getString(buttonText)
    }

    private fun completeOnboarding() {
        lifecycleScope.launch {
            AppConfiguration.disableOnboarding()
        }
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private class OnboardingPagerAdapter(activity: FragmentActivity) :
        FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = slides.size
        override fun createFragment(position: Int) =
            OnboardingFragment.newInstance(position)
    }

}