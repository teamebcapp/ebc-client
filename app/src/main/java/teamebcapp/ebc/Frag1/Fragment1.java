package teamebcapp.ebc.Frag1;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import teamebcapp.ebc.Frag2.registerBCactivity;
import teamebcapp.ebc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    EditText textView;

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, null);

        final Button btn_start_qrcode_generate = view.findViewById(R.id.btn_start_qrcode_generate);
        final EditText edt_qrcode_content = view.findViewById(R.id.edt_qrcode_content);
        final ImageView img_qrcode_result = view.findViewById(R.id.iv_generated_qrcode);

        btn_start_qrcode_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edt_qrcode_content.getText().toString();
                if (content.isEmpty()) {
                    Toast.makeText(getActivity(), "문자를 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    //QRcodegenerator
                    QRCodeWriter qrCodeWriter = new QRCodeWriter();
                    try {
                        Bitmap bitmap = toBitmap(qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 200, 200));
                        img_qrcode_result.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                }

            }
        });


        return view;
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


}