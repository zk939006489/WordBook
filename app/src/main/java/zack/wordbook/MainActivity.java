package zack.wordbook;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener,BlankFragment.OnFragmentInteractionListener{
    Button btn_tran;
    private EditText editText1;
    private TextView word;
    private TextView tran_yinbiao;
    private TextView tran_fanyi;
    private TextView tran_exp;
    LinearLayout b1;
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public Object onRetainNonConfigurationInstance() {
        final MyDataObject data = collectMyLoadedData();
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MyDataObject data = (MyDataObject) getLastNonConfigurationInstance();
        if (data == null) {
            data = loadMyData();
        }
        word=(TextView)findViewById(R.id.word);
        tran_yinbiao=(TextView)findViewById(R.id.tran_yinbiao);
        tran_fanyi=(TextView)findViewById(R.id.tran_fanyi);
        tran_exp=(TextView)findViewById(R.id.tran_exp);
        editText1 = (EditText) findViewById(R.id.search_main);
        btn_tran=(Button) findViewById(R.id.tran_main);
        btn_tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = editText1.getText().toString();
                try {
                    NetThread nt1 = new NetThread("http://www.cycycd.com/api/youdao");
                    nt1.setWord(a);
                    nt1.start();
                    try {
                        nt1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Gson gs = new Gson();
                    Jsonres qwe = gs.fromJson(Global.res, Jsonres.class);


                    if (findViewById(R.id.blank) == null) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        BlankFragment b = new BlankFragment(a, qwe.phonetic, qwe.tran, qwe.exp);
                        fragmentTransaction.add(R.id.search_layout, b);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    } else {
                        word.setText(editText1.getText().toString());
                        tran_yinbiao.setText("音标：" + qwe.phonetic);
                        tran_fanyi.setText("翻译：" + qwe.tran);
                        tran_exp.setText("解释：" + qwe.exp);
                    }
                }
                catch(Exception e){
                    Toast.makeText(MainActivity.this, "查无此词，请重新输入！", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_collect:
                Intent intent = new Intent(MainActivity.this, CollectActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}