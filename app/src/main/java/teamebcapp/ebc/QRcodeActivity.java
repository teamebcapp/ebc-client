package teamebcapp.ebc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRcodeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        findViewById(R.id.btn_start_qrcode_reader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQRCode();
            }
        });

        findViewById(R.id.btn_start_qrcode_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = ((EditText) findViewById(R.id.edt_qrcode_content)).getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(QRcodeActivity.this, "문자를 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    generateRQCode(content);
                }

            }
        });

    }

    public void startQRCode() {
        new IntentIntegrator(this).initiateScan();
    }

    public void generateRQCode(String contents) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            Bitmap bitmap = toBitmap(qrCodeWriter.encode(contents, BarcodeFormat.QR_CODE, 200, 200));
            ((ImageView) findViewById(R.id.iv_generated_qrcode)).setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static Bitmap toBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
