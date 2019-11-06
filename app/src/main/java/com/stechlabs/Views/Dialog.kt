package com.stechlabs.Views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import com.stechlabs.R
import kotlinx.android.synthetic.main.dialog.view.*

class Dialog : DialogFragment() {

    lateinit var listener:(String,String)->Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Creating a Dialog

        val builder=AlertDialog.Builder(activity!!)
        val inflator=activity!!.layoutInflater
        val view=inflator.inflate(R.layout.dialog,null)

        // intializing views

        val title:EditText=view.title_edittext
        val desc:EditText=view.desc_edittext

        //setting defaults values from activity for updation

        val titleText:String?=arguments?.getString("title")
        val descText:String?=arguments?.getString("desc")

        titleText?.let {
            title.setText(it)
        }
        descText?.let {
            desc.setText(it)
        }
        // setting the dialog properties

        builder.apply {
            setView(view)
            setTitle("Create Note")
            setNegativeButton("Close"){
                dialog, which ->
                dialog.cancel()
            }
            setPositiveButton("Upload"){
                dialog, which ->
                if(title.text.toString().isNotEmpty() and desc.text.toString().isNotEmpty())
                listener(title.text.toString(),desc.text.toString())
                else{
                    Toast.makeText(activity!!.applicationContext,"No data to insert",Toast.LENGTH_LONG).show()
                    dialog.cancel()
                }

            }
        }
        return builder.create()
    }

    // high order function for update values
    fun getValue(listener:(String,String)->Unit){

        this.listener=listener
    }
}