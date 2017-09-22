package zack.wordbook;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BlankFragment extends Fragment {
    TextView word;
    TextView phonetic;
    TextView tran;
    TextView exp;
    String dc;
    String yb;
    String fy;
    String js;
    Boolean isv=false;
    public BlankFragment() {

    }
    public BlankFragment(String a,String b,String c,String d) {
        this.dc=a;
        this.yb=b;
        this.fy=c;
        this.js=d;
        this.isv=true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank,container,false);
        word=view.findViewById(R.id.word);
        phonetic=view.findViewById(R.id.tran_yinbiao);
        tran=view.findViewById(R.id.tran_fanyi);
        exp=view.findViewById(R.id.tran_exp);
        if(isv)
        {
            word.setText(dc);
            phonetic.setText("音标："+yb);
            tran.setText("翻译："+fy);
            exp.setText("解释："+js);
        }
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
