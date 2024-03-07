package org.minhtc.and103;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity2";
    TextView tvKq;
    Button btnInsert, btnUpdate, btnDelete, btnSelect;
    FirebaseFirestore db;
    Context context = this;
    String strKq = "";
    ToDo todo = null;
    String[] todos = null;

    List<String> stringList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKq = findViewById(R.id.tvKq);
        db = FirebaseFirestore.getInstance();
        btnSelect = findViewById(R.id.btnIndex);
        btnInsert = findViewById(R.id.btnCreate);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect.setOnClickListener(v -> {
            select();
        });
        btnInsert.setOnClickListener(v -> {
            insert();
        });
        btnUpdate.setOnClickListener(v -> {
            update();
        });
        btnDelete.setOnClickListener(v -> {
            delete();
        });
    }
    void insert() {
        todo = new ToDo(UUID.randomUUID().toString(),
                "Làm bài tập",
                "Làm bài tập Android");
        Log.d(TAG, "delete: " + todo);
        db.collection("todos")
                .document(todo.getId())
                .set(todo.convertToHashMap())
                .addOnSuccessListener(documentReference -> {
                    strKq = "Thêm thành công";
                    //Toast
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    strKq = "Thêm thất bại";
                    //Toast
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                });
    }
    void update() {
        Log.d(TAG, "delete: " + todo);
        todo.setTitle("Làm bài tập Android 2");
        todo.setContent("Làm bài tập Android 2");
        db.collection("todos")
                .document(todo.getId())
                .update(todo.convertToHashMap())
                .addOnSuccessListener(documentReference -> {
                    strKq = "Cập nhật thành công";
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    strKq = "Cập nhật thất bại";
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                });
    }
    void delete() {
        Log.d(TAG, "delete: " + todo);
        db.collection("todos")
                .document(todo.getId())
                .delete()
                .addOnSuccessListener(documentReference -> {
                    strKq = "Xóa thành công";
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    strKq = "Xóa thất bại";
                    Toast.makeText(context, strKq, Toast.LENGTH_SHORT).show();
                });
    }
    String[] select() {
        ArrayList<ToDo> todos = new ArrayList<>();
        db.collection("todos")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String str = "";
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            todo = document.toObject(ToDo.class);
                            todos.add(todo);
                            stringList.add(todo.toDisplay());
                            str += todo.getId() + "\n" + todo.getTitle() + "\n" + todo.getContent() + "\n\n";
                        }
                        Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
                        tvKq.setText(str);
                        strKq = str;
                    } else {
                        Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show();
                        tvKq.setText(strKq);
                    }
                });
        return stringList.toArray(new String[0]);
    }
}