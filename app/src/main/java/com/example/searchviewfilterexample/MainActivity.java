package com.example.searchviewfilterexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
   private RecyclerView rcvUsers;
   private UserAdapter userAdapter;
   private SearchView searchView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      rcvUsers = findViewById(R.id.rcv_users);
      rcvUsers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

      userAdapter = new UserAdapter(getListUsers());
      rcvUsers.setAdapter(userAdapter);

      RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,
              DividerItemDecoration.VERTICAL);
      rcvUsers.addItemDecoration(itemDecoration);
   }

   private List<User> getListUsers() {
      List<User> list = new ArrayList<>();
      list.add(new User(R.drawable.img_1, "Tincoder", "Ha Noi"));
      list.add(new User(R.drawable.img_2, "dsad12", "Thai Nguyen"));
      list.add(new User(R.drawable.img_3, "51321ds", "Da Nang"));
      list.add(new User(R.drawable.img_4, "615156", "Nghe An"));
      list.add(new User(R.drawable.img_5, "dsadxz", "Quang Tri"));
      list.add(new User(R.drawable.img_1, "hahaf", "Quang Binh"));
      list.add(new User(R.drawable.img_2, "hahaga", "Ho Chi Minh"));
      list.add(new User(R.drawable.img_3, "hduaihduai", "Quang Nam"));
      list.add(new User(R.drawable.img_4, "djaioda", "Binh Dinh"));
      list.add(new User(R.drawable.img_5, "k1jdsaj", "Binh Duong"));

      return list;
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.main_menu, menu);

      SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

      searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
      searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
      searchView.setMaxWidth(Integer.MAX_VALUE);

      searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String s) {
            userAdapter.getFilter().filter(s);
            return false;
         }

         @Override
         public boolean onQueryTextChange(String s) {
            userAdapter.getFilter().filter(s);
            return false;
         }
      });

      return true;
   }
}