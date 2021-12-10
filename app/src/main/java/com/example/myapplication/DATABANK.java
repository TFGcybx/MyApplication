package com.example.myapplication;
import android.content.Context;

import com.example.myapplication.R;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DATABANK {
    public static final String DATA_FILE_NAME = "data";
    private final Context context;
    List<BOOK> bookList;

    public DATABANK(Context context) {
        this.context=context;
    }

    @SuppressWarnings("unchecked")
    public List<BOOK> loadData() {
        bookList = new ArrayList<>();
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(context.openFileInput(DATA_FILE_NAME));
            bookList = (ArrayList<BOOK>) objectInputStream.readObject();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return bookList;
    }

    public void saveData() {
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(context.openFileOutput(DATA_FILE_NAME, Context.MODE_PRIVATE));
            objectOutputStream.writeObject(bookList);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
