package com.stechlabs.Views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stechlabs.R
import com.stechlabs.Repository.MyRepository
import com.stechlabs.Repository.MyViewModel
import com.stechlabs.models.NotesResponse
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel:MyViewModel
    lateinit var adapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel=ViewModelProviders.of(this).get(MyViewModel::class.java)
        adapter = MyAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        refresh()

        fab.setOnClickListener{
            val dialog=Dialog()
            dialog.show(supportFragmentManager,"Add")
            dialog.getValue{title:String,desc:String->
                viewModel.addNote(NotesResponse(title, desc))
            }
        }
        // Swiping functionality of Recyclerview

        ItemTouchHelper(object:ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean { return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val note:NotesResponse=adapter.getNote(viewHolder.adapterPosition)
                viewModel.deleteNote(note.id!!)
            }
        }).attachToRecyclerView(recyclerview)

        // recyclerview clicklistener
        adapter.clickEvent {
            val titlee=it.title
            val description=it.description
            val dialog=Dialog()
            val bundle=Bundle()
            bundle.putString("title",titlee)
            bundle.putString("desc",description)
            dialog.arguments=bundle
            dialog.show(supportFragmentManager,"Update")
            dialog.getValue{title:String,desc:String->
                viewModel.updateNote(it.id!!, NotesResponse(title, desc))
            }
        }
    }
    fun refresh(){
        viewModel.getNotes().observe(this@MainActivity, Observer {
            adapter.setList(it.body()!!)
            recyclerview.adapter=adapter
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuinflator=menuInflater
        menuinflator.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.refresh-> refresh()
            R.id.deleteall->{
                viewModel.deleteAllNotes()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        MyRepository.cancelJobs()
    }

    override fun onDestroy() {
        super.onDestroy()
        MyRepository.cancelJobs()
    }

}