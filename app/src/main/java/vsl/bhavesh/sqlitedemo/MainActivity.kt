package vsl.bhavesh.sqlitedemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var dBase = openOrCreateDatabase("sqlite", Context.MODE_PRIVATE, null)  // SQLDatabase claass oject

        // Create table and database
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement, id integer, name varchar(100), designation varchar(100), department varchar(100))" )


    } //OnCreate method
} //MainActivity
