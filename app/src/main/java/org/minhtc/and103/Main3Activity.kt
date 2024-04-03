package org.minhtc.and103

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.AuthFailureError
import com.android.volley.NetworkError
import com.android.volley.ParseError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.ServerError
import com.android.volley.TimeoutError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.android.volley.NoConnectionError as NoConnectionError1

class Main3Activity : AppCompatActivity() {
    var strJson = "";
    var context: Context = this;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.enableEdgeToEdge()
        setContentView(R.layout.activity_main3)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        var tvkq = findViewById<TextView>(R.id.textView)
        var btn = findViewById<Button>(R.id.button)
        btn!!.setOnClickListener {
            readData(context, tvkq)
        }
    }
    fun readData(context: Context, textView: TextView) {
        val queue = Volley.newRequestQueue(context)
        val url = "https://jsonplaceholder.typicode.com/todos/1"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->

                textView.text = "Response: %s".format(response.toString())

            },
            {
                error ->
                Log.d("activity 3", error.toString())
                textView.text = "That didn't work!"
            }
        )
        queue.add(jsonObjectRequest)
    }
}