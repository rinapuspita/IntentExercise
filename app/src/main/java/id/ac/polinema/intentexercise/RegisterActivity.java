package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;
public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;
    public static final String IMAGE_KEY = "image";
    private ImageView avatarImage;
    private EditText fullnameInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText confirmpasswordInput;
    private EditText homepageInput;
    private EditText aboutInput;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fullnameInput = findViewById(R.id.text_fullname);
        emailInput = findViewById(R.id.text_email);
        passwordInput = findViewById(R.id.text_password);
        confirmpasswordInput = findViewById(R.id.text_confirm_password);
        homepageInput = findViewById(R.id.text_homepage);
        aboutInput = findViewById(R.id.text_about);
        avatarImage = findViewById(R.id.image_profile);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    avatarImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handleReg(View view) {

        String fullname = fullnameInput.getText().toString();
        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();
        String confirmPassword = confirmpasswordInput.getText().toString();
        String homePage = homepageInput.getText().toString();
        String about = aboutInput.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!password.equals(confirmPassword)){
            Toast.makeText(this,"Password yang Anda masukkan tidak sesuai. ", Toast.LENGTH_SHORT).show();
        } else if(fullname.equals("")|| email.equals("")|| password.equals("")|| confirmPassword.equals("")||homePage.equals("")|| about.equals("")){
            Toast.makeText(this,"Data tidak boleh kosong. ", Toast.LENGTH_SHORT).show();
        } else if(!email.matches(emailPattern)){
            Toast.makeText(this,"Email Anda tidak valid. ", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, ProfileActivity.class);
            User user = new User(fullname, email, password, confirmPassword, homePage, about);
            intent.putExtra("user", user);
            intent.putExtra(IMAGE_KEY, imageUri.toString());
            startActivity(intent);

        }

    }

    public void handleAvatar(View view) {
        Intent foto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(foto, GALLERY_REQUEST_CODE);
    }
}
