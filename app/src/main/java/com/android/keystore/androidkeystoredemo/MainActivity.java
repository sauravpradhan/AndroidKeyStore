package com.android.keystore.androidkeystoredemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {
    private static final String ALIAS = "MYALIAS";
    private Encrypt mEncrypt;
    private Decrypt mDecrypt;
    Button toEncryt,toDecrypt;
    EditText password;
    TextView decrypted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        password = (EditText) findViewById(R.id.editText);
        decrypted = (TextView) findViewById(R.id.textView2);
        toEncryt = (Button) findViewById(R.id.button);
        toDecrypt = (Button) findViewById(R.id.button2);
        mEncrypt = new Encrypt();
        try {
            mDecrypt = new Decrypt();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        toEncryt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encryptText();
            }
        });
        toDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decryptText();
            }
        });
    }
    private void encryptText() {

        try {
            final byte[] encryptedText = mEncrypt.encryptText(ALIAS, password.getText().toString());
            Log.d("s@urac","s@urac Encrypted Text:"+encryptedText);
        } catch (InvalidAlgorithmParameterException | SignatureException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void decryptText() {
        try {
            Log.d("s@urac","s@urac Decrypted data is:"+mDecrypt.decryptData(ALIAS, mEncrypt.getEncryption(), mEncrypt.getIv()));
            decrypted.setText(mDecrypt.decryptData(ALIAS, mEncrypt.getEncryption(), mEncrypt.getIv()));
            decrypted.setVisibility(View.VISIBLE);
        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
                IOException | InvalidKeyException e) {
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
