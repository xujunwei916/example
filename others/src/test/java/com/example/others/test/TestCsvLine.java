package com.example.others.test;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class TestCsvLine {


    public static final int INITIAL_STRING_SIZE = 128;
    public static final char DEFAULT_ESCAPE_CHARACTER = '"';

    /** The default separator to use if none is supplied to the constructor. */
    public static final char DEFAULT_SEPARATOR = ',';

    /**
     * The default quote character to use if none is supplied to the
     * constructor.
     */
    public static final char DEFAULT_QUOTE_CHARACTER = '"';

    /** The quote constant to use when you wish to suppress all quoting. */
    public static final char NO_QUOTE_CHARACTER = '\u0000';

    /** The escape constant to use when you wish to suppress all escaping. */
    public static final char NO_ESCAPE_CHARACTER = '\u0000';

    /** Default line terminator uses platform encoding. */
    public static final String DEFAULT_LINE_END = "\n";



    public static void main(String[] args) throws IOException {


        CSVParser parser = new CSVParser();
        String [] line= parser.parseLine("\"111111\",nnnn,\"\n你好\"");
        System.out.println(line);
        for (String l:line             ) {
            System.out.println("-----"+l+"-------");
        }

//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);
//
//        CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
//        writer.writeNext(line);
//        writer.close();
//        System.out.println(new String(byteArrayOutputStream.toByteArray()));

        long start = System.currentTimeMillis();
        int cout=0;
        for (int i = 0; i <10000000 ; i++) {
            toLine2(line);
//            cout+=1;
        }
        System.out.println(System.currentTimeMillis()-start);
        System.out.println(toLine(line));



    }

    public static String toLine(String [] nextLine){
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1000);

            CSVWriter writer = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream));
            writer.writeNext(nextLine);
            writer.close();
            return  new String(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String toLine2(String [] nextLine){
        if (nextLine == null)
            return null;

        StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
        for (int i = 0; i < nextLine.length; i++) {

            if (i != 0) {
                sb.append(DEFAULT_SEPARATOR);
            }

            String nextElement = nextLine[i];
            if (nextElement == null)
                continue;
            if (DEFAULT_QUOTE_CHARACTER !=  NO_QUOTE_CHARACTER)
                sb.append(DEFAULT_QUOTE_CHARACTER);

            sb.append(stringContainsSpecialCharacters(nextElement) ? processLine(nextElement) : nextElement);

            if (DEFAULT_QUOTE_CHARACTER != NO_QUOTE_CHARACTER)
                sb.append(DEFAULT_QUOTE_CHARACTER);
        }

        return sb.toString();
    }
    private static boolean stringContainsSpecialCharacters(String line) {
        return line.indexOf(DEFAULT_QUOTE_CHARACTER) != -1 || line.indexOf(DEFAULT_ESCAPE_CHARACTER) != -1;
    }

    protected  static StringBuilder processLine(String nextElement)
    {
        StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
        for (int j = 0; j < nextElement.length(); j++) {
            char nextChar = nextElement.charAt(j);
            if (DEFAULT_ESCAPE_CHARACTER != NO_ESCAPE_CHARACTER && nextChar == DEFAULT_QUOTE_CHARACTER) {
                sb.append(DEFAULT_ESCAPE_CHARACTER).append(nextChar);
            } else if (DEFAULT_ESCAPE_CHARACTER != NO_ESCAPE_CHARACTER && nextChar == DEFAULT_ESCAPE_CHARACTER) {
                sb.append(DEFAULT_ESCAPE_CHARACTER).append(nextChar);
            } else {
                sb.append(nextChar);
            }
        }

        return sb;
    }



}
