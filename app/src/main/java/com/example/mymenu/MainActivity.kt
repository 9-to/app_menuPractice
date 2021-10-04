package com.example.mymenu

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.mymenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.allowFileAccess = true
        binding.webView.settings.allowContentAccess = true
        //binding.webView.loadUrl("file:///android_asset/html/index.html")
        binding.webView.loadUrl("https://www.google.com")
        registerForContextMenu(binding.webView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.top ->{
                //binding.webView.loadUrl("file:///android_asset/html/index.html")
                binding.webView.loadUrl("https://www.google.com")
                return true
            }
            R.id.lunch01->{
                binding.webView.loadUrl("https://www.msn.com")
                return true
            }
            R.id.lunch02->{
                binding.webView.loadUrl("https://m.yahoo.co.jp/")
                return true
            }
            R.id.dinner01->{
                binding.webView.loadUrl("https://www.bing.com/")
                return true
            }
            R.id.dinner02->{
                binding.webView.loadUrl("https://www.ask.com/")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.sms->{
                val number = "999-9999-9999"
                val uri = Uri.parse("sms:$number")
                var intent = Intent(Intent.ACTION_VIEW)
                intent.data = uri
                startActivity(intent)
                return true
            }
            R.id.mail->{
                val email = "nobady@example.com"
                val subject = "予約問い合わせ"
                val text = "以下の通り予約希望します"
                val uri = Uri.parse("mailto:")
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = uri
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
                    .putExtra(Intent.EXTRA_SUBJECT, subject)
                    .putExtra(Intent.EXTRA_TEXT, text)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
            R.id.share->{
                val text = "おいしいレストランを紹介します"
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,text)
                val chooser = Intent.createChooser(intent,null)
                if (intent.resolveActivity(packageManager) != null){
                    startActivity(chooser)
                }
                return true
            }
            R.id.browse->{
                val url: String = "http://www.yahoo.co.jp/"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                if(intent.resolveActivity(packageManager) != null){
                    startActivity(intent)
                }
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

}