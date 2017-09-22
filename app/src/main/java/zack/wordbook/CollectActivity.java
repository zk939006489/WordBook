package zack.wordbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectActivity extends AppCompatActivity implements CollectFragment.OnFragmentInteractionListener,BlankFragment.OnFragmentInteractionListener{

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_collect, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private ArrayList<Map<String,String>> getAll(){
        ArrayList <Map<String,String>> items = new ArrayList<Map<String,String>>();
        for(int i=0;i<this.getResources().getStringArray(R.array.items).length;++i){
            HashMap<String,String>hashMap = new HashMap<String,String>();
            hashMap.put("word",getResources().getStringArray(R.array.items)[i]);
            items.add(hashMap);
        }
        return items;
    }

    private void setWordListView(ArrayList<Map<String,String>> items){
        SimpleAdapter adapter = new SimpleAdapter(this,items,R.layout.fragment_item,
                new String[]{
                        "word"
                },
                new int[]{
                        R.id.content
                });
        ListView list = (ListView) findViewById(R.id.ListView);
        list.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ListView list = (ListView) findViewById(R.id.ListView);
        registerForContextMenu(list);
        ArrayList<Map<String,String>> items = getAll();
        setWordListView(items);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_main:
                Intent intent = new Intent(CollectActivity.this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
