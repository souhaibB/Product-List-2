package com.example.souhaib.productlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    ArrayList<Produit> ListP = new ArrayList<Produit>();

    Produit p1 = new Produit(1,"Dell","Inspiron",1200,50);
    Produit p2 = new Produit(2,"Acer","aspire",1100,30);
    Produit p3 = new Produit(3,"HP","pavilion",1300,10);
    Produit p4 = new Produit(4,"Toshiba","satellite",1000,40);

    ArrayList<String> Panier = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int k=0;

        ListP.add(p1);
        ListP.add(p2);
        ListP.add(p3);
        ListP.add(p4);

        final String[] ProduitArray = new String[ListP.size()];

        for(int i=0;i<ListP.size();i++){
            ProduitArray[i] = ListP.get(i).getDesignation();

        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ProduitArray);
        mListView = (ListView) findViewById(R.id.mListView);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stringText;

                stringText = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, stringText, Toast.LENGTH_SHORT).show();
                YesNoDialog(stringText);
            }
        });

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Panier.size()==0){
                    Toast.makeText(MainActivity.this,"Panier est vide !!", Toast.LENGTH_SHORT).show();
                }
                else{
                    for(int j=0;j<Panier.size();j++){
                        Toast.makeText(MainActivity.this, Panier.get(j).toString(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    public void YesNoDialog (final String titre){

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Panier.add(titre);
                Toast.makeText(MainActivity.this, "Product has been added !!", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("Google it", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Uri uri = Uri.parse("https://www.google.com/search?q="+titre);
                Intent gSearchIntent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(gSearchIntent);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
