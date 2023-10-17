package com.example.proyectoseminario
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private var rvLista: RecyclerView? = null
    private var adapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyToolbar().show(this, "NETFLIX", false)
        initViews()
    }

    private fun initViews() {
        rvLista = findViewById(R.id.rvLista)
        val manager = LinearLayoutManager(this)
        rvLista!!.layoutManager = manager
        adapter = RecyclerAdapter(this)
        rvLista!!.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_contextual, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.opcion1)
            startActivity(Intent(this, PreguntasFrecuentes::class.java))
        if (item.itemId == R.id.opcion2)
            startActivity(Intent(this, TerminosCondiciones::class.java))
        if (item.itemId == R.id.opcion3)
            startActivity(Intent(this, Informacion::class.java))
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val intent=  Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}
