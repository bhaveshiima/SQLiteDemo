package vsl.bhavesh.sqlitedemo

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
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


            // Insert data into Table  - return into Long Type
            var status = dBase.insert("employee",null,values)

            if(status==-1.toLong()){
                Toast.makeText(this@MainActivity, "Failed to Insert", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, "Inserted Successfully", Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")
            }

        }  // insert [ Finish ]



        // When user click on Read Button [ START ]
        read.setOnClickListener {

            // query has 7 parameters
            // 1st is the Table name
            // 2nd is cloumn - if null then all columns data
            // 3rd is selection:  select * from employee where id=? and department=?
            // 4th is groupby :
            // 5th is
            // 6th is
            // 7th is

            var cursor = dBase.query("employee",null,null,null,null,null,"id asc")


            /*  Adapter methods to display data
            var from = arrayOf("id","name","designation","department")
            var to = intArrayOf(R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4)

            var cAdapter = SimpleCursorAdapter(this@MainActivity, R.layout.indiview, cursor, from, to,0 )

            lview.adapter = cAdapter
            */

            // with array adpater
            var list = arrayListOf<String>()
            while(cursor.moveToNext())
            {
                list.add(cursor.getInt(1).toString()+"\t"+ cursor.getString(2)+"\n"+ cursor.getString(3)+"\t"+ cursor.getString(4))

            }
            var myadpter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_list_item_single_choice, list)
            lview.adapter = myadpter



        }  // read [ Finish ]




        //update records [ START ]
        update.setOnClickListener {
            // update set name=value,desg=value table_name where id=?

            var cv = ContentValues()
            cv.put("name", et2.text.toString())
            cv.put("designation", et3.text.toString())
            cv.put("department", et4.text.toString())


            var count = dBase.update("employee", cv, "id=?", arrayOf(et1.text.toString()))
            // no of records updated store into count return type is int

            if(count > 0){
                Toast.makeText(this@MainActivity, "Record updated Successfully", Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText(""); et3.setText(""); et4.setText("")

            }else{
                Toast.makeText(this@MainActivity, "Failed to update Successfully", Toast.LENGTH_LONG).show()

            }


        } // update [ END ]



        // Delete record [ START ]

        delete.setOnClickListener {
            // 3 paraeter need to pass into delete operation
           var dcount =  dBase.delete("employee", "id?", arrayOf(et1.text.toString()))

            if(dcount > 0){
                Toast.makeText(this@MainActivity, "Record deleted Successfully", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@MainActivity, "Failed to delete", Toast.LENGTH_LONG).show()
            }





        }


    } //OnCreate method
} //MainActivity
