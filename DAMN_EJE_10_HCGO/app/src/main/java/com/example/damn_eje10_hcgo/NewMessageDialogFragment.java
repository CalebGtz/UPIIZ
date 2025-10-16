package com.example.damn_eje10_hcgo;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class NewMessageDialogFragment extends DialogFragment {

    public interface OnMessageCreatedListener {
        void onMessageCreated(String text, boolean fromMe);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View content = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_new_message, null, false);

        Spinner spSender = content.findViewById(R.id.spSender);
        EditText etMessage = content.findViewById(R.id.etMessageInput);

        // Spinner: "Yo" (index 0) / "Otro" (index 1)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sender_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSender.setAdapter(adapter);
        spSender.setSelection(0); // por defecto "Yo"

        MaterialAlertDialogBuilder b = new MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.dialog_title)
                .setView(content)
                .setPositiveButton(R.string.action_send, null) // set later to validate
                .setNegativeButton(R.string.action_cancel, (d, w) -> d.dismiss());

        Dialog dialog = b.create();

        dialog.setOnShowListener(dlg -> {
            // Interceptar para validar antes de cerrar
            ((androidx.appcompat.app.AlertDialog) dialog)
                    .getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                    .setOnClickListener(v -> {
                        String txt = etMessage.getText().toString().trim();
                        if (TextUtils.isEmpty(txt)) {
                            etMessage.setError(getString(R.string.error_empty));
                            return;
                        }
                        boolean fromMe = spSender.getSelectedItemPosition() == 0;
                        if (getActivity() instanceof OnMessageCreatedListener) {
                            ((OnMessageCreatedListener) getActivity()).onMessageCreated(txt, fromMe);
                        }
                        dialog.dismiss();
                    });
        });

        return dialog;
    }

    public static NewMessageDialogFragment newInstance() {
        return new NewMessageDialogFragment();
    }
}
