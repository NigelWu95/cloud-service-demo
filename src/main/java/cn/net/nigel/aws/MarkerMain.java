package cn.net.nigel.aws;

import com.amazonaws.util.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class MarkerMain {

    public static void main(String[] args) throws IOException {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                new ByteArrayOutputStream()
        );
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(com.amazonaws.util.Base64
                .decode("Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8"));
//        outputStreamWriter.
        System.out.println(outputStream.toString());
//        System.out.println(CodecUtils);
        System.out.println();
        System.out.println(new String(BinaryUtils.fromBase64("Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8")));
        System.out.println(CodecUtils.toStringDirect("Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8"
                .getBytes("utf-8")));
        System.out.println(new String(com.amazonaws.util.Base64
                .decode("Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8"
                        .getBytes("utf-8"))));
        Base64.Decoder decoder = Base64.getDecoder();
        String keyString = new String(decoder.decode("Vtu5vEsvdsHBvyA/Y/z6ihTlTEqq9q5RuUi0J8N/c+oYQJiP/aRLt/8E8gXxFwTFy4O3O96Cou7/u/XQNHethzMjVhmRkW+8"
                .getBytes("utf-8")
        ));
        System.out.println(keyString);
    }
}
