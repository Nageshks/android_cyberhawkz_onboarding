package com.nageshempire.cybersecurityonboarding.presentation.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nageshempire.cybersecurityonboarding.data.AppConfiguration
import com.nageshempire.cybersecurityonboarding.databinding.ActivityLauncherBinding
import com.nageshempire.cybersecurityonboarding.presentation.home.HomeActivity
import com.nageshempire.cybersecurityonboarding.presentation.onboarding.OnboardingActivity
import com.nageshempire.cybersecurityonboarding.util.enableFullScreen
import kotlinx.coroutines.delay

class LauncherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableFullScreen()
        observeOnboarding()
    }

    private fun observeOnboarding() {
        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                AppConfiguration.getOnboardingEnabled().collect{
                    delay(1000)
                    launchHomeOrOnboarding(it)
                }
            }
        }
    }

    private fun launchHomeOrOnboarding(onboardingEnabled: Boolean) {
        val targetActivity = if (onboardingEnabled) OnboardingActivity::class.java
            else HomeActivity::class.java
        startActivity(Intent(this,targetActivity))
        finish()
    }
}