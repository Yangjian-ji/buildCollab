package com.example.buildcollab.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Class inutil neste trabalho, apenas serve para verficar os editText
 */
public class checkInput {

    boolean valid;

    public checkInput() {
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    /**
     * Verificar se escreveu bem o email
     * @param input
     */

    public void emailInputVerification(EditText input) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input.getText().toString().contains("@")) {
                    valid = true;
                } else {
                    valid = false;
                    input.setError("This is not a valid email");
                }
            }
        };
        input.addTextChangedListener(watcher);
    }

    /**
     * Verifica se inseriu alguma coisa
     * @param input
     */
    public void normalInputVerification(EditText input) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (input.getText().toString().isEmpty()) {
                    valid = false;
                    input.setError("This slot can't be empty");
                } else {
                    valid = true;
                }
            }
        };
        input.addTextChangedListener(watcher);
    }

    /**
     * Verifica se e igual a pass e a pass de confirmacao
     * @param password
     * @param cpassword
     */
    public void cpasswordVerification(EditText password, EditText cpassword) {
        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (password.getText().toString().equals(cpassword.getText().toString())) {
                    valid = true;
                } else {
                    valid = false;
                    cpassword.setError("Those passwords don't match");
                }
            }
        };
        cpassword.addTextChangedListener(watcher);
    }
}
