package org.minhtc.and103.ui.login;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.minhtc.and103.data.payload.login.LoginRequest;
import org.minhtc.and103.data.payload.login.LoginResponse;
import org.minhtc.and103.infrastructure.service.AuthService;
import org.minhtc.and103.infrastructure.service.RetrofitClient;
import org.minhtc.and103.databinding.ActivityLoginBinding;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {

//    private LoginViewModel loginViewModel;
    private static final String TAG = "LoginActivity";
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
//                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                loadingProgressBar.setVisibility(View.VISIBLE);
                LoginRequest loginUser = new LoginRequest(usernameEditText.getText().toString(), passwordEditText.getText().toString());
                Log.d(TAG, "onClick: "  + loginUser);
                AuthService authService = RetrofitClient.getClient().create(AuthService.class);
                Call<LoginResponse> login = authService.login(loginUser);
                login.enqueue(new retrofit2.Callback<LoginResponse>() {
                    @Override
                    public void onResponse(retrofit2.Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {
                        Log.d(TAG, "onResponse: " + response);
                        if (response.isSuccessful()) {
                            sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
                            LoginResponse body = response.body();
                            if (body == null) {
                                Log.d(TAG, "onResponse: body is null");
                                return;
                            } else {
                                Log.d(TAG, "onResponse: " + body);
                                sharedPreferences.edit().putString("userId", body.getUserId()).apply();
                                sharedPreferences.edit().putString("username", body.getUsername()).apply();
                            }

                            loadingProgressBar.setVisibility(View.GONE);
                            finish();
                        } else {
                            Log.d(TAG, "onResponse: " + response.errorBody());
                            Log.d(TAG, "onResponse: " + call.request().body());
                            Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                            loadingProgressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(retrofit2.Call<LoginResponse> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                        loadingProgressBar.setVisibility(View.GONE);
                    }
                });
//                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
//                finish();
            }
        });

//        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
//            @Override
//            public void onChanged(@Nullable LoginFormState loginFormState) {
//                if (loginFormState == null) {
//                    return;
//                }
//                loginButton.setEnabled(loginFormState.isDataValid());
//                if (loginFormState.getUsernameError() != null) {
//                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
//                }
//                if (loginFormState.getPasswordError() != null) {
//                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
//                }
//            }
//        });
//
//        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
//            @Override
//            public void onChanged(@Nullable LoginResult loginResult) {
//                if (loginResult == null) {
//                    return;
//                }
//                loadingProgressBar.setVisibility(View.GONE);
//                if (loginResult.getError() != null) {
//                    showLoginFailed(loginResult.getError());
//                }
//                if (loginResult.getSuccess() != null) {
//                    updateUiWithUser(loginResult.getSuccess());
//                }
//                setResult(Activity.RESULT_OK);
//
//                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply();
//                //Complete and destroy login activity once successful
//                finish();
//            }
//        });

//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        };
//        usernameEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.addTextChangedListener(afterTextChangedListener);
//        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    loginViewModel.login(usernameEditText.getText().toString(),
//                            passwordEditText.getText().toString());
//                }
//                return false;
//            }
//        });
//
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadingProgressBar.setVisibility(View.VISIBLE);
//                loginViewModel.login(usernameEditText.getText().toString(),
//                        passwordEditText.getText().toString());
//            }
//        });
//    }
//
//    private void updateUiWithUser(LoggedInUserView model) {
//        String welcome = getString(R.string.welcome) + model.getDisplayName();
//        // TODO : initiate successful logged in experience
//        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
//    }
//
//    private void showLoginFailed(@StringRes Integer errorString) {
//        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}