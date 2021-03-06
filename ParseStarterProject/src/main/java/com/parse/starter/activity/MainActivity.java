/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.adapter.TabsAdapter;
import com.parse.starter.util.SlidingTabLayout;


public class MainActivity extends AppCompatActivity {

  private Toolbar toolbarMain;
  private SlidingTabLayout slindingTabLayout;
  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
     //recuperar toolbar princopal
    toolbarMain = (Toolbar) findViewById(R.id.toolbar_principal);

    //Configuração toolbar
    setSupportActionBar( toolbarMain );

    //configurar abas
    slindingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab_main);
    viewPager = (ViewPager) findViewById(R.id.view_page_main);

    //configurar adapter
    TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager(), this );
    viewPager.setAdapter( tabsAdapter);
    //aba customizada inferior
    slindingTabLayout.setCustomTabView(R.layout.tab_view, R.id.text_item_tab);
    //preecher toda a aba inferior
    slindingTabLayout.setDistributeEvenly(true);
    slindingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.branco));
    slindingTabLayout.setViewPager( viewPager );


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    switch (item.getItemId()){
      case R.id.action_sair:
        deslogarUser();
        return true;
      case R.id.action_saldo:
        return true;
      case R.id.action_saldo_plus:
        return true;
      default:
    }
    return super.onOptionsItemSelected(item);
  }

  /*metodo para deslogar usuário*/
  private void deslogarUser(){
    ParseUser.logOut();
    //enviar usuário para tela de login
    Intent intent = new Intent(this, LoginActivity.class);
    startActivity( intent );
  }
}
