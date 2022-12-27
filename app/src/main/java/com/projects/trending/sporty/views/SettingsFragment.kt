package com.projects.trending.sporty.views
//
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.appcompat.app.AppCompatDelegate
//import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
//import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
//import androidx.fragment.app.Fragment
//import com.projects.trending.sporty.R
//import com.projects.trending.sporty.databinding.FragmentSettingsBinding
//import com.projects.trending.sporty.utils.PreferenceData.getSharedPreferences
//
//
//
//class SettingsFragment : Fragment() {
//
//    private lateinit var binding: FragmentSettingsBinding
//
//
////    private var btnToggleDark: Button? = null
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentSettingsBinding.inflate(layoutInflater)
//
//
//        val sharedPreferences: SharedPreferences = getSharedPreferences(
//            requireContext()
//        )
//        val editor = sharedPreferences.edit()
//        val isDarkModeOn = sharedPreferences.getBoolean("isDarkModeOn", false)
//
//        // When user reopens the app after applying dark/light mode
//        if (isDarkModeOn) {
//            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
//            binding.btnToggleDark.setText(getString(R.string.diableDarkMode))
//        } else {
//            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//            binding.btnToggleDark.setText(getString(R.string.enableDarkMode))
//        }
//
//        binding.btnToggleDark.setOnClickListener(
//            View.OnClickListener {
//                // When user taps the enable/disable dark mode button
//                if (isDarkModeOn) {
//                    // if dark mode is on it will turn it off
//                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
//                    // it will set isDarkModeOn boolean to false
//                    editor.putBoolean("isDarkModeOn", false)
//                    editor.apply()
//                    // change text of Button
//                    binding.btnToggleDark.setText("Enable Dark Mode")
//                } else {
//                    // if dark mode is off it will turn it on
//                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
//                    // it will set isDarkModeOn boolean to true
//                    editor.putBoolean("isDarkModeOn", true)
//                    editor.apply()
//                    // change text of Button
//                    binding.btnToggleDark.setText("Disable Dark Mode")
//                }
//            })
//
//
//        return binding.root
//    }
//
//
//}