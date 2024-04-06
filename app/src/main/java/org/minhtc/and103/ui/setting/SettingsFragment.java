package org.minhtc.and103.ui.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import org.minhtc.and103.R;
import org.minhtc.and103.ui.login.LoginActivity;

public class SettingsFragment extends PreferenceFragmentCompat {
    private static final String TAG = "SettingsFragment";
    private SharedPreferences sharedPreferences;
    private Preference loginLogoutPreference;
    private Preference accountPreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext());

        // Find the login/logout preference
        loginLogoutPreference = findPreference("login_logout");
        accountPreference = findPreference("account");
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        String username = sharedPreferences.getString("username", "");
        String userId = sharedPreferences.getString("userId", "");
        Log.d(TAG, "onCreatePreferences: " + isLoggedIn);
        if (isLoggedIn) {
            loginLogoutPreference.setTitle(getString(R.string.logout));
            loginLogoutPreference.setSummary(getString(R.string.logout_summary));
            accountPreference.setTitle(userId);
            accountPreference.setSummary(username);
        } else {
            loginLogoutPreference.setTitle(getString(R.string.login));
            loginLogoutPreference.setSummary(getString(R.string.login_summary));
            accountPreference.setTitle(null);
            accountPreference.setSummary(null);
        }

        // Set click listener for the login/logout preference
        loginLogoutPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Check if the user is logged in or not
                boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
                Log.d(TAG, "onPreferenceClick: isLoggedIn" + isLoggedIn);
                // Perform login/logout action based on the user's status
                if (isLoggedIn) {
                    // Logout action
                    loginLogoutPreference.setTitle(getString(R.string.login));
                    loginLogoutPreference.setSummary(getString(R.string.login_summary));
                    Toast.makeText(requireContext(), "Logged out", Toast.LENGTH_SHORT).show();
                    accountPreference.setTitle(null);
                    accountPreference.setSummary(null);
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
                    sharedPreferences.edit().putString("username", "").apply();
                    sharedPreferences.edit().putString("userId", "").apply();
                } else {
                    // Login action
                    loginLogoutPreference.setTitle(getString(R.string.login));
                    loginLogoutPreference.setSummary(getString(R.string.login_summary));
                    Toast.makeText(requireContext(), "Logged in", Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putBoolean("isLoggedIn", false).apply();
                    Intent intent = new Intent(requireContext(), LoginActivity.class);
                    startActivity(intent);
//                    preference.setTitle(getString(R.string.logout));
//                    preference.setSummary(getString(R.string.logout_summary));
                }
                return true;
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();

        // Reload the login/logout preference to reflect the updated login status
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
        String username = sharedPreferences.getString("username", "");
        String userId = sharedPreferences.getString("userId", "");

        if (isLoggedIn) {
            // User is logged in
            loginLogoutPreference.setTitle(getString(R.string.logout));
            loginLogoutPreference.setSummary(getString(R.string.logout_summary));
            accountPreference.setTitle("ID: "+ userId);
            accountPreference.setSummary(username);
        } else {
            // User is not logged in
            loginLogoutPreference.setTitle(getString(R.string.login));
            loginLogoutPreference.setSummary(getString(R.string.login_summary));
            accountPreference.setTitle(null);
            accountPreference.setSummary(null);
        }
    }
}