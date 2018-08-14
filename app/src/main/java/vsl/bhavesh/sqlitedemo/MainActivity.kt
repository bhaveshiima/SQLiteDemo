package vsl.bhavesh.sqlitedemo

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var dBase = openOrCreateDatabase("tblSQLite", Context.MODE_PRIVATE, null)  // SQLDatabase claass oject

        // Create table and database
        dBase.execSQL("create table if not exists employee(_id integer primary key autoincrement, id integer, name varchar(100), designation varchar(100), department varchar(100))" )

        insert.setOnClickListener {

            // contect value oject
            var values = ContentValues()

            // key and value want to insert
            values.put("id", et1.text.toString().toInt())
            values.put("name", et2.text.toString())
            values.put("designation", et3.text.toString())
            values.put("department", et4.text.toString())


            // Insert data into Table
            var status = dBase.insert("employee",null,values)

            if(status==-1.toLong()){
                Toast.makeText(this@MainActivity, "Failed to Insert", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, "Inserted Successfully", Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")
            }







        }



    } //OnCreate method
} //MainActivity
