package com.example.myassignment.presentation.chat

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val chatList : ArrayList<ChatMessage> = ArrayList()
    private lateinit var chatAdapter : ChatAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()
    }

    private fun init(){
        binding.messageEditText.doOnTextChanged { text, start, before, count ->
            binding.btnSend.visibility = View.VISIBLE
            binding.imageSelect.visibility = View.GONE
            binding.imageCamera.visibility = View.GONE
        }
        binding.btnSend.setOnClickListener {
            sendMessage(binding.messageEditText.text.toString())
        }
        setAdapter()
    }
    private fun sendMessage(item: String) {
        if (item.isNotEmpty()) {
            chatList.add(ChatMessage(item))
            binding.messageEditText.text.clear()
            binding.btnSend.visibility = View.GONE
            binding.imageSelect.visibility = View.VISIBLE
            binding.imageCamera.visibility = View.VISIBLE

            chatAdapter.notifyItemInserted(chatList.size-1)
            chatAdapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        binding.messageListRecyclerview.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatAdapter(chatList)
        binding.messageListRecyclerview.adapter = chatAdapter
    }
}
