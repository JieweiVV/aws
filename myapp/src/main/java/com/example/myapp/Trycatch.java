package com.example.myapp;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Trycatch {

    
    public static void main( String[] args ) throws CodeException {
        try{
            int tmp = Integer.valueOf("");
            System.out.println(tmp);
            TmpException exception = new CodeException(ErrorCode.OK, "tkzvault", "my message", new RuntimeException());
            throw exception;
        } catch (CodeException e) {
            System.out.println("CodeException catch it:" + e.getCode().name());
            System.out.println(e.getMessage());
            e.printStackTrace();
            StringWriter out = new StringWriter();
            PrintWriter  writer = new PrintWriter(out);
            e.printStackTrace(writer);
            writer.flush(); // flush is really optional here, as Writer calls the empty StringWriter.flush
            String result = out.toString();
            System.out.println(result);
            throw e;
            
        } catch (TmpException e) {
            System.out.println("TmpException catch it: " + ((CodeException)e).getCode().getCode());
            if (e.getCause() instanceof RuntimeException) {
                System.out.println("runtime exception");
            }
        }
    }

    public enum ErrorCode {
        OK(200);

        private final int code;

        private ErrorCode(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
