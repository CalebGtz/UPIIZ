package com.example.damn_eje10_hcgo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewMessageDialogFragment.OnMessageCreatedListener {

    private RecyclerView rvChat;
    private FloatingActionButton fabNew;
    private final ArrayList<Message> messages = new ArrayList<>();
    private MessageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvChat = findViewById(R.id.rvChat);
        fabNew = findViewById(R.id.fabNew);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setStackFromEnd(true);
        rvChat.setLayoutManager(lm);

        adapter = new MessageAdapter(messages);
        rvChat.setAdapter(adapter);

        // Mensajes de ejemplo
        addMessage("¡Hola! Este es un chat de prueba.", false);
        addMessage("Se ve bien. ¿Listo para enviar mensajes?", true);

        fabNew.setOnClickListener(v -> {
            NewMessageDialogFragment.newInstance()
                    .show(getSupportFragmentManager(), "NewMessageDialog");
        });
    }

    private void addMessage(String text, boolean fromMe) {
        messages.add(new Message(text, System.currentTimeMillis(), fromMe));
        int pos = messages.size() - 1;
        adapter.notifyItemInserted(pos);
        rvChat.scrollToPosition(pos);
    }

    @Override
    public void onMessageCreated(String text, boolean fromMe) {
        addMessage(text, fromMe);
    }
}
