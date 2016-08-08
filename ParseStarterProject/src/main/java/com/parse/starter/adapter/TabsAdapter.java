package com.parse.starter.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.parse.starter.R;
import com.parse.starter.fragments.EventosFragment;
import com.parse.starter.fragments.HomeFragment;
import com.parse.starter.fragments.UsuarioFragment;

/**
 * Created by Rafael on 07/08/2016.
 */
public class TabsAdapter  extends FragmentStatePagerAdapter{

    private Context context;
    //private String[] abas = new String[]{"HOME", "EVENTOS", "USUÁRIO"};
    private int[] icones = new int[]{R.drawable.ic_home_white_18dp, R.drawable.ic_action_list, R.drawable.ic_account_circle_white_18dp};
    private int tamanhoIcone;
    public TabsAdapter(FragmentManager fm, Context c) {
        super(fm);
        this.context = c;
        double escala = this.context.getResources().getDisplayMetrics().density;
        tamanhoIcone = (int) (36 * escala);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new HomeFragment();
                break;
            case 1 :
                fragment = new UsuarioFragment();
                break;
            case 2:
                fragment = new EventosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //recuperar icones de acordo com a posição
        Drawable drawable = ContextCompat.getDrawable(this.context, icones[ position ]);
        drawable.setBounds(0,0,tamanhoIcone,tamanhoIcone);

        //permite colocar imagem em texto
        ImageSpan imageSpan = new ImageSpan( drawable );

        //retorna char sequence
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableString;

    }

    @Override
    public int getCount() {

        return icones.length;
    }
}
