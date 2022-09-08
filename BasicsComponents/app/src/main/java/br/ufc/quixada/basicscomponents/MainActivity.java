package br.ufc.quixada.basicscomponents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //11 foto colocada na activity 2
    private ToggleButton toggleButton1;
    private Button btn, btnAuto,btnRadio, buttonPoup, longClick, goSecondAct;
    private EditText editText;
    private AutoCompleteTextView autoCompleteText;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter adapterSpinner;
    // 3)
    private ArrayList<String> texts = new ArrayList();
    //Usando na 4) questão:
    private final String[] people = new String[] {
            "Bruno Sales",
            "Icaro Caminha",
            "Mais um Cara",
            "Israel Negão",
            "Elixande Silveira"
    };
    //Usando na 5)
    private Spinner exampleSpinner;
    //6) Radio Group
    private RadioGroup radioG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addActionsScreen();
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    public void addActionsScreen() {
        toggleButton1 = findViewById(R.id.toggleButtonOne);
        btn = findViewById(R.id.buttonSubmitText);
        editText = findViewById(R.id.textViewEdit);
        autoCompleteText = findViewById(R.id.autoCompleteTextView);
        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                people
        );
        //5) spinner
        adapterSpinner = ArrayAdapter.createFromResource(
                this,
                R.array.peaple,
                android.R.layout.simple_spinner_item);

        autoCompleteText.setAdapter(adapter);
        btnAuto = findViewById(R.id.buttonAuto);
        //5) spinner
        exampleSpinner = findViewById(R.id.spinner);
        exampleSpinner.setAdapter(adapterSpinner);

        //6) Radio Buttons
        btnRadio = findViewById(R.id.buttonRadio);
        radioG = findViewById(R.id.radioGroupp);
        radioG.clearCheck();

        //8) pouop Menu
        buttonPoup = findViewById(R.id.buttonPoup);

        //10) Clique Longo
        longClick = findViewById(R.id.buttonLongClick);

        //12) Navegação
        goSecondAct = findViewById(R.id.goActivity);


        //1) Toggle Button
        toggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Toggle Ligado!!",
                            Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Toggle Desligado!!",
                            Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        //2) Edit Text
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = editText.getText().toString();
                texts.add(str + "\n");
                Toast.makeText(
                                getApplicationContext(),
                                str.toString() + "Array: " + texts,
                                Toast.LENGTH_SHORT)
                        .show();
                editText.setText("");
            }
        });

        //4)
        btnAuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = autoCompleteText.getText().toString();
                Toast.makeText(
                                getApplicationContext(),
                                str.toString(),
                                Toast.LENGTH_SHORT)
                        .show();
                texts.add(str + "\n");
                autoCompleteText.setText("");
            }
        });

        //6)
        radioG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rb = radioGroup.findViewById(i);
                if(null!=rb && i != -1 ){
                    Toast.makeText(
                            MainActivity.this,
                            rb.getText(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRadio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioG.clearCheck();
            }
        });

        //8) pouop Menu
        buttonPoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(MainActivity.this, buttonPoup);
                popup.getMenuInflater().inflate(R.menu.poupup_menu, popup.getMenu());

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Toast.makeText(
                          MainActivity.this,
                          "Você clicou no popup: " + menuItem.getTitle(),
                          Toast.LENGTH_SHORT
                        ).show();
                        return true;
                    }
                });

                popup.show();
            }
        });

        longClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(
                        MainActivity.this,
                        "Um longo Clique feitooo",
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });

        //12) Navegação
        goSecondAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SegundaActivity.class);
                startActivity(i);
            }
        });

    }

}